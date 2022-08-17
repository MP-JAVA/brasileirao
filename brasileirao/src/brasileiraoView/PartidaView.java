package brasileiraoView;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class PartidaView {

	private static JFrame telaPart = new JFrame("Partida");
	private static JLabel title = new JLabel("Partida");

	public PartidaView() {
		telaPart.setSize(600, 400);
		telaPart.setLayout(null);
		telaPart.setVisible(true);
		telaPart.setResizable(false);

		telaPart.setBounds(50, 50, 100, 30);
		telaPart.setFont(new Font("fira code", Font.BOLD, 20)); // Estilização da fonte

		telaPart.add(title);

	}

	public static void main(String[] args) {
		ClassificacaoView partida = new ClassificacaoView();
	}

}
