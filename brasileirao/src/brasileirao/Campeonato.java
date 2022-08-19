package brasileirao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Campeonato {
		private ArrayList<Time> classificacao;
		private ArrayList<Jogador> artilharia;
		private ArrayList<Partida> tabelaDePartidas;
		private ArrayList<Time> times;
		private ArrayList<Treinador> treinadores;
		private ArrayList<Jogador> jogadores;

		public Campeonato() {
			this.classificacao = new ArrayList<>();
			this.artilharia = new ArrayList<>();
			this.tabelaDePartidas = new ArrayList<>();
			this.times = new ArrayList<>();
			this.treinadores = new ArrayList<>();
			this.jogadores = new ArrayList<>();
		}
		
		
		
		
		public ArrayList<Time> getClassificacao() {
			return classificacao;
		}

		public void setClassificacao(ArrayList<Time> classificacao) {
			this.classificacao = classificacao;
		}

		public ArrayList<Jogador> getArtilharia() {
			return artilharia;
		}

		public void setArtilharia(ArrayList<Jogador> artilharia) {
			this.artilharia = artilharia;
		}

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

		public ArrayList<Treinador> getTreinadores() {
			return treinadores;
		}

		public void setTreinadores(ArrayList<Treinador> treinadores) {
			this.treinadores = treinadores;
		}

		public ArrayList<Jogador> getJogadores() {
			return jogadores;
		}

		public void setJogadores(ArrayList<Jogador> jogadores) {
			this.jogadores = jogadores;
		}

		private void cadastrarTime(String nome) {
			Time time = new Time(nome);
			this.times.add(time);
		}
		
		
		public void cadastrarJogador(String nome, String posicao, String id) {
			Jogador jogador = new Jogador(nome, posicao);
			this.jogadores.add(jogador);
			int idTime = Integer.parseInt(id);
			this.times.get(idTime).cadastrarJogador(jogador);
		}
		
		private void cadastrarPartida(String timeMandante, String timeVisitante) {
			Partida partida = new Partida(timeMandante, timeVisitante);
			this.tabelaDePartidas.add(partida);
		}
		
		
		public int[] digitarResultado() {
			int id, gMandante, gVisitante;
			System.out.printf("Digite o id. da partida: ");
			Scanner ler = new Scanner(System.in);
			id = ler.nextInt();
			
			System.out.printf("Digite o número de gols do mandante: ");
			Scanner ler1 = new Scanner(System.in);
			gMandante = ler.nextInt();

			System.out.printf("Digite o número de gols do visitante: ");
			Scanner ler2 = new Scanner(System.in);
			gVisitante = ler.nextInt();
			
			return new int[] {id, gMandante, gVisitante};
		}
		
		public void carregarResultado(int[] dadosPartida) {
			int id, gMandante, gVisitante;
			
			id = dadosPartida[0];
			gMandante = dadosPartida[1];
			gVisitante = dadosPartida[2];
			
			String nomeMandante = this.tabelaDePartidas.get(id).getTimeMandante();
			String nomeVisitante = this.tabelaDePartidas.get(id).getTimeVisitante();
			
			int idMandante = 0;
			int idVisitante = 0;

			for (int b = 0; b < this.tabelaDePartidas.size(); b++) {
				if(this.tabelaDePartidas.get(b).getIdPartida() == id) {
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
		
		public void cadastrarResultado() {
			carregarResultado(digitarResultado());
		}
		
		public ArrayList<Integer> digitarMarcadores() {
			System.out.println("Digite o id. da partida: ");
			Scanner ler1 = new Scanner(System.in);
			int idPartida = ler1.nextInt();
			ArrayList<Integer> idMarcadores = new ArrayList<Integer>();
			idMarcadores.add(idPartida);
			int gols = (this.tabelaDePartidas.get(idPartida).getGolsMandante() + this.tabelaDePartidas.get(idPartida).getGolsVisitante());
			for (int a = 0; a < gols; a++) {
				System.out.printf("Digite o id. do jogador que marcou o gol número %d: ", a+1);
				Scanner ler = new Scanner(System.in);
				int idMarcador = ler.nextInt();
				idMarcadores.add(idMarcador);
			}
			return idMarcadores;
		}
		
		public void carregarMarcadores(ArrayList<Integer> idMarcadores) {
			int qtdadeMarcadores = (idMarcadores.size() - 1);
			
			if (qtdadeMarcadores > 0) {
				int idPartida = idMarcadores.get(0);
				
				for (int b = 0; b < this.tabelaDePartidas.size(); b++) {
					
					if(this.tabelaDePartidas.get(b).getIdPartida() == idPartida) {
						for (int a = 1; a <= qtdadeMarcadores; a++) {				
							this.tabelaDePartidas.get(idPartida).getMarcadores().add(this.jogadores.get(idMarcadores.get(a)));
							this.jogadores.get(idMarcadores.get(a)).somaGols();
							
						}
						
					}
				}
			}
		}
		
		public void cadastrarMarcadores() {
			carregarMarcadores(digitarMarcadores());
		}
		
	/*	public void cadastroCompletoDePartida() {
			int [] dadosPartida = digitarResultado();
			carregarResultado(dadosPartida);
			carregarMarcadores(digitarMarcadores(dadosPartida[0]));
		} */
		//O método está pronto. Só falta eu conseguir fazer com que digitarMarcadores possa receber um parâmetro
		//opcional, que seria o idPartida. Se não for informado, lemos dentro do método.
		

		public void carregarDadosElencos(String caminho) {
			File arquivo = new File(caminho);
			if (arquivo.exists()) {
				if (arquivo.isFile() && (arquivo.length()>0)) {
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
							}
							this.cadastrarJogador(dados[6], dados[5], dados[0]);
							
							contador++;
							linha = leitor.readLine();
						}
						
					} catch(IOException ioex) {		
					}
				}
			}	
		}
		
		public void carregarPartidas(String caminho) {
			File arquivo = new File(caminho);
			if (arquivo.exists()) {
				if (arquivo.isFile() && (arquivo.length()>0)) {
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
							int [] dadosPartida = {Integer.parseInt(dados[0]), Integer.parseInt(dados[2]), Integer.parseInt(dados[3])};
							carregarResultado(dadosPartida);
							contador++;
							linha = leitor.readLine();
						}
						
					} catch(IOException ioex) {		
					}
				}
			}			}
		
		
		public void carregarDados(String caminhoElenco, String caminhoPartidas) {
			this.carregarDadosElencos(caminhoElenco);
			this.carregarPartidas(caminhoPartidas);
		}
		
		
		public void apresentarTime() {
			for (int b = 0; b < this.times.size(); b++) {
				System.out.printf("%d - %s - %d vitórias", this.times.get(b).getIdTime(), this.times.get(b).getNome(), this.times.get(b).getVitorias());
				System.out.println();
				//this.times.get(b).apresentarJogadores();
				System.out.println();
			}
		}
		
		public void apresentarPartidas() {
			for (int b = 0; b < this.tabelaDePartidas.size(); b++) {
				System.out.printf("%d - %s  -  %s %d x %d %s", 
				this.tabelaDePartidas.get(b).getIdPartida(), this.tabelaDePartidas.get(b).getStatus(), 
				this.tabelaDePartidas.get(b).getTimeMandante(), this.tabelaDePartidas.get(b).getGolsMandante(), 
				this.tabelaDePartidas.get(b).getGolsVisitante(), this.tabelaDePartidas.get(b).getTimeVisitante());
				System.out.println();
				
				int qtdadeGols = (this.tabelaDePartidas.get(b).getGolsMandante() + this.tabelaDePartidas.get(b).getGolsVisitante());
				if (qtdadeGols > 0) {
					if (this.tabelaDePartidas.get(b).getMarcadores().isEmpty()) {
						System.out.println("Os marcadores ainda não foram cadastrados!");
					} else {
						System.out.println("Os gols foram marcados pelos seguintes jogadores:");
						for (int a = 0; a < qtdadeGols; a++ ) {
							System.out.println((a+1) + " - " + this.tabelaDePartidas.get(b).getMarcadores().get(a).getNome());	
						}
					}
				} else if (this.tabelaDePartidas.get(b).getStatus().equals("REALIZADA")){
					System.out.println("Não foram marcados gols na partida!");
				}
			}
		}
		
		
		public void ordenarCLassificacao() {

			
			Collections.sort(this.getTimes(), new ComparatorClassificacao());

			
			for (int a = 0; a < this.times.size(); a++) {
				System.out.printf("%s - %d - %d  - %d - %d - %d - %d - %d", times.get(a).getNome(), 
				times.get(a).getPontos(), times.get(a).getVitorias(), times.get(a).getEmpates(), 
				times.get(a).getDerrotas(), times.get(a).getGolsPara(), times.get(a).getGolsContra(), 
				(times.get(a).getGolsPara() - times.get(a).getGolsContra()));
				System.out.println();
			}
			
		}
		
		public void ordenarArtilharia() {

			
			Collections.sort(this.getJogadores());

			
			for (int a = 0; a < this.jogadores.size(); a++) {
				System.out.printf("%s - %d ", jogadores.get(a).getNome(), jogadores.get(a).getGols());
				System.out.println();
			}
			
		}
		
}
