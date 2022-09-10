package brasileirao.controll;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import brasileirao.model.Jogador;
import brasileirao.model.Partida;
import brasileirao.model.Time;
import brasileirao.model.Treinador;
import config.configuracoes;

/**
 * Classe que conecta a model com a view.
 * 
 * @author Leonardo Passos
 * @since 2022
 * @version 1.1
 */

public class Campeonato {
	private ArrayList<Partida> tabelaDePartidas;
	public static ArrayList<Time> times;
	private final String Elencos;
	private final String Partidas;

	public Campeonato(String PathElencos, String PathPartidas) {
		this.tabelaDePartidas = new ArrayList<>();
		times = new ArrayList<>();
		this.Elencos = PathElencos;
		this.Partidas = PathPartidas;
		carregarDados();
	}

	/** Getters and Setters */
	public ArrayList<Partida> getTabelaDePartidas() {
		return tabelaDePartidas;
	}

	public void setTabelaDePartidas(ArrayList<Partida> tabelaDePartidas) {
		this.tabelaDePartidas = tabelaDePartidas;
	}

	public ArrayList<Time> getTimes() {
		return times;
	}

	public void setTimes(ArrayList<Time> times) {
		this.times = times;
	}

	public void update() {
		carregarDados();
	}

	/**
	 * Carregamento de dados dos arquivos
	 * --------------------------------------------------------------------------
	 * Instancia todos times e jogadores envolvidos no campeonato a partir do
	 * arquivo CSV.
	 */

	public void carregarDadosElencos() {
		File arquivo = new File(Elencos);
		if (arquivo.exists()) {
			if (arquivo.isFile() && (arquivo.length() > 0)) {
				BufferedReader leitor = null;
				try {
					leitor = new BufferedReader(new FileReader(Elencos));
					String linha;
					linha = leitor.readLine();
					linha = leitor.readLine();
					int contador = 11;
					while (linha != null) {
						String dados[] = linha.split(";");
						if (contador % 11 == 0) {
							this.cadastrarTime(dados[1]);
							this.cadastrarTreinador(dados[3], dados[0]);
						}
						this.cadastrarJogador(dados[6], dados[5], dados[0]);

						contador++;
						linha = leitor.readLine();
					}

				} catch (IOException ioex) {
				}
			}
		}
	}

	/** Instancia todas as partidas do campeonato a partir do arquivo CSV. */
	public void carregarPartidas() {
		File arquivo = new File(Partidas);
		if (arquivo.exists()) {
			if (arquivo.isFile() && (arquivo.length() > 0)) {
				BufferedReader leitor = null;
				try {
					leitor = new BufferedReader(new FileReader(Partidas));
					String linha = leitor.readLine();
					int contador = 0;
					while (linha != null) {
						String dados[] = linha.split(";");
						this.cadastrarPartida(dados[1], dados[4]);
						if (dados[5].equals("R")) {
							this.tabelaDePartidas.get(contador).setStatus("REALIZADA");

							ArrayList<Integer> idMarcadores = new ArrayList<Integer>();
							idMarcadores.add(contador);
							for (int a = 6; a <= 15; a++) {
								if (Integer.parseInt(dados[a]) == 1000) {
									break;
								}
								idMarcadores.add(Integer.parseInt(dados[a]));
							}

							carregarMarcadores(idMarcadores);

						}
						int[] dadosPartida = { Integer.parseInt(dados[0]), Integer.parseInt(dados[2]),
								Integer.parseInt(dados[3]) };
						carregarResultadoDoArquivo(dadosPartida);
						contador++;
						linha = leitor.readLine();
					}

				} catch (IOException ioex) {
				}
			}
		}
	}

	public void carregarDados() {
		this.carregarDadosElencos();
		this.carregarPartidas();
	}

	/** metodos de organanizacao do programa */

	/** Instancia um Time e o insere na ArrayList do campeonato. */
	public void cadastrarTime(String nome) {
		Time time = new Time(nome);
		this.times.add(time);
	}

