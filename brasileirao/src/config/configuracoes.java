package config;

import brasileirao.model.Jogador;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class configuracoes {

    public static GridBagConstraints posComponentes(int crescimento,
    		int largura,
    		int altura,
    		int posColuna,
    		int posLinha,
    		int largEmColunas,
    		int altEmLinhas){
        GridBagConstraints Constraints = new GridBagConstraints();
        Constraints.gridheight = altEmLinhas;
        Constraints.weightx = largura;
        Constraints.gridwidth = largEmColunas;
        Constraints.insets = new Insets(5,5,5,5);
        Constraints.gridx = posColuna;
        Constraints.gridy = posLinha;
        Constraints.weighty = altura;
        Constraints.fill = crescimento;
        return Constraints;
    }

    public static int posJogEmUmaLista(String ID, ArrayList<Jogador> Jogadores){
        return IntStream.range(0, Jogadores.size())
                .filter(i -> ID.equals(String.valueOf(Jogadores.get(i).getIdJog()))).findFirst().orElse(0);
    }

}
