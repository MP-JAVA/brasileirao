package brasileiraoView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Artilharia {

    public Artilharia(){
        JFrame frame = new JFrame("Artilharia");
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(titulo(), BorderLayout.NORTH);
        panel.add(tabelaArtilharia(), BorderLayout.CENTER);
        frame.setSize(800,600);
        frame.setMinimumSize(new Dimension(300, 400));
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
    }

    public JPanel titulo(){
        JPanel Titulo = new JPanel();
        Titulo.setBorder(new EmptyBorder(15, 15, 15, 15));
        JLabel Frase = new JLabel("Artilharia");
        Frase.setFont(new Font("Arial", Font.PLAIN, 25));
        Titulo.add(Frase);
        return Titulo;
    }

    public JScrollPane tabelaArtilharia(){
        JTable tabelaDeArtilheiros = new JTable(Menu.brasileirao.imprimirArtilharia(),
        		new String[]{"Jogador", "Time", "Gols"}){ //imprime uma tabela a partir de um vetor de vetores.
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false; //impede que o usuario edite a tabela.
            }
        };
        JScrollPane barraRolagem = new JScrollPane(tabelaDeArtilheiros);
        return barraRolagem;
    }

}
