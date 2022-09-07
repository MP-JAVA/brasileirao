package config;

import brasileirao.model.Jogador;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class configuracoes {

    public static GridBagConstraints layoytconstr(int fill, int weightx, int weighty, int gridx, int gridy, int gridwidth, int gridheight){
        GridBagConstraints Constraints = new GridBagConstraints();
        Constraints.gridheight = gridheight;
        Constraints.weightx = weightx;
        Constraints.gridwidth = gridwidth;
        Constraints.insets = new Insets(5,5,5,5);
        Constraints.gridx = gridx;
        Constraints.gridy = gridy;
        Constraints.weighty = weighty;
        Constraints.fill = fill;
        return Constraints;
    }

    public static int indexByID(String ID, ArrayList<Jogador> Jogadores){
        return IntStream.range(0, Jogadores.size())
                .filter(i -> ID.equals(String.valueOf(Jogadores.get(i).getIdJog()))).findFirst().orElse(0);
    }

}