	/** Instancia um jogador e o insere na ArrayList de jogadores do Time. */
	public void cadastrarJogador(String nome, String posicao, String id) {
		Jogador jogador = new Jogador(nome, posicao);
		int idTime = Integer.parseInt(id);
		jogador.setIdTime(idTime);
		jogador.setTime();

		this.times.get(idTime).cadastrarJogador(jogador);
	}

	/** Instancia um treinador e o insere no Time. */
	public void cadastrarTreinador(String nome, String id) {
		Treinador treinador = new Treinador(nome);
		int idTime = Integer.parseInt(id);
		treinador.setIdTime(idTime);
		treinador.setTime();

		this.times.get(idTime).setTreinador(treinador);
	}

	/** Instancia uma partida e a inclui na ArrayList tabelaDePartidas */
	public void cadastrarPartida(String timeMandante, String timeVisitante) {
		Partida partida = new Partida(timeMandante, timeVisitante);
		this.tabelaDePartidas.add(partida);
	}

	/**
	 * Carrega todos os resultados constantes do arquivo CSV relacionados as
	 * partidas ja ocorridas
	 */
	public void carregarResultadoDoArquivo(int[] dadosPartida) {
		int id, gMandante, gVisitante;

		id = dadosPartida[0];
		gMandante = dadosPartida[1];
		gVisitante = dadosPartida[2];

		String nomeMandante = this.tabelaDePartidas.get(id).getTimeMandante();
		String nomeVisitante = this.tabelaDePartidas.get(id).getTimeVisitante();

		int idMandante = 0;
		int idVisitante = 0;

		for (int b = 0; b < this.tabelaDePartidas.size(); b++) {
			if (this.tabelaDePartidas.get(b).getIdPartida() == id) {
				if (this.tabelaDePartidas.get(b).getStatus().equals("REALIZADA")) {
					this.tabelaDePartidas.get(b).setGolsMandante(gMandante);
					this.tabelaDePartidas.get(b).setGolsVisitante(gVisitante);

					for (int a = 0; a < this.times.size(); a++) {
						if (this.times.get(a).getNome().equals(nomeMandante)) {
							idMandante = a;
							this.times.get(idMandante).somaGolsPara(gMandante);
							this.times.get(idMandante).somaGolsContra(gVisitante);
							if (gMandante > gVisitante) {
								this.times.get(idMandante).somaVitorias(a);
							} else if (gMandante < gVisitante) {
								this.times.get(idMandante).somaDerrotas(a);
							} else if (gMandante == gVisitante) {
								this.times.get(idMandante).somaEmpates(a);
							}
						}
					}

					for (int c = 0; c < this.times.size(); c++) {
						if (this.times.get(c).getNome().equals(nomeVisitante)) {
							idVisitante = c;
							this.times.get(idVisitante).somaGolsPara(gVisitante);
							this.times.get(idVisitante).somaGolsContra(gMandante);
							if (gMandante < gVisitante) {
								this.times.get(idVisitante).somaVitorias(c);
							} else if (gMandante > gVisitante) {
								this.times.get(idVisitante).somaDerrotas(c);
							} else if (gMandante == gVisitante) {
								this.times.get(idVisitante).somaEmpates(c);
							}
						}
					}

				}

			}
		}
	}

	/** Cria um jogador e inclu�-lo na lista de jogadores. */
	public boolean addJogador(int TimeID, String Nome, String Posicao) {
		try {
			Jogador novoJogador = new Jogador(Nome, Posicao);
			novoJogador.setIdTime(TimeID);
			novoJogador.setTime();
			int idTime = posTimeNaListaDeTimes(TimeID);
			times.get(idTime).getJogadores().add(novoJogador);
			return true;
		} catch (Exception E) {
			return false;
		}
	}

	public boolean deleteJogador(int IDTime, int IDJogador) {
		try {
			int idTime = posTimeNaListaDeTimes(IDTime);
			/** retorna a posicao do jogador na lista de jogadores do time */
			int encontrarJogador = configuracoes.posJogEmUmaLista(String.valueOf(IDJogador),
					times.get(idTime).getJogadores());
			times.get(idTime).getJogadores().remove(encontrarJogador);
			return true;
		} catch (Exception E) {
			return false;
		}
	}

