package brasileirao.controll;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Classe Testes que e responsavel pelos testes unitarios
 * 
 * @author Leandro Souza da Silva
 * @since 2022
 * @version 1.1
 */

class Testes {

	public Campeonato brasileirao;

	public Testes() {
		if (brasileirao == null) {
			String caminhoElencos = "./arq_backup/cadastro.csv";
			String caminhoPartidas = "./arq_backup/partidas.csv";
			brasileirao = new Campeonato(caminhoElencos, caminhoPartidas);
		}
	}

	/**
	 * Verifica se os nomes estão iguais
	 */

	@Test
	public void testgetTimeByName() {

		int Atletico = brasileirao.idTimePeloNome("Atletico-MG");
		int Botafogo = brasileirao.idTimePeloNome("Botafogo");
		int Internacional = brasileirao.idTimePeloNome("Internacional");

		boolean AM = brasileirao.times.get(brasileirao.posTimeNaListaDeTimes(Atletico)).getNome().equals("Atletico-MG");
		boolean BF = brasileirao.times.get(brasileirao.posTimeNaListaDeTimes(Botafogo)).getNome().equals("Botafogo");
		boolean INT = brasileirao.times.get(brasileirao.posTimeNaListaDeTimes(Internacional)).getNome()
				.equals("Internacional");

		assertTrue(AM && BF & INT);
	}

	/**
	 * Esse teste verifica se e possivel adiconar jogador
	 */

	@Test
	public void testAddJogador() {

		long quantPre = brasileirao.times.get(0).getJogadores().stream()
				.filter(Item -> Item.getNome() == "Leonardo" && Item.getPosicao() == "Atacante").count();
		boolean resultado = brasileirao.addJogador(0, "Leonardo", "Atacante");
		long quantPos = brasileirao.times.get(0).getJogadores().stream()
				.filter(Item -> Item.getNome() == "Leonardo" && Item.getPosicao() == "Atacante").count();

		assertTrue(resultado && quantPre == quantPos - 1);

	}

	/**
	 * Esse teste verifica se e possivel deletar jogador
	 */

	@Test
	public void testDeleteJogador() {

		long quantPre = brasileirao.times.get(0).getJogadores().stream()
				.filter(Item -> Item.getNome().equals("Gabriel Delfim")).count();
		boolean resultado = brasileirao.deleteJogador(0, 0);

		long quantPos = brasileirao.times.get(0).getJogadores().stream()
				.filter(Item -> Item.getNome().equals("Gabriel Delfim")).count();

		assertTrue(resultado && quantPre == quantPos + 1);

	}
}
