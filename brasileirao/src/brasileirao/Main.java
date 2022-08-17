package brasileirao;

import java.util.ArrayList;

public class Main {
	
	public static void main (String args[]) {

		Campeonato brasileirao = new Campeonato();
		String caminhoElencos = "C:\\Users\\Leo\\git\\brasileirao\\brasileirao\\src\\arquivos\\cadastro.csv";
		String caminhoPartidas = "C:\\Users\\Leo\\git\\brasileirao\\brasileirao\\src\\arquivos\\partidas.csv";
		brasileirao.carregarDados(caminhoElencos, caminhoPartidas);
		
	
		//brasileirao.apresentarTime();
		

		
		//brasileirao.apresentarPartidas();
		
		


	}
	
	

}
