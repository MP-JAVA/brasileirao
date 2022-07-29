package brasileirao;

public class Time {
	private static int idGeral;
	private int idTime;
	private String nome;
	private int numJog;
	private int vitorias;
	private int empates;
	private int derrotas;
	private int golsPara;
	private int golsContra;
	private int classificacao;
	
	public Time(int idTime, String nome, int numJog, int vitorias, int empates, int derrotas, int golsPara, int golsContra, int classificacao) {
		this.idTime = Time.idGeral++;
		this.nome = nome;
		this.numJog = numJog;
		this.vitorias = vitorias;
		this.empates = empates;
		this.derrotas = derrotas;
		this.golsPara = golsPara;
		this.golsContra = golsContra;
		this.classificacao = classificacao;
	}
	
	public String getNome() {
		return this.nome;
	}
	public int getNumJog() {
		return this.numJog;
	}
	public int getVitorias() {
		return this.vitorias;
	}
	public int getEmpates() {
		return this.empates;
	}
	public int getDerrotas() {
		return this.derrotas;
	}
	public int getGolsPara() {
		return this.golsPara;
	}
	public int getGolsContra() {
		return this.golsContra;
	}
	public int getIdTime() {
		return this.idTime;
	}
	public int getClassificacao() {
		return this.classificacao;
	}

}




