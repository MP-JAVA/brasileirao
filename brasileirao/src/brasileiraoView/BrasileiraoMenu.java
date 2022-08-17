package brasileiraoView;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BrasileiraoMenu implements ActionListener {

	private static JFrame brasi = new JFrame("Brasileirão");
	private static JButton classificacao = new JButton("Classificação");
	private static JButton partida = new JButton("Partida");
	private static JLabel menu = new JLabel("Menu");
	private static ImageIcon image2 = new ImageIcon("bola.png");

	public BrasileiraoMenu() {
		menu.setBounds(350, 50, 100, 30);
		menu.setFont(new Font("fira code", Font.BOLD, 40)); // Estilização da fonte

		classificacao.setBounds(50, 400, 150, 40);
		partida.setBounds(50, 450, 150, 40);

		brasi.setSize(800, 600); // definindo a largura e altura
		// brasi.setTitle("Brasileirão");
		brasi.setLayout(null);
		brasi.setVisible(true); // deixa a janela visivel
		brasi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		brasi.setResizable(false); // não deixa você expandir a janela

		brasi.add(classificacao);
		brasi.add(partida);
		brasi.add(menu);

		// create ImageIcon
		brasi.setIconImage(image2.getImage());

	}

	public static void main(String[] args) {
		BrasileiraoMenu menu = new BrasileiraoMenu();

		classificacao.addActionListener(menu);
		partida.addActionListener(menu);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == classificacao)
			new ClassificacaoView();

		if (src == partida)
			new PartidaView();
	}
}
