package brasileiraoView;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class PartidaView implements ActionListener {

	JFrame framePartida;
	JLabel labelTitulo, labelTime1, labelTime2, labelGols1, labelGols2;
	JList<String> listaTimes1, listaTimes2;
	JScrollPane scrollList1, scrollList2;
	JTextField fieldGols1, fieldGols2;
	JButton buttonCriar, defMarcador;
	ImageIcon image = new ImageIcon("brasileirao/src/arquivos/bola.png");

	String times1[] = { "Flamengo", "São paulo", "Fluminense", "Vasco da gama", "Salvador", "Palmeiras", "Juventude",
			"Flamengo", "São paulo", "Fluminense", "Vasco da gama", "Salvador", "Palmeiras", "Juventude" };

	String times2[] = { "Flamengo", "São paulo", "Fluminense", "Vasco da gama", "Salvador", "Palmeiras", "Juventude",
			"Flamengo", "São paulo", "Fluminense", "Vasco da gama", "Salvador", "Palmeiras", "Juventude" };

	public PartidaView() {
		framePartida = new JFrame("Inserir resultado");

		framePartida.setLayout(null);
		framePartida.setSize(600, 400);
		framePartida.setResizable(false);

		labelTitulo = new JLabel("Inserir resultado");
		labelTitulo.setBounds(25, 25, 200, 50);
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));

		labelTime1 = new JLabel("Time 1:");
		labelTime1.setBounds(75, 80, 100, 25);

		labelTime2 = new JLabel("Time 2:");
		labelTime2.setBounds(250, 80, 100, 25);

		labelGols1 = new JLabel("Gols time 1:");
		labelGols1.setBounds(75, 250, 100, 25);

		labelGols2 = new JLabel("Gols time 2:");
		labelGols2.setBounds(250, 250, 100, 25);

		listaTimes1 = new JList<String>(times1);
		listaTimes1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		listaTimes2 = new JList<String>(times2);
		listaTimes2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollList1 = new JScrollPane(listaTimes1);
		scrollList1.setBounds(75, 100, 120, 140);

		scrollList2 = new JScrollPane(listaTimes2);
		scrollList2.setBounds(250, 100, 120, 140);

		fieldGols1 = new JTextField();
		fieldGols1.setBounds(75, 275, 100, 25);

		fieldGols2 = new JTextField();
		fieldGols2.setBounds(250, 275, 100, 25);

		buttonCriar = new JButton("Criar partida");
		buttonCriar.setBounds(400, 225, 150, 30);
		buttonCriar.addActionListener(this);

		defMarcador = new JButton("Inserir Marcadores");
		defMarcador.setBounds(400, 275, 150, 30);
		defMarcador.addActionListener(this);

		framePartida.add(scrollList1);
		framePartida.add(scrollList2);
		framePartida.add(labelTitulo);
		framePartida.add(labelTime1);
		framePartida.add(labelTime2);
		framePartida.add(labelGols1);
		framePartida.add(labelGols2);
		framePartida.add(fieldGols1);
		framePartida.add(fieldGols2);
		framePartida.add(buttonCriar);
		framePartida.add(defMarcador);

		framePartida.setVisible(true);

		framePartida.setIconImage(image.getImage());
	}

	@Override
	public void actionPerformed(ActionEvent f) {
		Object src = f.getSource();

		if (src == defMarcador)
			new MarcadoresView();
	}

	public static void main(String args[]) {
		new PartidaView();
	}
}