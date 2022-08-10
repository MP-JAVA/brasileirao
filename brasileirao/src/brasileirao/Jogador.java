package brasileirao;

public class Jogador extends Funcionario{
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
		return this.posicao;
	}
	public int getGols() {
		return this.gols;
	}
}
