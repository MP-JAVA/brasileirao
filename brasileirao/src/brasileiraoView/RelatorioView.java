package brasileiraoView;

import brasileirao.controll.Campeonato;
import brasileirao.model.Time;

import java.awt.*;
import java.util.Arrays;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import static config.configuracoes.layoytconstr;

public class RelatorioView {

	private JTextField Tecnico;
	private JComboBox Times;
	private JButton Buscar;
	private JTable Tabela;
	private JScrollPane Scroll;

	private void iniciar_componentes(){
		Times = new JComboBox();
		Tecnico = new JTextField();
		Buscar = new JButton("Buscar");
	}

	private void inicar_metodos(){
		Object[] Modelo = Menu.brasileirao.getTimes().stream().map(Time::getNome).toArray();
		Arrays.sort(Modelo);
		Times.setModel(new DefaultComboBoxModel(Modelo));
		Tabela = new JTable(new DefaultTableModel(new Object[][]{}, new Object[]{"Jogador", "Gols", "Posicao"})){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		Scroll = new JScrollPane(Tabela);
		Tecnico.setEnabled(false);
		Buscar.doClick();
	}

	public RelatorioView(){
		JFrame frame = new JFrame("Relatorio");
		JPanel panel = new JPanel(new BorderLayout());
		iniciar_componentes();
		acoes();
		inicar_metodos();
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel.add(titulo(), BorderLayout.NORTH);
		panel.add(botoes(), BorderLayout.CENTER);
		frame.setSize(800,600);
		frame.setMinimumSize(new Dimension(300, 400));
		frame.setLocationRelativeTo(null);
		frame.add(panel);
		frame.setVisible(true);
	}

	public JPanel titulo(){
		JPanel Titulo = new JPanel();
		Titulo.setBorder(new EmptyBorder(15, 15, 15, 15));
		JLabel Frase = new JLabel("Relatorio");
		Frase.setFont(new Font("Arial", Font.PLAIN, 25));
		Titulo.add(Frase);
		return Titulo;
	}

	public JPanel botoes(){
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(time_select(), BorderLayout.NORTH);
		panel.add(Scroll, BorderLayout.CENTER);
		return panel;
	}

	public JPanel time_select(){
		JPanel Insercao = new JPanel(new GridBagLayout());
		Insercao.add(new JLabel("Times",SwingConstants.CENTER), layoytconstr(GridBagConstraints.HORIZONTAL,
				1, 0, 0,0,2,1));
		Insercao.add(Times, layoytconstr(GridBagConstraints.HORIZONTAL,
				1, 0, 0,1,2,1));
		Insercao.add(Buscar, layoytconstr(GridBagConstraints.HORIZONTAL,
				1, 0, 0,2,2,1));
		Insercao.add(new JLabel("Resultado",SwingConstants.CENTER), layoytconstr(GridBagConstraints.HORIZONTAL,
				1, 0, 0,3,2,1));
		Insercao.add(new JLabel("Tecnico: ",SwingConstants.CENTER), layoytconstr(GridBagConstraints.HORIZONTAL,
				0, 0, 0,4,1,1));
		Insercao.add(Tecnico, layoytconstr(GridBagConstraints.HORIZONTAL,
				2, 0, 1,4,1,1));
		return Insercao;
	}

	private void acoes(){
		Buscar.addActionListener(e->{
			Map.Entry<String,Object[][]> XD = Menu.brasileirao.tabela_times(Times.getSelectedItem().toString());
			DefaultTableModel model = (DefaultTableModel) Tabela.getModel();
			model.setRowCount(0);
			for(Object[] Item:XD.getValue()){
				model.addRow(Item);
			}
			Tecnico.setText(XD.getKey());
		});
	}

}
