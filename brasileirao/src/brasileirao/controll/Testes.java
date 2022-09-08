package brasileirao.controll;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * Classe Testes que e responsavel pelos testes unitarios
 * 
 * @author Leandro Souza da Silva
 * @since 2022
 * @version 1.1
 */

class Testes {
    String caminhoElencos = "./arq_backup/cadastro.csv";
    String caminhoPartidas = "./arq_backup/partidas.csv";
    Campeonato brasileirao = new Campeonato(caminhoElencos,caminhoPartidas);

	/**
	 * Metodo para verificar se o metodo cadastrarTime está funcionando da maneira
	 * desejada
	 * 
	 * @see Campeonato
	 */

	@Test
	void testCadastrarTime() {
		Campeonato c = new Campeonato(null, null);
		assertTrue(c.cadastrarTime("Flamengo"));
		assertFalse(c.cadastrarTime("22"));
	}

	/**
	 * Metodo para verificar se o metodo cadastrarJogador está funcionando da
	 * maneira desejada
	 * 
	 * @see Campeonato
	 */

	@Test
	void testCadastrarJogador() {
		String nomeCerto = "Hulk";
		String nomeErrado = "2345";
		String posicaoCerta = "Atacante";
		String posicaoErrada = "GabrielZagueiro";
		String idCerto = "23";
		String idErrado = "oi";

		assertTrue(Campeonato.cadastrarJogador(nomeCerto, posicaoCerta, idCerto));
		assertFalse(Campeonato.cadastrarJogador(nomeErrado, posicaoErrada, idErrado));

	}

	/**
	 * Metodo para verificar se o metodo cadastrarJogador está funcionando da
	 * maneira desejada
	 * 
	 * @see Campeonato
	 */

	@Test
	public void testBuscarIdTime() {

        
		int idTimeEsperado = 0;
		int resultado = brasileirao.buscarIdTime(3);
		assertTrue(resultado == idTimeEsperado);
	}
}
