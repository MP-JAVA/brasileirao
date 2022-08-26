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

	private static JFrame brasi = new JFrame("Brasileirao");
	private static JButton classificacao = new JButton("Classificacao");
	private static JButton artilharia = new JButton("Artilharia");
	private static JButton tabPartidas = new JButton("Tabela de partidas");
	private static JButton insResultados = new JButton("Inserir resultados");
	private static JButton relatorio = new JButton("Resultados");
	private static JLabel menu = new JLabel("Brasileirao 2022");
	private static ImageIcon image2 = new ImageIcon("bola.png");

	public BrasileiraoMenu() {
		brasi.setSize(500, 400); // definindo a largura e altura
		brasi.setLayout(null);
		brasi.setVisible(true); // deixa a janela visivel
		brasi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fecha o programa ao apertar o "X"
		brasi.setResizable(false); // não deixa você expandir a janela

		menu.setBounds(130, 10, 300, 90);
		menu.setFont(new Font("Arial", Font.BOLD, 30)); // Estilização da fonte

		// Definindo a posição dos compoenetes
		classificacao.setBounds(170, 100, 150, 40);
		artilharia.setBounds(170, 150, 150, 40);
		tabPartidas.setBounds(170, 200, 150, 40);
		insResultados.setBounds(170, 250, 150, 40);
		relatorio.setBounds(170, 300, 150, 40);

		// Adicionando os componentes na interface
		brasi.add(classificacao);
		brasi.add(artilharia);
		brasi.add(tabPartidas);
		brasi.add(insResultados);
		brasi.add(relatorio);
		brasi.add(menu);

		// Criação do icone de imagem
		brasi.setIconImage(image2.getImage());

	}

	// Criando um construtor
	public static void main(String[] args) {
		BrasileiraoMenu menu = new BrasileiraoMenu();

		classificacao.addActionListener(menu);
		artilharia.addActionListener(menu);

	}

	// Método para abrir uma nova página ao apertar os butões
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == classificacao)
			new ClassificacaoView();

		if (src == artilharia)
			new ArtilheiroView();
	}
}