	/** Busca a posicao do Time na ArrayList times pelo id do time. */
	public int posTimeNaListaDeTimes(int ID) {
		return IntStream.range(0, times.size()).filter(i -> ID == times.get(i).getIdTime()).findFirst().orElse(0);
	}

	/** Busca o id do time na ArrayList times pelo nome. */
	public int idTimePeloNome(String Nome) {
		return times.stream().filter(Item -> Nome.equals(Item.getNome())).findFirst().orElse(null).getIdTime();
	}

	/**
	 * Carregamento pelo usuario dos resultados das partidas que ainda serao
	 * realizadas.
	 */
	public void carregarResultadoPeloUsuario(int[] dadosPartida) {
		int id, gMandante, gVisitante;

		id = dadosPartida[0];
		gMandante = dadosPartida[1];
		gVisitante = dadosPartida[2];

		String nomeMandante = this.tabelaDePartidas.get(id).getTimeMandante();
		String nomeVisitante = this.tabelaDePartidas.get(id).getTimeVisitante();

		int idMandante = 0;
		int idVisitante = 0;

		for (int b = 0; b < this.tabelaDePartidas.size(); b++) {
			if (this.tabelaDePartidas.get(b).getIdPartida() == id) {
				if (this.tabelaDePartidas.get(b).getStatus().equals("PENDENTE")) {
					this.tabelaDePartidas.get(b).setStatus("REALIZADA");
					this.tabelaDePartidas.get(b).setGolsMandante(gMandante);
					this.tabelaDePartidas.get(b).setGolsVisitante(gVisitante);

					for (int a = 0; a < this.times.size(); a++) {
						if (this.times.get(a).getNome().equals(nomeMandante)) {
							idMandante = a;
							this.times.get(idMandante).somaGolsPara(gMandante);
							this.times.get(idMandante).somaGolsContra(gVisitante);
							if (gMandante > gVisitante) {
								this.times.get(idMandante).somaVitorias(a);
							} else if (gMandante < gVisitante) {
								this.times.get(idMandante).somaDerrotas(a);
							} else if (gMandante == gVisitante) {
								this.times.get(idMandante).somaEmpates(a);
							}
						}
					}

					for (int c = 0; c < this.times.size(); c++) {
						if (this.times.get(c).getNome().equals(nomeVisitante)) {
							idVisitante = c;
							this.times.get(idVisitante).somaGolsPara(gVisitante);
							this.times.get(idVisitante).somaGolsContra(gMandante);
							if (gMandante < gVisitante) {
								this.times.get(idVisitante).somaVitorias(c);
							} else if (gMandante > gVisitante) {
								this.times.get(idVisitante).somaDerrotas(c);
							} else if (gMandante == gVisitante) {
								this.times.get(idVisitante).somaEmpates(c);
							}
						}
					}

				} else {
					System.out.println("Nao e permitida a alteracao de partidas que ja ocorreram.");
				}

			}
		}
	}

	/** Carregamento dos marcadores das partidas que ainda serao realizadas. */
	public void carregarMarcadores(List<Integer> idMarcadores) {
		int qtdadeMarcadores = (idMarcadores.size() - 1);

		if (qtdadeMarcadores > 0) {
			int idPartida = idMarcadores.get(0);
			for (int d = 0; d < this.tabelaDePartidas.size(); d++) {

				if (this.tabelaDePartidas.get(d).getIdPartida() == idPartida) {
					for (int a = 1; a <= qtdadeMarcadores; a++) {

						int idMarcador = idMarcadores.get(a);

						for (int b = 0; b < this.times.size(); b++) {
							for (int c = 0; c < this.times.get(b).getJogadores().size(); c++) {
								if (idMarcador == this.times.get(b).getJogadores().get(c).getIdJog()) {
									this.tabelaDePartidas.get(idPartida).getMarcadores()
											.add(this.times.get(b).getJogadores().get(c));
									this.times.get(b).getJogadores().get(c).somaGols();
								}
							}
						}

					}

				}
			}
		}
	}

