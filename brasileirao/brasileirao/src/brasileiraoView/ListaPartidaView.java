package brasileiraoView;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListaPartidaView {
	JFrame tabelaPartidas;
	JPanel painelPartidas;
	JTable tabelaJogos;
	JScrollPane tabelaRolagem;
	ImageIcon image = new ImageIcon("brasileirao/src/arquivos/bola.png");

	Object[][] dados = { { "1", "Palmeiras", "48", "x", "45", "flamengo" },
			{ "2", "corinthias", "48", "x", "45", "vasco" }, { "3", "vasco da gama", "48", "x", "45", "atletico" }, };

	String[] colunas = { "rodada", "time mandante", "gols", "x", "gols", "time visitante" };

	public ListaPartidaView() {
		tabelaPartidas = new JFrame("Lista de partidas");

		painelPartidas = new JPanel();
		painelPartidas.setLayout(new GridLayout(1, 1));

		tabelaJogos = new JTable(dados, colunas);
		tabelaRolagem = new JScrollPane(tabelaJogos);
		painelPartidas.add(tabelaRolagem);

		tabelaPartidas.getContentPane().add(painelPartidas);
		tabelaPartidas.setSize(600, 300);
		tabelaPartidas.setVisible(true);

		tabelaPartidas.setIconImage(image.getImage());

	}

	public static void main(String[] args) {
		new ListaPartidaView();
	}
}
