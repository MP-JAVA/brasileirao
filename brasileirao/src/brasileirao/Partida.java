package brasileirao;

import java.util.ArrayList;

public class Partida {
	
	private static int idGeral;
	private int idPartida;
	private Time timeMandante;
	private Time timeVisitante;
	private int[] placar;
	private ArrayList<Jogador> marcadores;


	public Partida(Time timeMandante, Time timeVisitante) {
		this.idPartida = Partida.idGeral++;
		this.timeMandante = timeMandante;
		this.timeVisitante = timeVisitante;
		this.placar = new int[2];
		this.marcadores = new ArrayList<>();
	}
	
	public int getIdPartida() {
		return this.idPartida;
	}
	public Time getTimeMandante() {
		return this.timeMandante;
	}
	public Time getTimeVisitante() {
		return this.timeVisitante;
	}
	public int getGolsMandante() {
		return this.placar[0];
	}
	public int getGolsVisitante() {
		return this.placar[1];
	}
	public void cadastrarMarcador(Jogador jogador, int gols) { //Temos que desenvolver uma lógica para o retorno.
		for (int a = 0; a < gols; a++) {
			this.marcadores.add(jogador);
		}
	}
}

