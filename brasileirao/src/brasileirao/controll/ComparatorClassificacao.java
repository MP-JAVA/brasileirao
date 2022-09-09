package brasileirao.controll;

import java.util.Comparator;

import brasileirao.model.Time;

public class ComparatorClassificacao implements Comparator<Time> {

	@Override
	public int compare(Time a, Time b) {
		if (a.getPontos() - (b.getPontos()) < 0) {
			return 0;
		} else if (a.getVitorias() - (b.getVitorias()) < 0) {
			return -1;
		} else if ((a.getGolsPara() - a.getGolsContra()) - (b.getGolsPara() - b.getGolsContra()) < 0) {
			return -2;
		} else if (a.getGolsPara() - b.getGolsPara() < 0) {
			return -3;
		} else {
			return -4;
		}
	}

}
