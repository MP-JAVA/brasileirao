package brasileirao;

public class Jogador extends Funcionario{
	private String posicao;
	private int gols;
	
	public Jogador(String nome, int idTime, String posicao, int gols) {
		super(nome, idTime);
		this.posicao = posicao;
		this.gols = gols;
	}
	
	public int getIdFunc() {
		return super.getIdFunc();
	}
	public int getIdTime() {
		return super.getIdTime();
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
