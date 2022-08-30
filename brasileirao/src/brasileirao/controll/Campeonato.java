package brasileirao.controll;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import brasileirao.model.Jogador;
import brasileirao.model.Partida;
import brasileirao.model.Time;
import brasileirao.model.Treinador;

public class Campeonato {
	private ArrayList<Partida> tabelaDePartidas;
	private ArrayList<Time> times;

	public Campeonato() {
		this.tabelaDePartidas = new ArrayList<>();
		this.times = new ArrayList<>();
	}

// Getters and Setters ----------------------------------------------------------------------------------------
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

// Carregamento de dados dos arquivos --------------------------------------------------------------------------

	// Instancia todos times e jogadores envolvidos no campeonato a partir de dados
	// contidos em um arquivo CSV.
	public void carregarDadosElencos(String caminho) {
		File arquivo = new File(caminho);
		if (arquivo.exists()) {
			if (arquivo.isFile() && (arquivo.length() > 0)) {
				BufferedReader leitor = null;
				try {
					leitor = new BufferedReader(new FileReader(caminho));
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

	// Instancia todas as partidas do campeonato a partir de um arquivo CSV.
	public void carregarPartidas(String caminho) {
		File arquivo = new File(caminho);
		if (arquivo.exists()) {
			if (arquivo.isFile() && (arquivo.length() > 0)) {
				BufferedReader leitor = null;
				try {
					leitor = new BufferedReader(new FileReader(caminho));
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

	public void carregarDados(String caminhoElenco, String caminhoPartidas) {
		this.carregarDadosElencos(caminhoElenco);
		this.carregarPartidas(caminhoPartidas);
	}

// métodos de organização do programa --------------------------------------------------------------------------

	// Instancia um Time e o insere na ArrayList do campeonato.
	private void cadastrarTime(String nome) {
		Time time = new Time(nome);
		this.times.add(time);
	}

	// Instancia um jogador.
	public void cadastrarJogador(String nome, String posicao, String id) {
		Jogador jogador = new Jogador(nome, posicao);
		int idTime = Integer.parseInt(id);
		jogador.setIdTime(idTime);
		jogador.setTime();

		this.times.get(idTime).cadastrarJogador(jogador);
	}

	// Instancia um treinador.
	public void cadastrarTreinador(String nome, String id) {
		Treinador treinador = new Treinador(nome);
		int idTime = Integer.parseInt(id);
		treinador.setIdTime(idTime);
		treinador.setTime();

		this.times.get(idTime).setTreinador(treinador);
	}

	// Instancia uma partida e a inclui na ArrayList tabelaDePartidas
	private void cadastrarPartida(String timeMandante, String timeVisitante) {
		Partida partida = new Partida(timeMandante, timeVisitante);
		this.tabelaDePartidas.add(partida);
	}

	// Carrega todos os resultados constantes de um arquivo CSV relacionados às
	// partidas já realizadas.
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

	// Permite que o usuário carregue os resultados relacionados às partidas que
	// ainda serão realizadas.
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
					System.out.println("Não é permitida a alteração de partidas que já ocorreram.");
				}

			}
		}
	}

	// Permite que o usuário carregue os marcadores das partidas que ainda serão
	// realizadas.
	public void carregarMarcadores(ArrayList<Integer> idMarcadores) {
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

	// Busca o id de um time a partir do id de um jogador.
	public int buscarIdTime(int idJog) {
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

//Funcionalidades do programa

	// Apresenta o id/nome do time e id/nome/gols do treinador e de cada um dos seus
	// jogadores.
	public void apresentarTime(int idTime) {
		System.out.printf("Time: %-15s (id: %d)", this.times.get(idTime).getNome(), idTime);
		System.out.println();
		System.out.printf("Treinador: %-15s (id: %d)", this.times.get(idTime).getTreinador().getNome(),
				this.times.get(idTime).getTreinador().getIdTrei());
		System.out.println();
		this.times.get(idTime).apresentarJogadores();
		System.out.println();
	}

	// Imprime os dados de todas as partidas por meio de uma sequência de printf e
	// println
	public void apresentarPartidas() {
		for (int b = 0; b < this.tabelaDePartidas.size(); b++) {
			System.out.printf("%3d - %-10s %-13s %2d x %2d %13s", this.tabelaDePartidas.get(b).getIdPartida(),
					this.tabelaDePartidas.get(b).getStatus(), this.tabelaDePartidas.get(b).getTimeMandante(),
					this.tabelaDePartidas.get(b).getGolsMandante(), this.tabelaDePartidas.get(b).getGolsVisitante(),
					this.tabelaDePartidas.get(b).getTimeVisitante());

			int qtdadeGols = (this.tabelaDePartidas.get(b).getGolsMandante()
					+ this.tabelaDePartidas.get(b).getGolsVisitante());
			if (qtdadeGols > 0) {
				if (this.tabelaDePartidas.get(b).getMarcadores().isEmpty()) {
					System.out.printf("%45s", "Os marcadores ainda não foram cadastrados!");

				} else {
					System.out.printf("%10s", "Gols: ");
					for (int a = 0; a < qtdadeGols; a++) {
						System.out.printf("%s. ", this.tabelaDePartidas.get(b).getMarcadores().get(a).getNome());
					}

				}
			} else if (this.tabelaDePartidas.get(b).getStatus().equals("REALIZADA")) {
				System.out.printf("%33s", "Gols: -----------------------");

			}
			System.out.println();
		}
	}

	// Ordena a ArrayList de Times de acordo com o desempenho e
	// imprime a classificação por meio de uma sequência de printf e println
	public void imprimirCLassificacao() {

		Collections.sort(this.getTimes(), new ComparatorClassificacao());

		System.out.printf("%s %15s %5s %5s %5s %5s %5s %5s %5s %5s %s", "|", "---------------", "-----", "-----",
				"-----", "-----", "-----", "-----", "-----", "-------", "|");
		System.out.println();
		System.out.printf("%s %-15s %5s %5s %5s %5s %5s %5s %5s %5s %3s", "|", "Time", "P", "V", "E", "D", "GP", "GC",
				"SG", "%", "|");
		System.out.println();
		System.out.printf("%s %15s %5s %5s %5s %5s %5s %5s %5s %5s %s", "|", "---------------", "-----", "-----",
				"-----", "-----", "-----", "-----", "-----", "-------", "|");
		System.out.println();
		for (int a = 0; a < this.times.size(); a++) {

			float percentual = (float) times.get(a).getPontos()
					/ ((times.get(a).getVitorias() + times.get(a).getEmpates() + times.get(a).getDerrotas()) * 3) * 100;

			System.out.printf("%s %-15s %5d %5d %5d %5d %5d %5d %5d %7.1f %s", "|", times.get(a).getNome(),
					times.get(a).getPontos(), times.get(a).getVitorias(), times.get(a).getEmpates(),
					times.get(a).getDerrotas(), times.get(a).getGolsPara(), times.get(a).getGolsContra(),
					(times.get(a).getGolsPara() - times.get(a).getGolsContra()), percentual, "|");
			System.out.println();
		}
		System.out.printf("%s %15s %5s %5s %5s %5s %5s %5s %5s %5s %s", "|", "---------------", "-----", "-----",
				"-----", "-----", "-----", "-----", "-----", "-------", "|");
		System.out.println();

	}

	// Retorna uma ArrayList de string em que cada elemento corresponde a um time e
	// o seu desempenho.
	// A ideia é usar cada um desses elementos para imprimir a classificação na
	// interface.
	public ArrayList<String> imprimirCLassificacaoInterface() {

		Collections.sort(this.getTimes(), new ComparatorClassificacao());

		String resposta = "";
		ArrayList<String> dadosTimes = new ArrayList<String>();

		for (int a = 0; a < this.times.size(); a++) {

			float percentual = (float) times.get(a).getPontos()
					/ ((times.get(a).getVitorias() + times.get(a).getEmpates() + times.get(a).getDerrotas()) * 3) * 100;

			resposta = String.format("%-15s %5d %5d %5d %5d %5d %5d %5d %7.1f \n", times.get(a).getNome(),
					times.get(a).getPontos(), times.get(a).getVitorias(), times.get(a).getEmpates(),
					times.get(a).getDerrotas(), times.get(a).getGolsPara(), times.get(a).getGolsContra(),
					(times.get(a).getGolsPara() - times.get(a).getGolsContra()), percentual);
			dadosTimes.add(resposta);

		}

		return dadosTimes;

	}

	// Ordena os jogadores pelos gols feitos e imprime a artilharia por meio de uma
	// sequência de printf e println
	public void imprimirArtilharia() {

		ArrayList<Jogador> artilheiros = new ArrayList<Jogador>();

		for (int b = 0; b < this.times.size(); b++) {
			for (int c = 0; c < this.times.get(b).getJogadores().size(); c++) {

				if (this.times.get(b).getJogadores().get(c).getGols() > 0) {
					artilheiros.add(this.times.get(b).getJogadores().get(c));
				}
			}
		}

		Collections.sort(artilheiros);

		System.out.printf("%s %-20s %-20s %5s %s ", "|", "--------------------", "--------------------", "-----", "|");
		System.out.println();
		System.out.printf("%s %-20s %-20s %5s %s ", "|", "Jogador", "Time", "Gols", "|");
		System.out.println();
		System.out.printf("%s %-20s %-20s %5s %s ", "|", "--------------------", "--------------------", "-----", "|");
		System.out.println();

		for (int d = 0; d < artilheiros.size(); d++) {
			System.out.printf("%s %-20s %-20s %5d %s", "|", artilheiros.get(d).getNome(), artilheiros.get(d).getTime(),
					artilheiros.get(d).getGols(), "|");
			System.out.println();
		}

	}

}
