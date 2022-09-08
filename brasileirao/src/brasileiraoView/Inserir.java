package brasileiraoView;

import brasileirao.model.Jogador;
import brasileirao.model.Partida;
import brasileirao.model.Time;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

import static config.configuracoes.layoytconstr;
import static java.util.stream.Collectors.toList;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class Inserir {

    private JComboBox TimeMandante, TimeVisitante, ComboTimesSelecionados, ComboJogadores;
    private JButton Verificacao, Computar, Adicionar, Finalizar;
    private int ID,GolMan,GolVis;
    private JTextField GolsMan,GolsVis;
    private JFrame Frame;
    private JPanel PanelGols, PanelMarcadores;
    private ArrayList<Map.Entry<String,String>> Marcador;
    private String Mandante,Visitante;
    private JScrollPane ItensGols;
    private JList ListMarcadores;

    public Inserir(){
        Frame = new JFrame("Inserir resultados");
        iniciarcomponentes();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(titulo(), BorderLayout.NORTH);
        panel.add(processo(), BorderLayout.CENTER);
        iniciarmetodos();
        acoes();
        Frame.setSize(1000,800);
        Frame.setMinimumSize(new Dimension(1000, 800));
        Frame.setLocationRelativeTo(null);
        Frame.add(panel);
        Frame.setVisible(true);
    }

    public void iniciarcomponentes(){
    	Object[] Modelo = Menu.brasileirao.getTimes().stream().map(Time::getNome).toArray();
        Arrays.sort(Modelo);
        GolsMan = new JTextField();
        GolsVis = new JTextField();
        Computar = new JButton("Computar");
        ComboTimesSelecionados = new JComboBox();
        ComboJogadores = new JComboBox();
        Adicionar = new JButton("Adicionar");
        DefaultListModel<String> model = new DefaultListModel<>();
        ListMarcadores = new JList(model);
        Finalizar = new JButton("Finalizar");
        TimeMandante = new JComboBox(Modelo);
        TimeVisitante = new JComboBox(Modelo);
        Verificacao = new JButton("Verificacao");
        ItensGols = new JScrollPane(ListMarcadores);
        PanelGols = gols();
        PanelMarcadores = marcadores();
    }

    public void iniciarmetodos(){
        habilitar_componentes(PanelGols,false);
        habilitar_componentes(PanelMarcadores,false);
    }

    public JPanel titulo(){
        JPanel Titulo = new JPanel();
        Titulo.setBorder(new EmptyBorder(15, 15, 15, 15));
        JLabel Frase = new JLabel("Inserir resultados");
        Frase.setFont(new Font("Arial", Font.PLAIN, 25));
        Titulo.add(Frase);
        return Titulo;
    }

    public JPanel gols(){
        JPanel Insercao = new JPanel(new GridBagLayout());
        Insercao.add(new JLabel("Gols do time mandante: "), layoytconstr(GridBagConstraints.HORIZONTAL,
                0, 0, 0,0,1,1));
        Insercao.add(GolsMan, layoytconstr(GridBagConstraints.HORIZONTAL,
                2, 0, 1,0,1,1));
        Insercao.add(new JLabel("Gols do time visitante: "), layoytconstr(GridBagConstraints.HORIZONTAL,
                0, 0, 0,1,1,1));
        Insercao.add(GolsVis, layoytconstr(GridBagConstraints.HORIZONTAL,
                2, 0, 1,1,1,1));
        Insercao.add(Computar, layoytconstr(GridBagConstraints.HORIZONTAL,
                2, 0, 0,2,2,1));
        return Insercao;
    }

    public JPanel marcadores(){
        JPanel Insercao = new JPanel(new GridBagLayout());
        Insercao.add(new JLabel("Times",SwingConstants.CENTER), layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 0,0,1,1));
        Insercao.add(ComboTimesSelecionados, layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 0,1,1,1));
        Insercao.add(new JLabel("Jogadores",SwingConstants.CENTER), layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 0,2,1,1));
        Insercao.add(ComboJogadores, layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 0,3,1,1));
        Insercao.add(Adicionar, layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 0,4,1,1));
        Insercao.add(new JLabel("Selecionados",SwingConstants.CENTER), layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 1,0,1,1));
        Insercao.add(ItensGols, layoytconstr(GridBagConstraints.BOTH,
                1, 0, 1,1,1,4));
        Insercao.add(Finalizar, layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 0,5,2,1));
        return Insercao;
    }

    public JPanel processo(){
        JPanel Insercao = new JPanel(new GridBagLayout());
        Insercao.add(new JLabel("Time mandante",SwingConstants.CENTER), layoytconstr(GridBagConstraints.HORIZONTAL,
                3, 0, 0,0,1,1));
        Insercao.add(TimeMandante, layoytconstr(GridBagConstraints.HORIZONTAL,
                3, 0, 0,1,1,1));
        Insercao.add(new JLabel("Time visitante",SwingConstants.CENTER), layoytconstr(GridBagConstraints.HORIZONTAL,
                3, 0, 0,2,1,1));
        Insercao.add(TimeVisitante, layoytconstr(GridBagConstraints.HORIZONTAL,
                3, 0, 0,3,1,1));
        Insercao.add(Verificacao, layoytconstr(GridBagConstraints.HORIZONTAL,
                3, 0, 0,4,1,1));
        Insercao.add(PanelGols, layoytconstr(GridBagConstraints.HORIZONTAL,
                3, 0, 0,5,1,1));
        Insercao.add(PanelMarcadores, layoytconstr(GridBagConstraints.HORIZONTAL,
                3, 0, 0,6,1,1));
        return Insercao;
    }

    private void habilitar_componentes(JPanel Panel, boolean isEnable){
        Component[] com = Panel.getComponents();
        for(Component Item: com){
            Item.setEnabled(isEnable);
        }
    }

    private void acoes(){
        Verificacao.addActionListener(e->{
            if(Finalizar.isEnabled()){
                ComboTimesSelecionados.removeAllItems();
                habilitar_componentes(PanelMarcadores,false);
            }
            ArrayList<Partida> IDs = new ArrayList<>();
            for(Partida Item:Menu.brasileirao.getTabelaDePartidas()){
                if(Item.getTimeVisitante().equals(TimeVisitante.getSelectedItem().toString())
                        && Item.getTimeMandante().equals(TimeMandante.getSelectedItem().toString())){
                    if(Item.getStatus().equals("PENDENTE")){
                        IDs.add(Item);
                    }
                }
            }
            if(IDs.size()!=1){
                if(IDs.size()>1){
                    Object[] Itens = (IDs.stream().map(Item->String.format("%s",Item.getIdPartida())).toArray());
                    Object Selecionado = JOptionPane.showInputDialog(null, "Escolha um item" , "Selecao de itens" ,
                            JOptionPane.PLAIN_MESSAGE , null ,Itens,"");
                    if(Selecionado!=null){
                        ID = Integer.parseInt(Selecionado.toString());
                    }else{
                        return;
                    }
                }else{
                    JOptionPane.showMessageDialog(Frame,
                            "Esta partida nao existe ou ja foi realizada.");
                    return;
                }
            }else{
                ID = IDs.get(0).getIdPartida();
            }
            Mandante = TimeMandante.getSelectedItem().toString();
            Visitante = TimeVisitante.getSelectedItem().toString();
            GolsVis.setText("0");
            GolsMan.setText("0");
            habilitar_componentes(PanelGols,true);
        });
        Computar.addActionListener(e->{
            if(!GolsVis.getText().equals("") && !GolsVis.getText().equals("")){
                try{
                    GolVis = Integer.parseInt(GolsVis.getText());
                    GolMan = Integer.parseInt(GolsMan.getText());
                    if(GolVis != 0 || GolMan!=0){
                        Marcador = new ArrayList<>();
                        ComboTimesSelecionados.addItem(TimeMandante.getSelectedItem().toString());
                        ComboTimesSelecionados.addItem(TimeVisitante.getSelectedItem().toString());
                        habilitar_componentes(PanelGols,false);
                        habilitar_componentes(PanelMarcadores,true);
                    }else{
                        int Escolha = JOptionPane.showConfirmDialog(Frame,"Esta partida deu empate. Desja continuar?", "Atencao", YES_NO_OPTION );
                        if(Escolha!=1){
                            Menu.brasileirao.carregarResultadoPeloUsuario(new int[]{ID,GolMan,GolVis});
                            ArrayList<Partida> Atualizada = Menu.brasileirao.getTabelaDePartidas();
                            for(Partida Item:Atualizada){
                                if(Item.getIdPartida()==ID){
                                    Item.setGolsVisitante(0);
                                    Item.setGolsMandante(0);
                                    Item.setStatus("REALIZADA");
                                }
                            }
                            Menu.brasileirao.setTabelaDePartidas(Atualizada);
                            JOptionPane.showMessageDialog(Frame,
                                    "Partida inserida com sucesso.");
                            Frame.dispose();
                        }
                    }
                }catch (Exception Error){
                    JOptionPane.showMessageDialog(Frame,
                            "Voce inseriu algo diferente de numeros no campo de gols.");
                }
            }else{
                JOptionPane.showMessageDialog(Frame,
                        "Voce possui algum campo de gol vazio.");
            }
        });
        Adicionar.addActionListener(e->{
            DefaultListModel lm2 = (DefaultListModel) ListMarcadores.getModel();
            Map.Entry<String,String> Add = new AbstractMap.SimpleEntry(ComboJogadores.getSelectedItem().toString(),
                    ComboTimesSelecionados.getSelectedItem().toString());
            Marcador.add(Add);
            lm2.addElement(Add.getKey());
        });
        ComboTimesSelecionados.addActionListener(arg0 -> {
            ComboJogadores.removeAllItems();
            try{
                String Valor = ComboTimesSelecionados.getSelectedItem().toString();
                Time Selecao = Menu.brasileirao.getTimes().stream().filter(Item->Item.getNome().equals(Valor))
                        .findFirst().orElse(null);
                for(Jogador Jogadores:Selecao.getJogadores()){
                    ComboJogadores.addItem(Jogadores.getNome());
                }
            }catch (Exception Error){
                System.out.println("Comando - Iniciar novamente processo.");
            }
        });
        ListMarcadores.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(ItensGols.isEnabled()){
                    DefaultListModel ModelList = (DefaultListModel) ListMarcadores.getModel();
                    if(!ModelList.isEmpty()){
                        int Escolha = JOptionPane.showConfirmDialog(Frame,"Você deseja retirar esse marcador?", "Atencao", YES_NO_OPTION );
                        if(Escolha!=1){
                            int i = ListMarcadores.locationToIndex(evt.getPoint());
                            ModelList.remove(i);
                            Marcador.remove(i);
                        }
                    }
                }
            }
        });
        Finalizar.addActionListener(e -> {
            ArrayList<Jogador> Finalizacao = new ArrayList<>();
            long CheckGolV = Marcador.stream().filter(Item-> Objects.equals(Item.getValue(), Visitante)).count();
            long CheckGolM = Marcador.stream().filter(Item-> Objects.equals(Item.getValue(), Mandante)).count();
            if(CheckGolM == GolMan && CheckGolV == GolVis){
                for(Time Times:Menu.brasileirao.getTimes()) {
                    if (Times.getNome().equals(Mandante)){
                        for (Map.Entry ItemJogador : Marcador.stream().filter(i -> i.getValue().equals(Mandante)).collect(toList())) {
                            for (Jogador Jogadores : Times.getJogadores()) {
                                if (Jogadores.getNome().equals(ItemJogador.getKey())) {
                                    Finalizacao.add(Jogadores);
                                }
                            }
                        }
                    }
                    else if(Times.getNome().equals(Visitante)){
                            for (Map.Entry ItemJogador : Marcador.stream().filter(i -> i.getValue().equals(Visitante)).collect(toList())) {
                                for (Jogador Jogadores : Times.getJogadores()) {
                                    if (Jogadores.getNome().equals(ItemJogador.getKey())) {
                                        Finalizacao.add(Jogadores);
                                    }
                                }
                            }
                        }
                    }
            }else{
                JOptionPane.showMessageDialog(Frame,
                        "Os gols nao condizem com oque foi passado acima.");
                return;
            }
            List<Integer> Metodo = Finalizacao.stream().map(Jogador::getIdJog).collect(toList());
            Metodo.add(0,ID);
            Menu.brasileirao.carregarResultadoPeloUsuario(new int[]{ID,GolMan,GolVis});
            Menu.brasileirao.carregarMarcadores(Metodo);
            ArrayList<Partida> Atualizada = Menu.brasileirao.getTabelaDePartidas();
            for(Partida Item:Atualizada){
                if(Item.getIdPartida()==ID){
                    Item.setGolsVisitante(Integer.parseInt(GolsVis.getText()));
                    Item.setGolsMandante(Integer.parseInt(GolsMan.getText()));
                    Item.setStatus("REALIZADA");
                    Item.setMarcadores(Finalizacao);
                }
            }
            Menu.brasileirao.setTabelaDePartidas(Atualizada);
            JOptionPane.showMessageDialog(Frame,
                    "Partida atualizada com sucesso!.");
            Frame.dispose();
        });
    }

}
