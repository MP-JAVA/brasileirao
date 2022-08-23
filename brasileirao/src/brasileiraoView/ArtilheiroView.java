package brasileiraoView;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ArtilheiroView {
	JFrame frameArtilheiro;
	JLabel tituloArtilheiro, labelArtilheiro1, labelArtilheiro2, golsArtilheiro1, golsArtilheiro2;
	JList<String> listaArtilheiro1, listaArtilheiro2;
	JScrollPane scrollArtilheiro1, scrollArtilheiro2;
	JTextField fieldArtilheiro1, fieldArtilheiro2;
	JButton addArtilheiro;

	String Artilheiro1[] = { "Gabriel Delfim", "Dodô", "Réver", "Junior Alonso", "Mariano", "Rubens",
			"Ignacio Fernandez", "Matias Zaracho", "Pedrinho", "Hulk", "Alan Kardec" };

	String Artilheiro2[] = { "Gabriel Delfim", "Dodô", "Réver", "Junior Alonso", "Mariano", "Rubens",
			"Ignacio Fernandez", "Matias Zaracho", "Pedrinho", "Hulk", "Alan Kardec" };

	public ArtilheiroView() {
		frameArtilheiro = new JFrame("Definir artilheiro");

		frameArtilheiro.setLayout(null);
		frameArtilheiro.setSize(600, 400);
		frameArtilheiro.setResizable(false);
		frameArtilheiro.setVisible(true);

		tituloArtilheiro = new JLabel("Definir artilheiro");
		tituloArtilheiro.setBounds(25, 25, 250, 30);
		tituloArtilheiro.setFont(new Font("Arial", Font.BOLD, 20));

		// labelArtilheiro1 = new JLabel("Artilheiros do time 1");
		// labelArtilheiro1.setBounds(75, 80, 250, 30);

		// labelArtilheiro2 = new JLabel("Artilheiros do time 2");
		// labelArtilheiro2.setBounds(250, 80, 150, 25);

		golsArtilheiro1 = new JLabel("Gols feitos pelo artilheiro 1:");
		golsArtilheiro1.setBounds(75, 250, 175, 25);

		golsArtilheiro2 = new JLabel("Gols feitos pelo artilheiro 2:");
		golsArtilheiro2.setBounds(250, 250, 175, 25);

		listaArtilheiro1 = new JList<String>(Artilheiro1);
		listaArtilheiro1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollArtilheiro1 = new JScrollPane(listaArtilheiro1);
		scrollArtilheiro1.setBounds(75, 100, 120, 140);

		listaArtilheiro2 = new JList<String>(Artilheiro2);
		listaArtilheiro2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollArtilheiro2 = new JScrollPane(listaArtilheiro2);
		scrollArtilheiro2.setBounds(250, 100, 120, 140);

		fieldArtilheiro1 = new JTextField();
		fieldArtilheiro1.setBounds(75, 275, 100, 25);

		fieldArtilheiro2 = new JTextField();
		fieldArtilheiro2.setBounds(250, 275, 100, 25);

		addArtilheiro = new JButton("Adicionar Artilheiros");
		addArtilheiro.setBounds(400, 325, 150, 30);
		// addArtilheiro.addActionListener(this);

		frameArtilheiro.add(tituloArtilheiro);
		frameArtilheiro.add(scrollArtilheiro1);
		frameArtilheiro.add(scrollArtilheiro2);
		// frameArtilheiro.add(fieldArtilheiro1);
		// frameArtilheiro.add(fieldArtilheiro2);
		frameArtilheiro.add(golsArtilheiro1);
		frameArtilheiro.add(golsArtilheiro2);
		frameArtilheiro.add(fieldArtilheiro1);
		frameArtilheiro.add(fieldArtilheiro2);
		frameArtilheiro.add(addArtilheiro);
	}

	public static void main(String[] args) {
		new ArtilheiroView();
	}
}
