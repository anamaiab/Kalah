import javax.swing.*;

/**
 * Created by caroles on 18/06/2017.
 */
public class Interface extends JFrame {

    Tabuleiro tabuleiro;
    Jogadores jogador1, jogador2;

    public Interface (Tabuleiro tabuleiro, Jogadores jogador1, Jogadores jogador 2) {
        this.tabuleiro = tabuleiro;
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        for (int i = 0; i < 5; i++) {
            JButton botao = new JButton(Icon icone);
            pannel.add(botao);
            botao.addActionListener(event -> {
                //define a jogada
            }
            //n sera definido
            botao.setBounds(i*n, 50, 350, 300);
        }
    }


}
