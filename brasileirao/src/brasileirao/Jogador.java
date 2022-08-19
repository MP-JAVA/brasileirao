package brasileirao;

public class Jogador extends Funcionario implements Comparable<Jogador>{
	private String posicao;
	private int gols;
	
	public Jogador(String nome, String posicao) {
		super(nome);
		this.posicao = posicao;
		this.gols = 0;
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
	

}
