package brasileirao;

public class Jogador extends Funcionario implements Comparable<Jogador>{
	private String posicao;
	private int gols;
	private String time;
	
	public Jogador(String nome, String posicao) {
		super(nome);
		this.posicao = posicao;
		this.gols = 0;
		this.time = setTime();
	}
	
	public int getIdFunc() {
		return super.getIdFunc();
	}
	public String getNome() {
		return super.getNome();
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public int getGols() {
		return gols;
	}

	public void somaGols() {
		this.gols++;
	}

	@Override
	public int compareTo(Jogador a) {
		return (a.getGols() - this.gols);
	}

	public String getTime() {
		return time;
	}

	public String setTime() {
		if (this.getIdTime() == 0) {
			return "Atlético-MG";
		} else if (this.getIdTime() == 1) {
			return "Fluminense";
		} else if (this.getIdTime() == 2) {
			return "São Paulo";
		} else if (this.getIdTime() == 3) {
			return "Palmeiras";
		} else if (this.getIdTime() == 4) {
			return "Botafogo";
		} else if (this.getIdTime() == 5) {
			return "Juventude";
		} else if (this.getIdTime() == 6) {
			return "Fortaleza";
		} else if (this.getIdTime() == 7) {
			return "Atlético-GO";
		} else if (this.getIdTime() == 8) {
			return "Avaí";
		} else if (this.getIdTime() == 9) {
			return "Coritiba";
		} else if (this.getIdTime() == 10) {
			return "Internacional";
		} else if (this.getIdTime() == 11) {
			return "Santos";
		} else if (this.getIdTime() == 12) {
			return "Athletico-PR";
		} else if (this.getIdTime() == 13) {
			return "Ceará";
		} else if (this.getIdTime() == 14) {
			return "Corinthians";
		} else if (this.getIdTime() == 15) {
			return "Bragantino";
		} else if (this.getIdTime() == 16) {
			return "Cuiabá";
		} else if (this.getIdTime() == 17) {
			return "Flamengo";
		} else if (this.getIdTime() == 18) {
			return "América-MG";
		} else if (this.getIdTime() == 19) {
			return "Goiás";
		} else {
			return "Erro";
		}
	}
	

}
