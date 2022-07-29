package brasileirao;

public class Treinador extends Funcionario{
	
	//Criar atributo que justifique essa herança.

	
	public Treinador(String nome, int idTime) {
		super(nome, idTime);
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
}