package brasileiraoView;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MarcadoresView {
	JFrame frameMarcador;
	JLabel tituloMarcador, labelJogador;
	JList<String> listaMarcadores;
	JScrollPane scrollLista1;
	JButton insMarcador;
	ImageIcon image = new ImageIcon("brasileirao/src/arquivos/bola.png");

	String marcadores[] = { "Gabriel Delfim", "Dodô", "Réver", "Junior Alonso", "Mariano", "Rubens",
			"Ignacio Fernandez", "Matias Zaracho", "Pedrinho", "Hulk", "Alan Kardec" };

	public MarcadoresView() {
		frameMarcador = new JFrame("Marcadores");
		frameMarcador.setSize(600, 400);
		frameMarcador.setLayout(null);
		frameMarcador.setResizable(false);
		frameMarcador.setVisible(true);

		tituloMarcador = new JLabel("Inserir marcadores");
		tituloMarcador.setBounds(200, 10, 300, 90);
		tituloMarcador.setFont(new Font("Arial", Font.BOLD, 20));

		labelJogador = new JLabel("Jagadores: ");
		labelJogador.setBounds(210, 80, 100, 25);

		listaMarcadores = new JList<String>(marcadores);
		listaMarcadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollLista1 = new JScrollPane(listaMarcadores);
		scrollLista1.setBounds(210, 100, 120, 140);

		insMarcador = new JButton("Inserir");
		insMarcador.setBounds(200, 250, 150, 30);
		// insMarcador.addActionListener(this);

		frameMarcador.add(tituloMarcador);
		frameMarcador.add(labelJogador);
		frameMarcador.add(scrollLista1);
		frameMarcador.add(insMarcador);

		frameMarcador.setIconImage(image.getImage());
	}

	public static void main(String[] args) {
		new MarcadoresView();
	}
}
