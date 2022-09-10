package brasileiraoView;

import static config.configuracoes.posComponentes;
import static java.util.stream.Collectors.toList;
import static javax.swing.JOptionPane.YES_NO_OPTION;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import brasileirao.model.Jogador;
import brasileirao.model.Partida;
import brasileirao.model.Time;

/**
 * Classe Inserir no pacote view, um CRUD de adicionar novas partidas, podendo
 * escolher time mandante e visitante, quantidade gols que eles fizeram alem de
 * escolher os marcadores.
 * 
 * @author Leandro Souza da Silva
 * @author Leonardo Passos
 * @since 2022
 * @version 1.1
 */

public class Inserir {

	/**
	 * Definindo os elementos que irao compor a classe, como JTextfields e JLists,
	 * alem de estilizar e adicionar em inserir.
	 */

	private JComboBox comboNomesTimeMandante, comboNomesTimeVisitante, comboNomesTimesSelecionados,
			comboNomeJogTimesSelecionados;
	private JButton Verificacao, Computar, Adicionar, Finalizar;
	private int ID, GolMan, GolVis;
	private JTextField GolsMan, GolsVis;
	private JFrame Frame;
	private JPanel PanelGols, PanelMarcadores;
	private ArrayList<Map.Entry<String, String>> Marcador;
	private String nomeMandante, nomeVisitante;
	private JScrollPane scroll;
	private JList marcadoresSelecionados;

	public Inserir() {
		Frame = new JFrame("Inserir resultados");
		iniciarcomponentes();
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel.add(titulo(), BorderLayout.NORTH);
		panel.add(principal(), BorderLayout.CENTER);
		iniciarmetodos();
		acoes();
		Frame.setSize(1000, 800);
		Frame.setMinimumSize(new Dimension(1000, 800));
		Frame.setLocationRelativeTo(null);
		Frame.add(panel);
		Frame.setVisible(true);
	}

	/** Metodos de inicializacao dos componentes da tela --------------- */
	public void iniciarcomponentes() {
		Object[] nomesDosTimes = Menu.brasileirao.getTimes().stream().map(Time::getNome).toArray();
		Arrays.sort(nomesDosTimes);
		GolsMan = new JTextField();
		GolsVis = new JTextField();
		Computar = new JButton("Computar");
		comboNomesTimesSelecionados = new JComboBox();
		comboNomeJogTimesSelecionados = new JComboBox();
		Adicionar = new JButton("Adicionar");
		DefaultListModel<String> marcadoresEscolhidos = new DefaultListModel<>();
		marcadoresSelecionados = new JList(marcadoresEscolhidos);
		Finalizar = new JButton("Finalizar");
		comboNomesTimeMandante = new JComboBox(nomesDosTimes);
		comboNomesTimeVisitante = new JComboBox(nomesDosTimes);
		Verificacao = new JButton("Verificacao");
		scroll = new JScrollPane(marcadoresSelecionados);
		PanelGols = gols();
		PanelMarcadores = marcadores();

	}

	public void iniciarmetodos() {
		habilitar_componentes(PanelGols, false);
		habilitar_componentes(PanelMarcadores, false);
	}

	/** Fim dos metodos de inicializacao dos componentes da tela --------------- */

	public JPanel titulo() {
		JPanel Titulo = new JPanel();
		Titulo.setBorder(new EmptyBorder(15, 15, 15, 15));
		JLabel Frase = new JLabel("Inserir resultados");
		Frase.setFont(new Font("Arial", Font.PLAIN, 25));
		Titulo.add(Frase);
		return Titulo;
	}

	/**
	 * JLabel gols, marcadores e processos, sao containers de conteudo e o uso de
	 * GridBagLayout foi para posicionar esses elementos.
	 */

