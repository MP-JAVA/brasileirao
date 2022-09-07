package brasileiraoView.CRUD;

import brasileirao.controll.Campeonato;
import brasileirao.model.Jogador;
import brasileirao.model.Time;
import brasileiraoView.Menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static config.configuracoes.indexByID;
import static config.configuracoes.layoytconstr;

public class Jogadores {

    private ArrayList<Jogador> ListaJogadores;
    private final JFrame Frame;
    private JList ViewJogadores;
    private JComboBox Clube, Posicao;
    private JPanel Testezinho;
    private JButton Deletar,Adicionar,Finalizar;
    private JTextField ID, Nome;
    private int Index = Jogador.idGeralJog;

    public void iniciar_componentes(){
        ID = new JTextField();
        ID.setEnabled(false);
        Nome = new JTextField();
        ViewJogadores = new JList<>();
        ViewJogadores.setModel(new DefaultListModel());
        Clube = new JComboBox(Menu.brasileirao.getTimes().stream().map(Time::getNome).toArray());
        Posicao = new JComboBox();
        Deletar = new JButton("Deletar");
        Adicionar = new JButton("Adicionar");
        Finalizar = new JButton("Finalizar");
        Testezinho = hub();
    }

    public Jogadores(){
        Frame = new JFrame("HUB Jogadores");
        JPanel panel = new JPanel(new BorderLayout());
        iniciar_componentes();
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(titulo(), BorderLayout.NORTH);
        panel.add(Testezinho, BorderLayout.CENTER);
        Frame.setSize(800,600);
        Frame.setMinimumSize(new Dimension(300, 400));
        Frame.setLocationRelativeTo(null);
        Frame.add(panel);
        Frame.setVisible(true);
        metodos();
        acoes();
    }

    private void metodos() {
        ListaJogadores =  Menu.brasileirao.getJogadores();
        DefaultListModel model = (DefaultListModel) ViewJogadores.getModel();
        model.removeAllElements();
        ArrayList<Object> Posicoes = new ArrayList<>();
        for(Jogador item: ListaJogadores){
            try{
                Posicoes.add(item.getPosicao());
                model.addElement(String.format("%s | %s | %s",item.getIdJog(),item.getNome(),item.getTime()));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        Posicao.setModel(new javax.swing.DefaultComboBoxModel(Posicoes.stream().distinct().toArray()));
        ID.setText("");
        Nome.setText("");
        Clube.setSelectedIndex(0);
        Posicao.setSelectedIndex(0);
        Deletar.setEnabled(false);
    }

    public JPanel titulo(){
        JPanel Titulo = new JPanel();
        Titulo.setBorder(new EmptyBorder(15, 15, 15, 15));
        JLabel Frase = new JLabel("Artilharia");
        Frase.setFont(new Font("Arial", Font.PLAIN, 25));
        Titulo.add(Frase);
        return Titulo;
    }

    public JPanel hub(){
        JPanel HUB = new JPanel(new GridBagLayout());
        HUB.add(new JLabel("ID",SwingConstants.CENTER), layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 1,0,1,1));
        HUB.add(ID, layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 1,1,1,1));
        HUB.add(Adicionar, layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 2,1,1,1));
        HUB.add(new JLabel("Nome",SwingConstants.CENTER), layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 1,2,2,1));
        HUB.add(Nome, layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 1,3,2,1));
        HUB.add(new JLabel("Posicao",SwingConstants.CENTER), layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 1,4,2,1));
        HUB.add(Posicao, layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 1,5,2,1));
        HUB.add(new JLabel("Clube",SwingConstants.CENTER), layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 1,6,2,1));
        HUB.add(Clube, layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 1,7,2,1));
        HUB.add(Finalizar, layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 1,8,2,1));
        HUB.add(Deletar, layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 1,9,2,1));
        HUB.add(new JLabel("Jogadores",SwingConstants.CENTER), layoytconstr(GridBagConstraints.HORIZONTAL,
                1, 0, 0,0,1,1));
        HUB.add(new JScrollPane(ViewJogadores), layoytconstr(GridBagConstraints.BOTH,
                1, 0, 0,1,1,10));
        return HUB;
    }

    private void acoes(){
        ViewJogadores.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int i = ViewJogadores.locationToIndex(evt.getPoint());
                Jogador Item = ListaJogadores.get(i);
                ID.setText(String.valueOf(Item.getIdJog()));
                Nome.setText(Item.getNome());
                Clube.setSelectedItem(Item.getTime());
                Posicao.setSelectedItem(Item.getPosicao());
                Deletar.setEnabled(true);
            }
        });
        Adicionar.addActionListener(e->{
            Deletar.setEnabled(false);
            ID.setText(String.valueOf(Index));
            Nome.setText("");
            Clube.setSelectedIndex(0);
            Posicao.setSelectedIndex(0);
        });
        Finalizar.addActionListener(e->{
            if(!Nome.getText().equals("")){
                int idTime = Menu.brasileirao.getTimeByName(Clube.getSelectedItem().toString());
                if(ID.getText().equals(String.valueOf(Index))){
                    Jogador Novo = new Jogador(Nome.getText(),Posicao.getSelectedItem().toString());
                    Novo.setIdTime(idTime);
                    Novo.setTime();
                    Campeonato.times.get(idTime).getJogadores().add(Novo);
                    Index = Jogador.idGeralJog;
                    JOptionPane.showMessageDialog(Frame,
                            "Cadastro de jogador realizado com sucesso.");
                }else{
                    int idByList = indexByID(ID.getText(),ListaJogadores);
                    Jogador Update = ListaJogadores.get(idByList);
                    int lastTime = Update.getIdTime();
                    Update.setPosicao(Posicao.getSelectedItem().toString());
                    Update.setNome(Nome.getText());
                    if(lastTime!=idTime){
                        int indexInLastTime = indexByID(ID.getText(),Campeonato.times.get(lastTime).getJogadores());
                        Update.setIdTime(idTime);
                        Update.setTime();
                        Campeonato.times.get(idTime).getJogadores().add(Update);
                        Campeonato.times.get(lastTime).getJogadores().remove(indexInLastTime);
                    }else{
                        int idIndexTime = indexByID(ID.getText(),Campeonato.times.get(idTime).getJogadores());
                        Campeonato.times.get(idTime).getJogadores().set(idIndexTime,Update);
                    }
                    JOptionPane.showMessageDialog(Frame,
                            "Jogador atualizado com sucesso.");
                }
                metodos();
            }else{
                JOptionPane.showMessageDialog(Frame,
                        "Seu campo de nome esta vazio, por favor, registre o nome do seu jogador.");
            }
        });
        Deletar.addActionListener(e->{
            Jogador Update = ListaJogadores.get(indexByID(ID.getText(),ListaJogadores));
            int indexInLastTime = indexByID(ID.getText(),Campeonato.times.get(Update.getIdTime()).getJogadores());
            Campeonato.times.get(Update.getIdTime()).getJogadores().remove(indexInLastTime);
            JOptionPane.showMessageDialog(Frame,
                    "Jogador excluido com sucesso.");
            metodos();
        });
    }

}
