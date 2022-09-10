package brasileiraoView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Partidas {

    public Partidas(){
        JFrame frame = new JFrame("Partidas");
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(titulo(), BorderLayout.NORTH);
        panel.add(listagemDePartidas(), BorderLayout.CENTER);
        frame.setSize(800,600);
        frame.setMinimumSize(new Dimension(300, 400));
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
    }

    public JPanel titulo(){
        JPanel Titulo = new JPanel();
        Titulo.setBorder(new EmptyBorder(15, 15, 15, 15));
        JLabel Frase = new JLabel("Tabela de partidas");
        Frase.setFont(new Font("Arial", Font.PLAIN, 25));
        Titulo.add(Frase);
        return Titulo;
    }

    public JScrollPane listagemDePartidas(){
        JTable tabelaDePartidas = new JTable(Menu.brasileirao.apresentarPartidas(),
                new String[]{"ID", "Status", "Mandante", "Visitante", "Resultado", "Gols"}){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        JScrollPane barraRolagem = new JScrollPane(tabelaDePartidas);
        return barraRolagem;
    }

}
