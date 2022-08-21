package brasileiraoView;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

public class PartidaView implements ActionListener {

	JFrame telaPart;
	JLabel title;
	ImageIcon image2;
	JButton criarPartida;
	static JButton mostrarPartida;
	DefaultListModel<String> l1;
	JList<String> list;

	public PartidaView() {
		telaPart = new JFrame("Partida");
		title = new JLabel("Criar partida");
		l1 = new DefaultListModel<>();
		list = new JList<>(l1);
		criarPartida = new JButton("Criar");
		mostrarPartida = new JButton("Ver lista de partidas");

		telaPart.setSize(600, 400);
		telaPart.setLayout(null);
		telaPart.setVisible(true);
		telaPart.setResizable(false);

		l1.addElement("Flamengo");
		l1.addElement("São paulo");
		l1.addElement("Fluminense");
		l1.addElement("Vasco da gama");
		l1.addElement("Salvador");
		l1.addElement("Palmeiras");
		l1.addElement("Juventude");
		l1.addElement("Flamengo");
		l1.addElement("São paulo");
		l1.addElement("Fluminense");
		l1.addElement("Vasco da gama");
		l1.addElement("Salvador");
		l1.addElement("Palmeiras");
		l1.addElement("Juventude");

		list.setBounds(100, 100, 125, 125);

		criarPartida.setBounds(100, 300, 150, 40);
		mostrarPartida.setBounds(260, 300, 150, 40);

		title.setBounds(25, 25, 250, 30);
		title.setFont(new Font("fira code", Font.BOLD, 20)); // Estilização da fonte

		image2 = new ImageIcon("bola.png");
		telaPart.setIconImage(image2.getImage());

		telaPart.add(title);
		telaPart.add(list);
		telaPart.add(criarPartida);
		telaPart.add(mostrarPartida);

	}

	public static void main(String[] args) {
		PartidaView partida = new PartidaView();

		mostrarPartida.addActionListener(partida);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == mostrarPartida)
			new ListaPartidaView();
	}

}
