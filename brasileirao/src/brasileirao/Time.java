package brasileirao;

import java.util.ArrayList;

public class Time {
	private static int idGeral = 0;
	private int idTime;
	private String nome;
	private ArrayList<Jogador> jogadores;
	private int vitorias;
	private int empates;
	private int derrotas;
	private int golsPara;
	private int golsContra;
	private int classificacao;

	
	public Time(String nome) {
		this.idTime = Time.idGeral++;
		this.nome = nome;
		this.vitorias = 0;
		this.empates = 0;
		this.derrotas = 0;
		this.golsPara = 0;
		this.golsContra = 0;
		this.classificacao = 0;
		this.jogadores = new ArrayList<>();
	}
	
	public String getNome() {
		return this.nome;
	}
	public int getVitorias() {
		return this.vitorias;
	}
	public int getEmpates() {
		return this.empates;
	}
	public int getDerrotas() {
		return this.derrotas;
	}
	public int getGolsPara() {
		return this.golsPara;
	}
	public int getGolsContra() {
		return this.golsContra;
	}
	public int getIdTime() {
		return this.idTime;
	}
	public int getClassificacao() {
		return this.classificacao;
	}
	
	public void cadastrarJogador(Jogador jogador) {
		this.jogadores.add(jogador);
	}
	
	public void apresentarJogadores() {
		for (int a = 0; a < jogadores.size(); a++) {
			System.out.printf("%d - %s", this.jogadores.get(a).getIdFunc(), this.jogadores.get(a).getNome());
			System.out.println();
		}
	}

}




