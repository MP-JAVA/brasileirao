package brasileirao;

import java.util.ArrayList;

public class Partida {
	
	private static int idGeral;
	private int idPartida;
	private String timeMandante;
	private String timeVisitante;
	private int golsMandante;
	private int golsVisitante;
	private ArrayList<Jogador> marcadores;


	public Partida(String timeMandante, String timeVisitante) {
		this.idPartida = Partida.idGeral++;
		this.timeMandante = timeMandante;
		this.timeVisitante = timeVisitante;
		this.golsMandante = golsMandante;
		this.golsVisitante = golsVisitante;
		this.marcadores = new ArrayList<>();
	}
	
	public int getIdPartida() {
		return this.idPartida;
	}
	public String getTimeMandante() {
		return this.timeMandante;
	}
	public String getTimeVisitante() {
		return this.timeVisitante;
	}
	public int getGolsMandante() {
		return this.golsMandante;
	}
	public int getGolsVisitante() {
		return this.golsVisitante;
	}
	public void cadastrarMarcador(Jogador jogador, int gols) { //Temos que desenvolver uma lógica para o retorno.
		for (int a = 0; a < gols; a++) {
			this.marcadores.add(jogador);
		}
	}
}