	public JPanel gols() {
		JPanel Insercao = new JPanel(new GridBagLayout());
		Insercao.add(new JLabel("Gols do time mandante: "),
				posComponentes(GridBagConstraints.HORIZONTAL, 0, 0, 0, 0, 1, 1));
		Insercao.add(GolsMan, posComponentes(GridBagConstraints.HORIZONTAL, 2, 0, 1, 0, 1, 1));
		Insercao.add(new JLabel("Gols do time visitante: "),
				posComponentes(GridBagConstraints.HORIZONTAL, 0, 0, 0, 1, 1, 1));
		Insercao.add(GolsVis, posComponentes(GridBagConstraints.HORIZONTAL, 2, 0, 1, 1, 1, 1));
		Insercao.add(Computar, posComponentes(GridBagConstraints.HORIZONTAL, 2, 0, 0, 2, 2, 1));
		return Insercao;
	}

	public JPanel marcadores() {
		JPanel Insercao = new JPanel(new GridBagLayout());
		Insercao.add(new JLabel("Times", SwingConstants.CENTER),
				posComponentes(GridBagConstraints.HORIZONTAL, 1, 0, 0, 0, 1, 1));
		Insercao.add(comboNomesTimesSelecionados, posComponentes(GridBagConstraints.HORIZONTAL, 1, 0, 0, 1, 1, 1));
		Insercao.add(new JLabel("Jogadores", SwingConstants.CENTER),
				posComponentes(GridBagConstraints.HORIZONTAL, 1, 0, 0, 2, 1, 1));
		Insercao.add(comboNomeJogTimesSelecionados, posComponentes(GridBagConstraints.HORIZONTAL, 1, 0, 0, 3, 1, 1));
		Insercao.add(Adicionar, posComponentes(GridBagConstraints.HORIZONTAL, 1, 0, 0, 4, 1, 1));
		Insercao.add(new JLabel("Selecionados", SwingConstants.CENTER),
				posComponentes(GridBagConstraints.HORIZONTAL, 1, 0, 1, 0, 1, 1));
		Insercao.add(scroll, posComponentes(GridBagConstraints.BOTH, 1, 0, 1, 1, 1, 4));
		Insercao.add(Finalizar, posComponentes(GridBagConstraints.HORIZONTAL, 1, 0, 0, 5, 2, 1));
		return Insercao;
	}

	public JPanel principal() { /**
								 * Abarca a selecao dos times e, em seguida, os outros dois panels (gols e
								 * marcadores)
								 */
		JPanel Insercao = new JPanel(new GridBagLayout());
		Insercao.add(new JLabel("Time mandante", SwingConstants.CENTER),
				posComponentes(GridBagConstraints.HORIZONTAL, 3, 0, 0, 0, 1, 1));
		Insercao.add(comboNomesTimeMandante, posComponentes(GridBagConstraints.HORIZONTAL, 3, 0, 0, 1, 1, 1));
		Insercao.add(new JLabel("Time visitante", SwingConstants.CENTER),
				posComponentes(GridBagConstraints.HORIZONTAL, 3, 0, 0, 2, 1, 1));
		Insercao.add(comboNomesTimeVisitante, posComponentes(GridBagConstraints.HORIZONTAL, 3, 0, 0, 3, 1, 1));
		Insercao.add(Verificacao, posComponentes(GridBagConstraints.HORIZONTAL, 3, 0, 0, 4, 1, 1));
		Insercao.add(PanelGols, posComponentes(GridBagConstraints.HORIZONTAL, 3, 0, 0, 5, 1, 1));
		Insercao.add(PanelMarcadores, posComponentes(GridBagConstraints.HORIZONTAL, 3, 0, 0, 6, 1, 1));
		return Insercao;
	}

	private void habilitar_componentes(JPanel Panel,
			boolean isEnable) { /** (des)ativa os componentes do panel escolhido. */
		Component[] com = Panel.getComponents();
		for (Component Item : com) {
			Item.setEnabled(isEnable);
		}
	}

	/**
	 * O metodo acoes definido alguns acontecimentos para voce conseguir inserir uma
	 * partida, e retornando mensagens se esses acontecimentos não forem feitos
	 * corretamento.
	 */

