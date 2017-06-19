import javax.swing.*;

/**
 * Created by caroles on 18/06/2017.
 */
public class Interface extends JFrame {

    Tabuleiro tabuleiro;

    public Interface (Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

        JPanel panel = new JPanel(null);
        JButton botaoj1[] = new JButton[6];
        JButton botaoj2[] = new JButton[6];
        Icon casa = new ImageIcon("casinha3.png");
        Icon kalah = new ImageIcon("Kalah1.png");
        //inicializa os botões das casinhas do jogador 2
        for (int i = 0; i < 6; i++) {
            botaoj2[i] = new JButton(casa);
            panel.add(botaoj2[i]);
            botaoj2[i].addActionListener(event -> {
                //define a jogada
            });
            botaoj2[i].setBounds((i*150) + 200, 50, 150, 150);
            botaoj2[i].setText(Integer.toString(tabuleiro.j2[i].nFeijoes));
        }
        //inicializa os botões das casinhas do jogador 1
        for (int i = 0; i < 6; i++) {
            botaoj1[i] = new JButton(casa);
            panel.add(botaoj1[i]);
            botaoj1[i].addActionListener(event -> {
                //define a jogada
            });
            botaoj1[i].setBounds((i*150) + 200, 250, 150, 150);
            botaoj1[i].setText(Integer.toString(tabuleiro.j1[i].nFeijoes));
        }
        JLabel kalah1 = new JLabel(kalah);
        JLabel kalah2 = new JLabel(kalah);
        panel.add(kalah1);
        panel.add(kalah2);
        kalah1.setBounds(25, 115, 150, 200);
        kalah2.setBounds(1125, 115, 150, 200);

        this.setVisible(true);
        this.getContentPane().add(panel);
    }



}
