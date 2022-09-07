package brasileiraoView;

import brasileirao.controll.Campeonato;

public class Main {

	public static void main(String args[]) {

		String caminhoElencos = "./arq_backup/cadastro.csv";
		String caminhoPartidas = "./arq_backup/partidas.csv";
		Campeonato brasileirao = new Campeonato(caminhoElencos,caminhoPartidas);

	}
	
	

}