	private void acoes() {
		Verificacao.addActionListener(e -> {
			if (Finalizar.isEnabled()) {
				comboNomesTimesSelecionados.removeAllItems();
				habilitar_componentes(PanelMarcadores, false);
			}
			ArrayList<Partida> idPartidas = new ArrayList<>();
			/**
			 * Adiciona em idPartida a partida entre o mandante e visitante que esteja
			 * pendente.
			 */
			for (Partida Item : Menu.brasileirao.getTabelaDePartidas()) {
				if (Item.getTimeVisitante().equals(comboNomesTimeVisitante.getSelectedItem().toString())
						&& Item.getTimeMandante().equals(comboNomesTimeMandante.getSelectedItem().toString())) {
					if (Item.getStatus().equals("PENDENTE")) {
						idPartidas.add(Item);
					}
				}
			}
			if (idPartidas.size() != 1) {
				if (idPartidas.size() > 1) { /** Tratamento para diferentes partidas pendentes entre os mesmos times. */
					Object[] Itens = (idPartidas.stream().map(Item -> String.format("%s", Item.getIdPartida()))
							.toArray());
					Object Selecionado = JOptionPane.showInputDialog(null, "Escolha um item", "Selecao de itens",
							JOptionPane.PLAIN_MESSAGE, null, Itens, "");
					if (Selecionado != null) {
						ID = Integer.parseInt(Selecionado.toString());
					} else {
						return;
					}
				} else {
					JOptionPane.showMessageDialog(Frame, "Esta partida nao existe ou ja foi realizada.");
					return;
				}
			} else {
				ID = idPartidas.get(0).getIdPartida();
			}
			nomeMandante = comboNomesTimeMandante.getSelectedItem().toString();
			nomeVisitante = comboNomesTimeVisitante.getSelectedItem().toString();
			GolsVis.setText("0");
			GolsMan.setText("0");
			habilitar_componentes(PanelGols, true);
		});
		Computar.addActionListener(e -> {
			if (!GolsVis.getText().equals("") && !GolsVis.getText().equals("")) {
				try {
					GolVis = Integer.parseInt(GolsVis.getText());
					GolMan = Integer.parseInt(GolsMan.getText());
					if (GolVis != 0 || GolMan != 0) {
						Marcador = new ArrayList<>();
						comboNomesTimesSelecionados.addItem(nomeMandante);
						comboNomesTimesSelecionados.addItem(nomeVisitante);
						habilitar_componentes(PanelGols, false);
						habilitar_componentes(PanelMarcadores, true);
					} else {
						int Escolha = JOptionPane.showConfirmDialog(Frame,
								"Esta partida deu empate sem gols. Desja continuar?", "Atencao", YES_NO_OPTION);
						if (Escolha != 1) {
							Menu.brasileirao.carregarResultadoPeloUsuario(new int[] { ID, GolMan, GolVis });
							ArrayList<Partida> tabPartidasAtualizada = Menu.brasileirao.getTabelaDePartidas();
							for (Partida Item : tabPartidasAtualizada) {
								if (Item.getIdPartida() == ID) {
									Item.setGolsVisitante(0);
									Item.setGolsMandante(0);
									Item.setStatus("REALIZADA");
								}
							}
							Menu.brasileirao.setTabelaDePartidas(tabPartidasAtualizada);
							JOptionPane.showMessageDialog(Frame, "Partida inserida com sucesso.");
							Frame.dispose();
						}
					}
				} catch (Exception Error) {
					JOptionPane.showMessageDialog(Frame, "Voce inseriu algo diferente de numeros no campo de gols.");
				}
			} else {
				JOptionPane.showMessageDialog(Frame, "Voce possui algum campo de gol vazio.");
			}
		});
		Adicionar.addActionListener(e -> {
			DefaultListModel nomeJogSelecionado = (DefaultListModel) marcadoresSelecionados.getModel();
			/** salva em modo chave-valor o nome do time e do jogador. */
			Map.Entry<String, String> Add = new AbstractMap.SimpleEntry(
					comboNomeJogTimesSelecionados.getSelectedItem().toString(),
					comboNomesTimesSelecionados.getSelectedItem().toString());
			Marcador.add(Add);
			nomeJogSelecionado.addElement(Add.getKey());
		});
		comboNomesTimesSelecionados
				.addActionListener(arg0 -> { /** Altera a comboBox de jog quando h� nova selecao na de times. */
					comboNomeJogTimesSelecionados.removeAllItems();
					try {
						String Valor = comboNomesTimesSelecionados.getSelectedItem().toString();
						Time Selecao = Menu.brasileirao.getTimes().stream().filter(Item -> Item.getNome().equals(Valor))
								.findFirst().orElse(null);
						for (Jogador Jogadores : Selecao.getJogadores()) {
							comboNomeJogTimesSelecionados.addItem(Jogadores.getNome());
						}
					} catch (Exception Error) {
						System.out.println("Comando - Iniciar novamente processo.");
					}
				});
		marcadoresSelecionados.addMouseListener(new MouseAdapter() {
			/** Pergunta se quer excluir caso se clique em algum Jog. */
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (scroll.isEnabled()) {
					DefaultListModel nomesJogDaLista = (DefaultListModel) marcadoresSelecionados.getModel();
					if (!nomesJogDaLista.isEmpty()) {
						int Escolha = JOptionPane.showConfirmDialog(Frame, "Voce deseja retirar esse marcador?",
								"Atencao", YES_NO_OPTION);
						if (Escolha != 1) {
							int i = marcadoresSelecionados.locationToIndex(evt.getPoint());
							nomesJogDaLista.remove(i);
							Marcador.remove(i);
						}
					}
				}
			}
		});
		Finalizar.addActionListener(e -> {
			ArrayList<Jogador> Finalizacao = new ArrayList<>();
			long contGolsVisitante = Marcador.stream().filter(Item -> Objects.equals(Item.getValue(), nomeVisitante))
					.count();
			long contGolsMandante = Marcador.stream().filter(Item -> Objects.equals(Item.getValue(), nomeMandante))
					.count();
			if (contGolsMandante == GolMan && contGolsVisitante == GolVis) {
				for (Time Times : Menu.brasileirao.getTimes()) {
					if (Times.getNome().equals(nomeMandante)) {
						for (Map.Entry ItemJogador : Marcador.stream().filter(i -> i.getValue().equals(Times.getNome()))
								.collect(toList())) {
							for (Jogador Jogadores : Times.getJogadores()) {
								if (Jogadores.getNome().equals(ItemJogador.getKey())) {
									Finalizacao.add(Jogadores);
								}
							}
						}
					} else if (Times.getNome().equals(nomeVisitante)) {
						for (Map.Entry ItemJogador : Marcador.stream().filter(i -> i.getValue().equals(Times.getNome()))
								.collect(toList())) {
							for (Jogador Jogadores : Times.getJogadores()) {
								if (Jogadores.getNome().equals(ItemJogador.getKey())) {
									Finalizacao.add(Jogadores);
								}
							}
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(Frame, "Os gols nao condizem com oque foi passado acima.");
				return;
			}
			/**
			 * Cria uma nova ArrayList com os dados atualizados e sobrescreve a tabela de
			 * partidas principal.
			 */
			List<Integer> idJogadores = Finalizacao.stream().map(Jogador::getIdJog).collect(toList());
			idJogadores.add(0, ID); /** Adiciona o id da partida no indice 0 para se adequar a funcao. */
			Menu.brasileirao.carregarResultadoPeloUsuario(new int[] { ID, GolMan, GolVis });
			Menu.brasileirao.carregarMarcadores(idJogadores);
			ArrayList<Partida> tabPartidaAtualizada = Menu.brasileirao.getTabelaDePartidas();
			for (Partida Item : tabPartidaAtualizada) {
				if (Item.getIdPartida() == ID) {
					Item.setGolsVisitante(Integer.parseInt(GolsVis.getText()));
					Item.setGolsMandante(Integer.parseInt(GolsMan.getText()));
					Item.setStatus("REALIZADA");
					Item.setMarcadores(Finalizacao);
				}
			}
			Menu.brasileirao.setTabelaDePartidas(tabPartidaAtualizada);
			JOptionPane.showMessageDialog(Frame, "Partida atualizada com sucesso!.");
			Frame.dispose();
		});
	}

}
