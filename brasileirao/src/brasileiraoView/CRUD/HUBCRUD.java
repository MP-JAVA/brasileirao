package brasileiraoView.CRUD;

import brasileiraoView.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HUBCRUD {

    private JButton Jogadores,Tecnicos;

    public HUBCRUD() {
        JFrame frame = new JFrame("HUB CRUD");
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

    private JPanel titulo(){
        JPanel Titulo = new JPanel();
        Titulo.setBorder(new EmptyBorder(15, 15, 15, 15));
        JLabel Frase = new JLabel("HUB CRUD");
        Frase.setFont(new Font("Arial", Font.PLAIN, 25));
        Titulo.add(Frase);
        return Titulo;
    }

    private JPanel botoes(){
        Jogadores = new JButton("Jogadores");
        JPanel Botoes = new JPanel(new GridLayout(6, 1, 0, 15));
        Botoes.add(Jogadores);
        Botoes.setAlignmentY(Component.CENTER_ALIGNMENT);
        acoes();
        return Botoes;
    }

    private void acoes(){
        Jogadores.addActionListener(e -> new Jogadores());
    }

}