	/** Busca o id de um time a partir do id de um jogador. */
	public int buscarIdTimePeloIdJogador(int idJog) {
		int idTime = 0;
		for (int a = 0; a < this.times.size(); a++) {
			for (int b = 0; b < this.times.get(a).getJogadores().size(); b++) {
				if (idJog == this.times.get(a).getJogadores().get(b).getIdJog()) {
					idTime = this.times.get(a).getJogadores().get(b).getIdTime();
				}
			}
		}
		return idTime;
	}

	/**
	 * Funcionalidades do programa
	 * 
	 * Retorna todos os dados de todas as partidas. Para cada partida, criou-se uma
	 * String com o �ndice de cada gol e o jogador que o marcou ("Marcadores"). Para
	 * cada partida, criou-se um vetor de objetos em que se discriminou, em cada
	 * posicao, um dado da partida. Criou-se uma ArrayList (Partidas) com os objetos
	 * criados. Retorno: Vetor de vetores, contendo todas as partidas e, dentro
	 * delas, cada dado dessas partidas.
	 * 
	 */

	public Object[][] apresentarPartidas() {
		ArrayList<Object[]> Partidas = new ArrayList<>();
		for (Partida Item : this.tabelaDePartidas) {

			StringBuilder Marcadores = new StringBuilder();
			int Gols = Item.getGolsMandante() + Item.getGolsVisitante();
			if (Gols > 0) {
				if (Item.getMarcadores().isEmpty()) {
					Marcadores.append("Os marcadores ainda nao foram cadastrados!");
				} else {
					for (int i = 0; i < Item.getMarcadores().size(); i++) {
						Jogador Selecionado = Item.getMarcadores().get(i);
						Marcadores.append(String.format("%s - %s  ", i + 1, Selecionado.getNome()));
					}
				}
			} else if (Item.getStatus().equals("REALIZADA")) {
				Marcadores.append("");
			}

			Partidas.add(
					new Object[] { String.valueOf(Item.getIdPartida()), Item.getStatus(), Item.getTimeMandante(),
							Item.getTimeVisitante(), String.format("%s %s x %s %s", Item.getTimeMandante(),
									Item.getGolsMandante(), Item.getGolsVisitante(), Item.getTimeVisitante()),
							Marcadores });
		}
		Object[][] Retorno = new Object[Partidas.size()][];
		for (int i = 0; i < Partidas.size(); i++) {
			Retorno[i] = Partidas.get(i);
		}
		return Retorno;
	}

	/**
	 * Ordenou-se a ArrayList times. Criaram-se vetores com varios objetos contendo
	 * cada dado de cada time, j� em ordem. Esses vetores foram armazenados em uma
	 * ArrayList de objetos (Classificacao). Retorno: Vetor de vetores, contendo
	 * todos os times ja ordenados e, dentro deles, cada dado desses times.
	 * 
	 */

	public Object[][] imprimirCLassificacao() {
		ArrayList<Object[]> Classificacao = new ArrayList<>();
		Collections.sort(this.getTimes(), Comparator.comparingInt(Time::getPontos).thenComparing(Time::getVitorias)
				.thenComparing(Time::getSaldoDeGols).thenComparing(Time::getGolsPara));
		Collections.reverse(this.getTimes());
		for (int i = 0; i < this.times.size(); i++) {

			float percentual = (float) this.times.get(i).getPontos() / ((this.times.get(i).getVitorias()
					+ this.times.get(i).getEmpates() + this.times.get(i).getDerrotas()) * 3) * 100;

			Classificacao.add(new Object[] { String.valueOf(i + 1), this.times.get(i).getNome(),
					String.valueOf(((this.times.get(i).getVitorias() * 3 + this.times.get(i).getEmpates()))),
					String.valueOf(this.times.get(i).getVitorias()), String.valueOf(this.times.get(i).getEmpates()),
					String.valueOf(this.times.get(i).getDerrotas()), String.valueOf(this.times.get(i).getGolsPara()),
					String.valueOf(this.times.get(i).getGolsContra()),
					String.valueOf((this.times.get(i).getGolsPara() - this.times.get(i).getGolsContra())),
					String.format("%.2f", percentual) });

		}
		Object[][] Retorno = new Object[Classificacao.size()][];
		for (int i = 0; i < Classificacao.size(); i++) {
			Retorno[i] = Classificacao.get(i);
		}
		return Retorno;
	}

