package brasileiraoView;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ArtilheiroView {

	JFrame tabelaArt;
	JPanel painelArt;
	JTable tabela;
	JScrollPane barraRolagem;
	ImageIcon image;

	Object[][] dadosArt = { { "1", "Flamengo", "Gabriel Jesus", "30" }, { "1", "Flamengo", "Gabriel Jesus", "30" },
			{ "1", "Flamengo", "Gabriel Jesus", "30" } };
	String[] colunasArt = { "Num", "Time", "Jogador", "Gols" };

	public ArtilheiroView() {
		painelArt = new JPanel();
		painelArt.setLayout(new GridLayout(1, 1));

		tabela = new JTable(dadosArt, colunasArt);
		barraRolagem = new JScrollPane(tabela);
		painelArt.add(barraRolagem);

		tabelaArt = new JFrame("Artilheiros");
		tabelaArt.getContentPane().add(painelArt);
		tabelaArt.setSize(600, 300);
		tabelaArt.setVisible(true);

		image = new ImageIcon("bola.png");
		tabelaArt.setIconImage(image.getImage());

	}

	public static void main(String[] args) {
		new ArtilheiroView();
	}
}