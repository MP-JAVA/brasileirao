package brasileirao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
			this.times.get(idTime - 1).cadastrarJogador(jogador);
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
			
			
			for (int b = 0; b < this.tabelaDePartidas.size(); b++) {
				if(this.tabelaDePartidas.get(b).getIdPartida() == id) {
					this.tabelaDePartidas.get(b).setGolsMandante(gMandante);
					this.tabelaDePartidas.get(b).setGolsVisitante(gVisitante);
				}
			}
		}
		
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
						while (linha != null) {
							String dados[] = linha.split(";");
							this.cadastrarPartida(dados[1], dados[4]);
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
				System.out.printf("%d - %s", this.times.get(b).getIdTime(), this.times.get(b).getNome());
				System.out.println();
				this.times.get(b).apresentarJogadores();
				System.out.println();
			}
		}
		
		public void apresentarPartidas() {
			for (int b = 0; b < this.tabelaDePartidas.size(); b++) {
				System.out.printf("%d - %s %d x %d %s", this.tabelaDePartidas.get(b).getIdPartida(), this.tabelaDePartidas.get(b).getTimeMandante(), this.tabelaDePartidas.get(b).getGolsMandante(), this.tabelaDePartidas.get(b).getGolsVisitante(), this.tabelaDePartidas.get(b).getTimeVisitante());
				System.out.println();
			}
		}
}
