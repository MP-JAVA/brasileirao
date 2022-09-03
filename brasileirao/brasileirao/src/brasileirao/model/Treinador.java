package brasileirao.model;

public class Treinador extends Funcionario {

	private static int idGeralTrei = 0;
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