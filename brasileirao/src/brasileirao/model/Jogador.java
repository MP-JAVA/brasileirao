package brasileirao.model;

/**
 * Classe Jogador que herda de funcionario.
 * 
 * @author Leandro Souza da Silva
 * @author Leonardo Passos
 * @since 2022
 * @version 1.1
 */

/**
 * Comparable é uma interface que tem um metodo de comparacao, nesse caso vai
 * comparar a quantidade de gols de cada jogador.
 *
 */

public class Jogador extends Funcionario implements Comparable<Jogador> {
	public static int idGeralJog = 0;
	private int idJog;
	private String posicao;
	private int gols;

	public Jogador(String nome, String posicao) {
		super(nome);
		this.posicao = posicao;
		this.gols = 0;
		this.setIdJog(Jogador.idGeralJog++);
	}

	public int getIdJog() {
		return idJog;
	}

	public void setIdJog(int idJog) {
		this.idJog = idJog;
	}

	@Override
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
