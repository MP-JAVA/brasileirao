package brasileirao.controll;

//Acabei n�o usando essa classe porque consegui fazer implementando a interface comparable na classe Jogador,
//mas acho que seria melhor fazer por aqui para seguir a mesma l�gica do ComparatorClassificacao.

import java.util.Comparator;

import brasileirao.model.Jogador;

public class ComparatorArtilharia implements Comparator<Jogador> {

	@Override
	public int compare(Jogador a, Jogador b) {
		if ((a.getGols() - b.getGols()) > 0) {
			return 1;
		}
		return 0;
	}

}
