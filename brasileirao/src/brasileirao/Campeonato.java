package brasileirao;

import java.util.ArrayList;

public class Campeonato {
		private ArrayList<Time> classificacao;
		private ArrayList<Jogador> artilharia;
		private ArrayList<Partida> tabelaDePartidas;
		private ArrayList<Time> times;
		private ArrayList<Treinador> treinadores;
		private ArrayList<Jogador> jogadores;

		public Campeonato(ArrayList<Time> classificacao, 
				ArrayList<Jogador> artilharia, 
				ArrayList<Partida> tabelaDePartidas, 
				ArrayList<Time> times, 
				ArrayList<Treinador> treinadores, 
				ArrayList<Jogador> jogadores) {
			this.classificacao = classificacao;
			this.artilharia = artilharia;
			this.tabelaDePartidas = tabelaDePartidas;
			this.times = times;
			this.treinadores = treinadores;
			this.jogadores = jogadores;
		}
		
		public ArrayList<Time> getClassificacao() {
			return classificacao; 
		}
		
		public ArrayList<Jogador> getArtilharia(){
			return artilharia;
		}
		
		public ArrayList<Partida> getTabelaDePartidas() {
			return tabelaDePartidas;
		}
		
		public ArrayList<Time> getTimes(){
			return times;
		}
		
		public ArrayList<Treinador> getTreinadores() {
			return treinadores;
		}
		
		public ArrayList<Jogador> getJogadores() {
			return jogadores;
			
		}
}
