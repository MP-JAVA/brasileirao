package brasileirao;

public abstract class Funcionario {

	private static int idGeral = 0;
	private int idFunc;
	private int idTime;
	private String nome;
	
	public Funcionario(String nome) {
		this.nome = nome;
		this.idTime = idTime;
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
	

}
