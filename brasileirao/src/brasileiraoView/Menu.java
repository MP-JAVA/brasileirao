package brasileiraoView;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import brasileirao.controll.Campeonato;

/**
 * Classe Menu no pacote view, sendo a principal interface entre a aplicação e o
 * usuário.
 * 
 * @author Leandro Souza da Silva
 * @author Leonardo Passos
 * @since 2022
 * @version 1.1
 */

public class Menu {

	/**
	 * Definindo os elementos que irao compor a classe, como butoes e paneis, alem
	 * de estilizar e adicionar no Menu.
	 */

	private static final JButton Classificacao = new JButton("Classificacao");
	private static final JButton Artilharia = new JButton("Artilharia");
	private static final JButton Tabela = new JButton("Tabela de partidas");
	private static final JButton Inserir = new JButton("Inserir resultados");
	private static final JButton Relatorios = new JButton("Relatorios");
	private static final JButton Jogadores = new JButton("Jogadores");
	public static Campeonato brasileirao;

	public static JPanel titulo() {
		JPanel Titulo = new JPanel();
		Titulo.setBorder(new EmptyBorder(15, 15, 15, 15));
		JLabel Frase = new JLabel("Brasileirao 2022");
		Frase.setFont(new Font("Arial", Font.PLAIN, 25));
		Titulo.add(Frase);
		return Titulo;
	}

	public static JPanel botoes() {
		JPanel Botoes = new JPanel(new GridLayout(6, 1, 0, 15));
		Botoes.add(Classificacao);
		Botoes.add(Artilharia);
		Botoes.add(Tabela);
		Botoes.add(Inserir);
		Botoes.add(Relatorios);
		Botoes.add(Jogadores);
		Botoes.setAlignmentY(Component.CENTER_ALIGNMENT);
		return Botoes;
	}

	/**
	 * Instanciando alguns elementos do menu.
	 */

	public static void main(String[] args) {
		String caminhoElencos = "./arq_backup/cadastro.csv";
		String caminhoPartidas = "./arq_backup/partidas.csv";
		brasileirao = new Campeonato(caminhoElencos, caminhoPartidas);
		acoes();
		JFrame frame = new JFrame("Brasileirao 2022");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new BorderLayout()); /** panel principal config em N/S/L/O/C */
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel.add(titulo(), BorderLayout.NORTH); /** Cria e posiciona o titulo */
		panel.add(botoes(), BorderLayout.CENTER); /** Cria e posiciona os botoes */
		frame.setSize(800, 600);
		frame.setMinimumSize(new Dimension(300, 400));
		frame.setLocationRelativeTo(null); /** Centraliza o frame (caixa) */
		frame.add(panel); /** coloca o panel principal (com os demais panels nele incluidos) no frame. */
		frame.setVisible(true);
	}

	/**
	 * Adicionando acoes ao pressionar os butoes.
	 */

	public static void acoes() {
		Artilharia.addActionListener(e -> new Artilharia());
		Tabela.addActionListener(e -> new Partidas());
		Classificacao.addActionListener(e -> new Classificacao());
		Inserir.addActionListener(e -> new Inserir());
		Relatorios.addActionListener(e -> new RelatorioView());
		Jogadores.addActionListener(e -> new Jogadores());
	}

}
