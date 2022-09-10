package brasileiraoView;

import brasileirao.controll.Campeonato;
import brasileirao.model.Funcionario;
import brasileirao.model.Jogador;
import brasileirao.model.Time;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static config.configuracoes.posJogEmUmaLista;
import static config.configuracoes.posComponentes;

public class Jogadores {

    private ArrayList<Jogador> ListaJogadores;
    private final JFrame Frame;
    private JList ViewJogadores; //Lista dos jogadores
    private JComboBox Clube, Posicao; //Caixa de selecao de clubes/posicao
    private JPanel panelPrincipal;
    private JButton Deletar,Adicionar,Finalizar;
    private JTextField ID, Nome;
    private int Index = Jogador.idGeralJog; //Controla o numero de jogadores que ja foram criados.
    
    public Jogadores(){
        Frame = new JFrame("HUB Jogadores");
        JPanel panel = new JPanel(new BorderLayout());
        iniciar_componentes();
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(titulo(), BorderLayout.NORTH);
        panel.add(panelPrincipal, BorderLayout.CENTER);
        Frame.setSize(800,600);
        Frame.setMinimumSize(new Dimension(300, 400));
        Frame.setLocationRelativeTo(null);
        Frame.add(panel);
        Frame.setVisible(true);
        metodos();
        acoes();
    }

    public void iniciar_componentes(){ //Apenas inicia os componentes.
        ID = new JTextField();
        ID.setEnabled(false); //Desativa o componente para edicao do usuario.
        Nome = new JTextField();
        ViewJogadores = new JList<>();
        ViewJogadores.setModel(new DefaultListModel());
        Clube = new JComboBox();
        Posicao = new JComboBox();
        Deletar = new JButton("Deletar");
        Adicionar = new JButton("Adicionar");
        Finalizar = new JButton("Finalizar");
        panelPrincipal = hub();
    }

    private void metodos() { 
        ArrayList<String> Posicoes = new ArrayList<>();
        DefaultListModel dadosNomeTime = (DefaultListModel) ViewJogadores.getModel();
        dadosNomeTime.removeAllElements();
        ListaJogadores =  Menu.brasileirao.getJogadores();
        ListaJogadores.sort(Comparator.comparing(Funcionario::getTime)); //Ordena os jogadores em ordem alfa de time.
        for(Jogador item: ListaJogadores){
            Posicoes.add(item.getPosicao());
            dadosNomeTime.addElement(String.format("%s - %s",item.getNome(),item.getTime()));
        }
        //Resgata o nome de cada time na ArrayList times
        Object[] Times = Campeonato.times.stream().map(Time::getNome).toArray();
        Arrays.sort(Times);
        Clube.setModel(new DefaultComboBoxModel(Times));
        //Retira multiplicidades de ocorrencias e transforma a Arraylist em Array
        Posicao.setModel(new DefaultComboBoxModel(Posicoes.stream().distinct().toArray()));
        ID.setText(String.valueOf(Index));
        Nome.setText("");
        Clube.setSelectedIndex(0);
        Posicao.setSelectedIndex(0);
        Deletar.setEnabled(false);
    }

    public JPanel titulo(){
        JPanel Titulo = new JPanel();
        Titulo.setBorder(new EmptyBorder(15, 15, 15, 15));
        JLabel Frase = new JLabel("Jogadores");
        Frase.setFont(new Font("Arial", Font.PLAIN, 25));
        Titulo.add(Frase);
        return Titulo;
    }

    public JPanel hub(){
        JPanel HUB = new JPanel(new GridBagLayout());
        HUB.add(new JLabel("ID",SwingConstants.CENTER), posComponentes(GridBagConstraints.HORIZONTAL,
                1, 0, 1,0,1,1));
        HUB.add(ID, posComponentes(GridBagConstraints.HORIZONTAL,
                1, 0, 1,1,1,1));
        HUB.add(Adicionar, posComponentes(GridBagConstraints.HORIZONTAL,
                1, 0, 2,1,1,1));
        HUB.add(new JLabel("Nome",SwingConstants.CENTER), posComponentes(GridBagConstraints.HORIZONTAL,
                1, 0, 1,2,2,1));
        HUB.add(Nome, posComponentes(GridBagConstraints.HORIZONTAL,
                1, 0, 1,3,2,1));
        HUB.add(new JLabel("Posicao",SwingConstants.CENTER), posComponentes(GridBagConstraints.HORIZONTAL,
                1, 0, 1,4,2,1));
        HUB.add(Posicao, posComponentes(GridBagConstraints.HORIZONTAL,
                1, 0, 1,5,2,1));
        HUB.add(new JLabel("Clube",SwingConstants.CENTER), posComponentes(GridBagConstraints.HORIZONTAL,
                1, 0, 1,6,2,1));
        HUB.add(Clube, posComponentes(GridBagConstraints.HORIZONTAL,
                1, 0, 1,7,2,1));
        HUB.add(Finalizar, posComponentes(GridBagConstraints.HORIZONTAL,
                1, 0, 1,8,2,1));
        HUB.add(Deletar, posComponentes(GridBagConstraints.HORIZONTAL,
                1, 0, 1,9,2,1));
        HUB.add(new JLabel("Jogadores",SwingConstants.CENTER), posComponentes(GridBagConstraints.HORIZONTAL,
                1, 0, 0,0,1,1));
        HUB.add(new JScrollPane(ViewJogadores), posComponentes(GridBagConstraints.BOTH,
                1, 0, 0,1,1,10));
        return HUB;
    }

