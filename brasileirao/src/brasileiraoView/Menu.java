package brasileiraoView;

import brasileirao.controll.Campeonato;
import brasileirao.model.Partida;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Menu{

    private static final JButton Classificacao = new JButton("Classificacao");
    private static final JButton Artilharia = new JButton("Artilharia");
    private static final JButton Tabela = new JButton("Tabela de partidas");
    private static final JButton Inserir = new JButton("Inserir resultados");
    private static final JButton Relatorios = new JButton("Relatorios");
    public static Campeonato brasileirao;


    public static JPanel titulo(){
        JPanel Titulo = new JPanel();
        Titulo.setBorder(new EmptyBorder(15, 15, 15, 15));
        JLabel Frase = new JLabel("Brasileirao 2022");
        Frase.setFont(new Font("Arial", Font.PLAIN, 25));
        Titulo.add(Frase);
        return Titulo;
    }

    public static JPanel botoes(){
        JPanel Botoes = new JPanel(new GridLayout(5, 1, 0, 15));
        Botoes.add(Classificacao);
        Botoes.add(Artilharia);
        Botoes.add(Tabela);
        Botoes.add(Inserir);
        Botoes.add(Relatorios);
        Botoes.setAlignmentY(Component.CENTER_ALIGNMENT);
        return Botoes;
    }

    public static void main(String[] args) {
        String caminhoElencos = "./arq_backup/cadastro.csv";
        String caminhoPartidas = "./arq_backup/partidas.csv";
        brasileirao = new Campeonato(caminhoElencos,caminhoPartidas);
        acoes();
        JFrame frame = new JFrame("Brasileirao 2022");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(titulo(), BorderLayout.NORTH);
        panel.add(botoes(), BorderLayout.CENTER);
        frame.setSize(800,600);
        frame.setMinimumSize(new Dimension(300, 400));
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void acoes(){
        Artilharia.addActionListener(e -> new Artilharia());
        Tabela.addActionListener(e -> new Partidas());
        Classificacao.addActionListener(e -> new Classificacao());
        Relatorios.addActionListener(e -> new RelatorioView());
        Inserir.addActionListener(e -> new Inserir());
    }

}