	/** Retorna uma ArrayList com todos os jogadores cadastrados. */
	public ArrayList<Jogador> getJogadores() {
		ArrayList<Jogador> Jogadores = new ArrayList<>();
		for (Time Item : this.times) {
			Jogadores.addAll(Item.getJogadores());
		}
		return Jogadores;
	}

	/**
	 * Retorna uma ArrayList com todos os jogadores que ja tenham marcado pelo menos
	 * um gol.
	 */
	public ArrayList<Jogador> artilheiros() {
		ArrayList<Jogador> artilheiros = new ArrayList<>();
		for (int b = 0; b < this.times.size(); b++) {
			for (int c = 0; c < this.times.get(b).getJogadores().size(); c++) {

				if (this.times.get(b).getJogadores().get(c).getGols() > 0) {
					artilheiros.add(this.times.get(b).getJogadores().get(c));
				}
			}
		}
		return artilheiros;
	}

	/**
	 * Foi criada e posta em ordem uma Arraylist (JogadoresComGols) com todos os
	 * jogadores que j� tenham marcado gols. Para cada jogador, foi criada uma
	 * Arraylist de objetos contendo cada dado de cada jogador. Retorno: Vetor de
	 * vetores, contendo todos os jogadores ja ordenados e, dentro deles, cada dado
	 * desses jogadores.
	 * 
	 */
	public Object[][] imprimirArtilharia() {
		ArrayList<Jogador> jogadoresComGols = artilheiros();
		ArrayList<Object[]> Artilheiros = new ArrayList<>();
		Collections.sort(jogadoresComGols);
		for (Jogador Item : jogadoresComGols) {
			Artilheiros.add(new Object[] { Item.getNome(), Item.getTime(), Item.getGols() });
		}
		Object[][] Retorno = new Object[Artilheiros.size()][];
		for (int i = 0; i < Artilheiros.size(); i++) {
			Retorno[i] = Artilheiros.get(i);
		}
		return Retorno;
	}

	public Map.Entry<String, Object[][]> tabela_times(String time) {
		/**
		 * O stream funciona como uma esp�cie de for loop que passa por todos os Itens
		 * da ArrayList times. A parte do filter adiciona uma condi��o para a sele��o
		 * (nome do time) e o findfirst limita a primeira ocorrencia.
		 */

		Time timeSelecionado = times.stream().filter(Item -> Item.getNome().equals(time)).findFirst().orElse(null);
		ArrayList<Jogador> jogsDoTime = timeSelecionado.getJogadores();
		ArrayList<Object[]> dadosDeCadaJog = new ArrayList<>();
		for (Jogador Item : jogsDoTime) { /** Cria objetos com dados dos jogadores que interessam ao relatorio. */
			dadosDeCadaJog.add(new Object[] { Item.getNome(), Item.getGols(), Item.getPosicao() });
		}
		Object[][] Retorno = new Object[dadosDeCadaJog.size()][];
		/** Cria um vetor com os vetores anteriores. */
		for (int i = 0; i < dadosDeCadaJog.size(); i++) {
			Retorno[i] = dadosDeCadaJog.get(i);
		}
		/**
		 * retorna o tecnico e os dados dos jogadores em formato chave-valor. A chave e
		 * o nome do tecnico e o valor e o vetor de vetores com os dados dos jogadores.
		 */
		return new AbstractMap.SimpleEntry(timeSelecionado.getTreinador().getNome(), Retorno);
	}

}
