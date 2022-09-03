package brasileiraoView;

import brasileirao.controll.Campeonato;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;

public class Classificacao {


    public Classificacao(){
        JFrame frame = new JFrame("Classificacao");
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

    public JPanel titulo(){
        JPanel Titulo = new JPanel();
        Titulo.setBorder(new EmptyBorder(15, 15, 15, 15));
        JLabel Frase = new JLabel("Classificacao");
        Frase.setFont(new Font("Arial", Font.PLAIN, 25));
        Titulo.add(Frase);
        return Titulo;
    }

    public JScrollPane botoes(){
        JTable Teste = new JTable(Menu.brasileirao.imprimirCLassificacao(),
                new String[]{"Classificacao", "Time", "P", "V", "E", "D", "GP", "GC", "SG", "%"}){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        JScrollPane barraRolagem = new JScrollPane(Teste);
        return barraRolagem;
    }

}
