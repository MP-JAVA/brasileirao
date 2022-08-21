package brasileiraoView;
// foi usado as bibliotecas awt e swing para a criação das interfaces gráficas

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BrasileiraoMenu implements ActionListener {
	// Criação dos componentes da interface

	private static JFrame brasi = new JFrame("Brasileirão");
	private static JButton classificacao = new JButton("Classificação");
	private static JButton partida = new JButton("Partida");
	private static JLabel menu = new JLabel("Menu");
	private static ImageIcon image2 = new ImageIcon("bola.png");

	public BrasileiraoMenu() {
		brasi.setSize(500, 400); // definindo a largura e altura
		brasi.setLayout(null);
		brasi.setVisible(true); // deixa a janela visivel
		brasi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fecha o programa ao apertar o "X"
		brasi.setResizable(false); // não deixa você expandir a janela

		menu.setBounds(200, 50, 100, 30);
		menu.setFont(new Font("fira code", Font.BOLD, 40)); // Estilização da fonte

		// Definindo a posição dos compoenetes
		classificacao.setBounds(175, 150, 150, 40);
		partida.setBounds(175, 200, 150, 40);

		// Adicionando os componentes na interface
		brasi.add(classificacao);
		brasi.add(partida);
		brasi.add(menu);

		// Criação do icone de imagem
		brasi.setIconImage(image2.getImage());

	}

	// Criando um construtor
	public static void main(String[] args) {
		BrasileiraoMenu menu = new BrasileiraoMenu();

		classificacao.addActionListener(menu);
		partida.addActionListener(menu);

	}

	// Método para abrir uma nova página ao apertar os butões
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == classificacao)
			new ClassificacaoView();

		if (src == partida)
			new PartidaView();
	}
}
