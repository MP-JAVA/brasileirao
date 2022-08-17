package brasileirao;

import java.util.ArrayList;

public class Partida {
	
	private static int idGeral = 1;
	private int idPartida;
	private String status;
	private String timeMandante;
	private String timeVisitante;
	private int golsMandante;
	private int golsVisitante;
	private ArrayList<Jogador> marcadores;


	public Partida(String timeMandante, String timeVisitante) {
		this.idPartida = Partida.idGeral++;
		this.status = "PENDENTE";
		this.timeMandante = timeMandante;
		this.timeVisitante = timeVisitante;
		this.golsMandante = golsMandante;
		this.golsVisitante = golsVisitante;
		this.marcadores = new ArrayList<>();
	}
	
	public static int getIdGeral() {
		return idGeral;
	}

	public static void setIdGeral(int idGeral) {
		Partida.idGeral = idGeral;
	}

	public int getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimeMandante() {
		return timeMandante;
	}

	public void setTimeMandante(String timeMandante) {
		this.timeMandante = timeMandante;
	}

	public String getTimeVisitante() {
		return timeVisitante;
	}

	public void setTimeVisitante(String timeVisitante) {
		this.timeVisitante = timeVisitante;
	}

	public int getGolsMandante() {
		return golsMandante;
	}

	public void setGolsMandante(int golsMandante) {
		this.golsMandante = golsMandante;
	}

	public int getGolsVisitante() {
		return golsVisitante;
	}

	public void setGolsVisitante(int golsVisitante) {
		this.golsVisitante = golsVisitante;
	}

	public ArrayList<Jogador> getMarcadores() {
		return marcadores;
	}

	public void setMarcadores(ArrayList<Jogador> marcadores) {
		this.marcadores = marcadores;
	}

	public void cadastrarMarcador(Jogador jogador, int gols) { //Temos que desenvolver uma l�gica para o retorno.
		for (int a = 0; a < gols; a++) {
			this.marcadores.add(jogador);
		}
	}
}

