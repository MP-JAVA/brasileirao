package brasileirao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
		
		public void apresentarTime() {
			for (int b = 0; b < this.times.size(); b++) {
				System.out.printf("%d - %s", this.times.get(b).getIdTime(), this.times.get(b).getNome());
				System.out.println();
				this.times.get(b).apresentarJogadores();
				System.out.println();
			}
		}
		
		public void apresentarPartida() {
			for (int b = 0; b < this.tabelaDePartidas.size(); b++) {
				System.out.printf("%d - %s x %s", this.tabelaDePartidas.get(b).getIdPartida() + 1, this.tabelaDePartidas.get(b).getTimeMandante(), this.tabelaDePartidas.get(b).getTimeVisitante());
				System.out.println();
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
		
}
