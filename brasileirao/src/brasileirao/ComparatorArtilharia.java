package brasileirao; 

//Acabei não usando essa classe porque consegui fazer implementando a interface comparable na classe Jogador,
//mas acho que seria melhor fazer por aqui para seguir a mesma lógica do ComparatorClassificacao.

import java.util.Comparator;

public class ComparatorArtilharia implements Comparator <Jogador>{
	
	@Override
	public int compare(Jogador a, Jogador b) {
		if ((a.getGols() - b.getGols()) > 0) {
			return 1;	
		}
		return 0;
	}

}
