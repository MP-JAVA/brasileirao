package brasileiraoView;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ClassificacaoView {

	JFrame tabelaClassificacao;
	JPanel painelFundo;
	JTable tabela;
	JScrollPane barraRolagem;
	ImageIcon image = new ImageIcon("brasileirao/src/arquivos/bola.png");

	Object[][] dados = { { "1", "Palmeiras", "48", "22", "14", "6", "2" },
			{ "2", "Flamengo", "47", "22", "14", "6", "2" }, { "3", "Corinthians", "46", "22", "14", "6", "2" }, };

	String[] colunas = { "Classificacao", "Time", "p", "j", "v", "e", "d" };

	public ClassificacaoView() {
		tabelaClassificacao = new JFrame("Classificacao");
		painelFundo = new JPanel();
		painelFundo.setLayout(new GridLayout(1, 1));
		tabela = new JTable(dados, colunas);
		barraRolagem = new JScrollPane(tabela);
		painelFundo.add(barraRolagem);

		tabelaClassificacao.getContentPane().add(painelFundo);
		tabelaClassificacao.setSize(600, 300);
		tabelaClassificacao.setVisible(true);

		tabelaClassificacao.setIconImage(image.getImage());
	}

	public static void main(String[] args) {
		new ClassificacaoView();
	}

}