package brasileirao.model;

/**
 * Classe abstrata Funcionario que representa um Jogador ou Tecnico.
 * 
 * @author Leandro Souza da Silva
 * @author Leonardo Passos
 * @since 2022
 * @version 1.1
 */

public abstract class Funcionario {

	private static int idGeral = 0;
	private int idFunc;
	private int idTime;
	private String nome;
	private String time;

	public Funcionario(String nome) {
		this.nome = nome;
		this.idFunc = Funcionario.idGeral++;
	}

	public static int getIdGeral() {
		return idGeral;
	}

	public static void setIdGeral(int idGeral) {
		Funcionario.idGeral = idGeral;
	}

	public int getIdFunc() {
		return idFunc;
	}

	public void setIdFunc(int idFunc) {
		this.idFunc = idFunc;
	}

	public int getIdTime() {
		return idTime;
	}

	public void setIdTime(int idTime) {
		this.idTime = idTime;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTime() {
		return time;
	}

	/**
	 * Defini os 20 times que participam do brasileirao
	 * 
	 */
	public void setTime() {
		if (this.getIdTime() == 0) {
			this.time = "Atletico-MG";
		} else if (this.getIdTime() == 1) {
			this.time = "Fluminense";
		} else if (this.getIdTime() == 2) {
			this.time = "Sao Paulo";
		} else if (this.getIdTime() == 3) {
			this.time = "Palmeiras";
		} else if (this.getIdTime() == 4) {
			this.time = "Botafogo";
		} else if (this.getIdTime() == 5) {
			this.time = "Juventude";
		} else if (this.getIdTime() == 6) {
			this.time = "Fortaleza";
		} else if (this.getIdTime() == 7) {
			this.time = "Atletico-GO";
		} else if (this.getIdTime() == 8) {
			this.time = "Avai";
		} else if (this.getIdTime() == 9) {
			this.time = "Coritiba";
		} else if (this.getIdTime() == 10) {
			this.time = "Internacional";
		} else if (this.getIdTime() == 11) {
			this.time = "Santos";
		} else if (this.getIdTime() == 12) {
			this.time = "Athletico-PR";
		} else if (this.getIdTime() == 13) {
			this.time = "Ceara";
		} else if (this.getIdTime() == 14) {
			this.time = "Corinthians";
		} else if (this.getIdTime() == 15) {
			this.time = "Bragantino";
		} else if (this.getIdTime() == 16) {
			this.time = "Cuiaba";
		} else if (this.getIdTime() == 17) {
			this.time = "Flamengo";
		} else if (this.getIdTime() == 18) {
			this.time = "America-MG";
		} else if (this.getIdTime() == 19) {
			this.time = "Goias";
		}
	}

}
