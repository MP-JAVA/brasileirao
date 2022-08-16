package brasileiraoView;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BrasileiraoMenu {

	public static void main(String[] args) {

		JFrame f = new JFrame(); // criando instância do Jframe
		JButton classificacao, partida;
		JLabel menu = new JLabel("Menu");

		classificacao = new JButton("Classificação");
		partida = new JButton("Partida");
		classificacao.setBounds(50, 400, 150, 40);
		partida.setBounds(50, 450, 150, 40);

		menu.setBounds(350, 50, 100, 30);
		menu.setFont(new Font("fira code", Font.BOLD, 40)); // Estilização da fonte

		// adcionando o botão no JFrame
		f.add(classificacao);
		f.add(partida);
		f.add(menu);

		f.setSize(800, 600); // definindo a largura e altura
		f.setTitle("Brasileirão");
		f.setLayout(null);
		f.setVisible(true); // deixa a janela visivel
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false); // não deixa você expandir a janela

		ImageIcon image2 = new ImageIcon("bola.png"); // create ImageIcon
		f.setIconImage(image2.getImage());

	}
}