    private void acoes(){
        ViewJogadores.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int posicao = ViewJogadores.locationToIndex(evt.getPoint());
                Jogador selecionado = ListaJogadores.get(posicao);
                ID.setText(String.valueOf(selecionado.getIdJog()));
                Nome.setText(selecionado.getNome());
                Clube.setSelectedItem(selecionado.getTime());
                Posicao.setSelectedItem(selecionado.getPosicao());
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
                int idTimeNovo = Menu.brasileirao.idTimePeloNome(Clube.getSelectedItem().toString());
                int posNovoTimeEmTimes = Menu.brasileirao.posTimeNaListaDeTimes(idTimeNovo);
                //se o id estiver marcando um numero igual ao index (controle de jogadores criado) é caso de adicionar.
                if(ID.getText().equals(String.valueOf(Index))){
                	//se addJogador retornar true, cria-se o jogador e atualiza-se o valor do index
                	if(Menu.brasileirao.addJogador(idTimeNovo,Nome.getText(),Posicao.getSelectedItem().toString())){
                        Index = Jogador.idGeralJog;
                        JOptionPane.showMessageDialog(Frame,
                                "Cadastro de jogador realizado com sucesso.");
                    }else{
                        JOptionPane.showMessageDialog(Frame,
                                "Algo de errado aconteceu com o seu cadastro.");
                    }
                }else{
                	//Se não for caso de adicionar, faz-se o update.
                    int posDoJogEmJogadores = posJogEmUmaLista(ID.getText(),ListaJogadores);
                    Jogador jogEmUpdate = ListaJogadores.get(posDoJogEmJogadores);
                    int idTimeOriginal = jogEmUpdate.getIdTime();
                    jogEmUpdate.setPosicao(Posicao.getSelectedItem().toString());
                    jogEmUpdate.setNome(Nome.getText());
                    if(idTimeOriginal!=idTimeNovo){ //id do time original e do time novo são diferentes -> troca de times.
                        jogEmUpdate.setIdTime(idTimeNovo);
                        jogEmUpdate.setTime();
                        Campeonato.times.get(posNovoTimeEmTimes).getJogadores().add(jogEmUpdate);
                        int posJogNaListaDeJogDoTimeOriginal = posJogEmUmaLista(ID.getText(),
                                Campeonato.times.get(Menu.brasileirao.posTimeNaListaDeTimes(idTimeOriginal)).getJogadores());
                        Campeonato.times.get(Menu.brasileirao.posTimeNaListaDeTimes(idTimeOriginal))
                        .getJogadores()
                        .remove(posJogNaListaDeJogDoTimeOriginal);
                    }else{ //update sem troca de time.
                        int idIndexTime = posJogEmUmaLista(ID.getText(),Campeonato.times.get(posNovoTimeEmTimes).getJogadores());
                        Campeonato.times.get(posNovoTimeEmTimes).getJogadores().set(idIndexTime,jogEmUpdate);
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
        	Jogador Selecionado = ListaJogadores.get(posJogEmUmaLista(ID.getText(),ListaJogadores));
            int posTimeNaListaTimes = Menu.brasileirao.posTimeNaListaDeTimes(Selecionado.getIdTime());
            if(Menu.brasileirao.deleteJogador(posTimeNaListaTimes, Selecionado.getIdJog())) {
            	 JOptionPane.showMessageDialog(Frame,
                         "Jogador excluido com sucesso.");
            }else {
            	 JOptionPane.showMessageDialog(Frame,
                         "Erro ao excluir jogador.");
            }
            metodos();
        });
    }

}
