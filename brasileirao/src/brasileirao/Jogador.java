package brasileirao;

public class Jogador extends Funcionario implements Comparable<Jogador>{
	private String posicao;
	private int gols;
	private String time;
	
	public Jogador(String nome, String posicao) {
		super(nome);
		this.posicao = posicao;
		this.gols = 0;
		this.time = time;
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

	public void setTime() {
		if (this.getIdTime() == 0) {
			this.time = "Atlético-MG";
		} else if (this.getIdTime() == 1) {
			this.time = "Fluminense";
		} else if (this.getIdTime() == 2) {
			this.time = "São Paulo";
		} else if (this.getIdTime() == 3) {
			this.time = "Palmeiras";
		} else if (this.getIdTime() == 4) {
			this.time = "Botafogo";
		} else if (this.getIdTime() == 5) {
			this.time = "Juventude";
		} else if (this.getIdTime() == 6) {
			this.time = "Fortaleza";
		} else if (this.getIdTime() == 7) {
			this.time = "Atlético-GO";
		} else if (this.getIdTime() == 8) {
			this.time = "Avaí";
		} else if (this.getIdTime() == 9) {
			this.time = "Coritiba";
		} else if (this.getIdTime() == 10) {
			this.time = "Internacional";
		} else if (this.getIdTime() == 11) {
			this.time = "Santos";
		} else if (this.getIdTime() == 12) {
			this.time = "Athletico-PR";
		} else if (this.getIdTime() == 13) {
			this.time = "Ceará";
		} else if (this.getIdTime() == 14) {
			this.time = "Corinthians";
		} else if (this.getIdTime() == 15) {
			this.time = "Bragantino";
		} else if (this.getIdTime() == 16) {
			this.time = "Cuiabá";
		} else if (this.getIdTime() == 17) {
			this.time = "Flamengo";
		} else if (this.getIdTime() == 18) {
			this.time = "América-MG";
		} else if (this.getIdTime() == 19) {
			this.time = "Goiás";
		}
	}
	

}
