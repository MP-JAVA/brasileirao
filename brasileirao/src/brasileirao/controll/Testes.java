package brasileirao.controll;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import brasileirao.model.Jogador;
import brasileirao.model.Time;

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
		if(brasileirao == null) {
			String caminhoElencos = "./arq_backup/cadastro.csv";
		    String caminhoPartidas = "./arq_backup/partidas.csv";
		    brasileirao = new Campeonato(caminhoElencos,caminhoPartidas);
		}
	}

	@Test
	public void testgetTimeByName() {
		
		int Atletico = brasileirao.getTimeByName("Atletico-MG");
		int Botafogo = brasileirao.getTimeByName("Botafogo");
		int Internacional = brasileirao.getTimeByName("Internacional");
		
		boolean AM = brasileirao.times.get(brasileirao.getIndexTimeByID(Atletico)).getNome().equals("Atletico-MG");
		boolean BF = brasileirao.times.get(brasileirao.getIndexTimeByID(Botafogo)).getNome().equals("Botafogo");
		boolean INT = brasileirao.times.get(brasileirao.getIndexTimeByID(Internacional)).getNome().equals("Internacional");
		
		assertTrue(AM && BF & INT);
	}
	
	@Test
	public void testAddJogador() {
		
		long quantPre = brasileirao.times.get(0).getJogadores().stream().filter(Item->
																				Item.getNome()=="Leonardo" &&
																				Item.getPosicao() == "Atacante").count();
		boolean resultado = brasileirao.addJogador(0, "Leonardo", "Atacante");
		long quantPos = brasileirao.times.get(0).getJogadores().stream().filter(Item->
																				Item.getNome()=="Leonardo" &&
																				Item.getPosicao() == "Atacante").count();
	
		assertTrue(resultado && quantPre == quantPos - 1);

	}
	
	@Test
	public void testDeleteJogador() {
		
		long quantPre = brasileirao.times.get(0).getJogadores().stream().filter(Item->
																				Item.getNome().equals("Gabriel Delfim")).count();
		boolean resultado = brasileirao.deleteJogador(0, 0);
		
		long quantPos = brasileirao.times.get(0).getJogadores().stream().filter(Item->
																				Item.getNome().equals("Gabriel Delfim")).count();
		
		assertTrue(resultado && quantPre == quantPos + 1);

	}
}

