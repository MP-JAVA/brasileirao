package brasileiraoView;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ListaPartidaView {
	JFrame lpv;
	JLabel tituloLista;
	ImageIcon image2;

	public ListaPartidaView() {
		lpv = new JFrame("Lista de partidas");
		tituloLista = new JLabel("Lista de partidas");

		lpv.setSize(600, 400);
		lpv.setLayout(null);
		lpv.setVisible(true);
		lpv.setResizable(false);

		tituloLista.setBounds(50, 50, 250, 30);
		tituloLista.setFont(new Font("fira code", Font.BOLD, 20)); // Estilização da fonte

		image2 = new ImageIcon("bola.png");
		lpv.setIconImage(image2.getImage());

		lpv.add(tituloLista);
	}

	public static void main(String[] args) {
		new ListaPartidaView();
	}
}
