package brasileiraoView;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ClassificacaoView {

	private static JFrame telaClassi = new JFrame("Classificação");
	private static JLabel title = new JLabel("Classficação");

	public ClassificacaoView() {
		telaClassi.setSize(600, 400);
		telaClassi.setLayout(null);
		telaClassi.setVisible(true);
		telaClassi.setResizable(false);

		telaClassi.setBounds(50, 50, 50, 30);
		telaClassi.setFont(new Font("fira code", Font.BOLD, 20)); // Estilização da fonte

		telaClassi.add(title);
	}

	public static void main(String[] args) {
		ClassificacaoView classi = new ClassificacaoView();
	}

}
