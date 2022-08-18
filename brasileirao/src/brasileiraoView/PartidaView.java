package brasileiraoView;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PartidaView {

	JFrame telaPart;
	JLabel title;
	ImageIcon image2;

	public PartidaView() {
		telaPart = new JFrame("Partida");
		title = new JLabel("Partida");

		telaPart.setSize(600, 400);
		telaPart.setLayout(null);
		telaPart.setVisible(true);
		telaPart.setResizable(false);

		title.setBounds(50, 50, 100, 30);
		title.setFont(new Font("fira code", Font.BOLD, 20)); // Estilização da fonte

		image2 = new ImageIcon("bola.png");
		telaPart.setIconImage(image2.getImage());

		telaPart.add(title);

	}

	public static void main(String[] args) {
		new PartidaView();
	}

}
