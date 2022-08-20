package brasileirao;

import java.util.ArrayList;

public class Time {
	private static int idGeral = 0;
	private int idTime;
	private String nome;
	private ArrayList<Jogador> jogadores;
	private int pontos;	
	private int vitorias;
	private int empates;
	private int derrotas;
	private int golsPara;
	private int golsContra;
	private int classificacao;

	
	public Time(String nome) {
		this.idTime = Time.idGeral++;
		this.nome = nome;
		this.pontos = 0;
		this.vitorias = 0;
		this.empates = 0;
		this.derrotas = 0;
		this.golsPara = 0;
		this.golsContra = 0;
		this.classificacao = 0;
		this.jogadores = new ArrayList<>();
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



	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}



	public void setJogadores(ArrayList<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public int getPontos() {
		return pontos;
	}



	public void setPontos(int pontos) {
		this.pontos = pontos;
	}


	public int getVitorias() {
		return vitorias;
	}



	public void somaVitorias(int vitorias) {
		this.vitorias++;
		this.pontos = (this.pontos + 3);
	}



	public int getEmpates() {
		return empates;
	}



	public void somaEmpates(int empates) {
		this.empates++;
		this.pontos = (this.pontos +1);
	}



	public int getDerrotas() {
		return derrotas;
	}



	public void somaDerrotas(int derrotas) {
		this.derrotas++;
	}



	public int getGolsPara() {
		return golsPara;
	}



	public void somaGolsPara(int golsPara) {
		this.golsPara += golsPara;
	}



	public int getGolsContra() {
		return golsContra;
	}



	public void somaGolsContra(int golsContra) {
		this.golsContra += golsContra;
	}



	public int getClassificacao() {
		return classificacao;
	}



	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}



	public void cadastrarJogador(Jogador jogador) {
		this.jogadores.add(jogador);
	}
	
	public void apresentarJogadores() {
		System.out.printf("%-4s %-18s %s", "Id", "jogador", "Gols");
		System.out.println();
		for (int a = 0; a < jogadores.size(); a++) {
			System.out.printf("%-4d %-20s %-15s", this.jogadores.get(a).getIdFunc(), this.jogadores.get(a).getNome(), this.jogadores.get(a).getGols());
			System.out.println();
		}
	}






}




