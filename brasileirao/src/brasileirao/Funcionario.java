package brasileirao;

public abstract class Funcionario {

	private static int idGeral = 1;
	private int idFunc;
	private int idTime;
	private String nome;
	
	public Funcionario(String nome) {
		this.nome = nome;
		this.idTime = idTime;
		this.idFunc = Funcionario.idGeral++;
	}
	
	public int getIdFunc() {
		return this.idFunc;
	}
	public int getIdTime() {
		return this.idTime;
	}
	public String getNome() {
		return this.nome;
	}
}
