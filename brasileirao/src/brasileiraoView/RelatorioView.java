package brasileiraoView;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RelatorioView {
	JFrame frameRelatorio;
	JLabel tituloRelatorio, textoRelatorio;
	ImageIcon image = new ImageIcon("brasileirao/src/arquivos/bola.png");

	public RelatorioView() {
		frameRelatorio = new JFrame("Relatorios");
		frameRelatorio.setSize(600, 400);
		frameRelatorio.setLayout(null);
		frameRelatorio.setResizable(false);
		frameRelatorio.setVisible(true);

		tituloRelatorio = new JLabel("Relatorios");
		tituloRelatorio.setBounds(130, 10, 300, 90);
		tituloRelatorio.setFont(new Font("Arial", Font.BOLD, 20));

		frameRelatorio.add(tituloRelatorio);

		frameRelatorio.setIconImage(image.getImage());
	}

	public static void main(String[] args) {
		new RelatorioView();
	}
}
