package brasileirao.model;

/**
 * Classe Treinador que herda de Funcionario.
 * 
 * @author Leandro Souza da Silva
 * @author Leonardo Passos
 * @since 2022
 * @version 1.1
 */

public class Treinador extends Funcionario {

	public static int idGeralTrei = 0;
	private int idTrei;

	public Treinador(String nome) {
		super(nome);
		this.setIdTrei(Treinador.idGeralTrei++);
	}

	public int getIdTrei() {
		return idTrei;
	}

	public void setIdTrei(int idTrei) {
		this.idTrei = idTrei;
	}

	@Override
	public int getIdTime() {
		return super.getIdTime();
	}

	@Override
	public String getNome() {
		return super.getNome();
	}
}