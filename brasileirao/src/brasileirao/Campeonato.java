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
		
		/* private void cadastrarTime(String nome) {
			Time time = new Time(nome);
			this.times.add(time);
			for (int a = (time.getIdTime()*11); a < ((time.getIdTime()+1)*11); a++) {
				time.cadastrarJogador(this.jogadores.get(a));
			}
		} */ //forma antiga do método (apagar se achar desnecessário voltar atrás)
		
		private void cadastrarTime(String nome) {
			Time time = new Time(nome);
			this.times.add(time);
		}
		
		/* public void cadastrarJogador(String nome, String posicao) {
			Jogador jogador = new Jogador(nome, posicao);
			this.jogadores.add(jogador);
		} */ //forma antiga do método (apagar se achar desnecessário voltar atrás)
		
		public void cadastrarJogador(String nome, String posicao, String id) {
			Jogador jogador = new Jogador(nome, posicao);
			this.jogadores.add(jogador);
			int idTime = Integer.parseInt(id);
			this.times.get(idTime - 1).cadastrarJogador(jogador);
		}
		
		public void apresentarTime() {
			for (int b = 0; b < this.times.size(); b++) {
				System.out.println(this.times.get(b).getNome());
				this.times.get(b).apresentarJogadores();
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
							System.out.println(dados[1]);
							//this.cadastrarPartida(); //Elaborar esse método e depois passar os parâmetros aqui.
							//linha = leitor.readLine(); //Apagar esse print de teste
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
