package brasileirao;

public class Treinador extends Funcionario{
	
	//Criar atributo que justifique essa heran�a.

	
	public Treinador(String nome) {
		super(nome);
	}
	
	public int getIdFunc() {
		return super.getIdFunc();
	}
	public String getNome() {
		return super.getNome();
	}
}