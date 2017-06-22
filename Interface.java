import java.io.IOException;
import javax.swing.*;
import java.awt.event.WindowEvent;

/**
 * Created by caroles on 18/06/2017.
 */
public class Interface extends JFrame {

    Tabuleiro tabuleiro;
    Kalah k1;
    Kalah k2;
    Computador IA;
    Icones icones = new Icones();
    int nivel;
    int Comp = 0;

    public void atualiza(JButton[] botaoj1, JButton[] botaoj2, JLabel kalah1, JLabel kalah2) {
        for (int i = 0; i < 6; i++) {
            botaoj1[i].setText(Integer.toString(tabuleiro.j1[i].nFeijoes));
            botaoj2[i].setText(Integer.toString(tabuleiro.j2[i].nFeijoes));
        }
        kalah1.setText(Integer.toString(k1.nFeijoes));
        kalah2.setText(Integer.toString(k2.nFeijoes));
    }

    public void decidirnivel(int i) {
        this.nivel = i;
        this.Comp = 1;
    }

    public void decidirjogadores(int i) {
        this.Comp = i;
    }

    public void querJogarNovamente(JButton j1[], JButton j2[], JLabel k1, JLabel k2) {
        int resposta = JOptionPane.showConfirmDialog(null, "Você quer jogar novamente?",
                "Acabou!", JOptionPane.YES_NO_OPTION);
        // reinicia tudão
        if (resposta == 0) {
            tabuleiro.reinicia();
            for (int i = 0; i < 6; i++) {
                j1[i].setIcon(icones.casinhas[tabuleiro.j1[i].nFeijoes]);
                j2[i].setIcon(icones.casinhas[tabuleiro.j2[i].nFeijoes]);
            }
            k1.setIcon(icones.kalahs[0]);
            k2.setIcon(icones.kalahs[0]);
            atualiza(j1, j2, k1, k2);
        } else {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    public Interface(Tabuleiro tabuleiro, Kalah k1, Kalah k2, Computador IA) throws IOException {
        this.tabuleiro = tabuleiro;
        this.k1 = k1;
        this.k2 = k2;
        this.IA = IA;
        int i;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(null);
        JButton botaoj1[] = new JButton[6];
        JButton botaoj2[] = new JButton[6];

        Icon feijaodance = new ImageIcon("feijaodancante.gif");
        Icon vezjogador1 = new ImageIcon("vezjogador1.png");
        Icon vezjogador2 = new ImageIcon("vezjogador2.png");
        Icon umjogador = new ImageIcon("1jogador.png");
        Icon doisjogadores = new ImageIcon("2jogadores.png");
        JLabel feijaod = new JLabel(feijaodance);
        JLabel kalah1 = new JLabel(icones.kalahs[0]);
        JLabel kalah2 = new JLabel(icones.kalahs[0]);

        JButton botaoniveis[] = new JButton[3];//botões para o nível
        JButton botaoplayer[] = new JButton[2];//botao pra selecionar  2 jogadores ou contra o computador
        //inicializa os botões das casinhas do jogador 2

        for (i = 0; i < 3; i++) {
            botaoniveis[i] = new JButton(icones.iconeniveis[i]);
            panel.add(botaoniveis[i]);
            final int nivel = i;

            if (i < 2) {
                if (i == 0) {
                    botaoplayer[i] = new JButton(doisjogadores);
                    botaoplayer[i].setBounds(420, 420, 220, 100);
                } else {
                    botaoplayer[i] = new JButton(umjogador);
                    botaoplayer[i].setBounds(660, 420, 220, 100);
                }

                panel.add(botaoplayer[i]);

                botaoplayer[i].addActionListener(event -> {
                    decidirjogadores(nivel);

                });


            }

            botaoniveis[i].addActionListener(event -> {
                decidirnivel(nivel);

            });

            botaoniveis[i].setBounds(((5 - i) * 150), 550, 110, 110);

            // botaon[i].setText(Integer.toString(tabuleiro.j2[i].nFeijoes));

        }


        for (i = 0; i < 6; i++) {
            if (i == 2 || i == 3) botaoj2[i] = new JButton(icones.casinhas[4]);
            else botaoj2[i] = new JButton(icones.casinhas[3]);
            panel.add(botaoj2[i]);
            final int j = i;


            botaoj2[i].addActionListener(event -> {


                if (!tabuleiro.limiteKalah() && tabuleiro.temFeijoes()) {
                    tabuleiro.jogada(2, tabuleiro.j2, tabuleiro.k2, tabuleiro.j1, j, Comp, nivel, IA, tabuleiro);

                    atualiza(botaoj1, botaoj2, kalah1, kalah2);

                    for (int k = 0; k < 6; k++) {
                        int indicecasinha = tabuleiro.j1[k].nFeijoes;
                        //verifica o numero de feijoes da casinha e define os ícones
                        botaoj1[k].setIcon(icones.casinhas[indicecasinha > 4 ? 5 : indicecasinha]);
                        indicecasinha = tabuleiro.j2[k].nFeijoes;
                        botaoj2[k].setIcon(icones.casinhas[indicecasinha > 4 ? 5 : indicecasinha]);
                    }

                    int indicekalah = tabuleiro.k2.nFeijoes;
                    kalah2.setIcon(icones.kalahs[indicekalah > 4 ? 5 : indicekalah]);

                }
                if (!tabuleiro.temFeijoes() || tabuleiro.limiteKalah()) {
                    panel.add(feijaod);
                    //verifica qual kalah tem mais feijões para definir o vencedor
                    int Vencedor = tabuleiro.k1.nFeijoes < tabuleiro.k2.nFeijoes ? 2 : 1;
                    JOptionPane.showMessageDialog(null, "O jogador "+ Vencedor + " venceu!!",
                            "Parabens!", JOptionPane.PLAIN_MESSAGE);
                    querJogarNovamente(botaoj1, botaoj2, kalah1, kalah2);
                    panel.remove(feijaod);
                }
            });
            botaoj2[i].setBounds(((5 - i) * 150) + 200, 50, 150, 150);

            botaoj2[i].setText(Integer.toString(tabuleiro.j2[i].nFeijoes));

        }
        //inicializa os botões das casinhas do jogador 1

        for (i = 0; i < 6; i++) {
            if (i == 2 || i == 3) botaoj1[i] = new JButton(icones.casinhas[4]);
            else botaoj1[i] = new JButton(icones.casinhas[3]);
            panel.add(botaoj1[i]);
            final int j = i;
            final int aux = 1;
            //final int aux2 = casinha;
            //final int casinha;


            botaoj1[i].addActionListener(event -> {

                if (tabuleiro.temFeijoes() && !tabuleiro.limiteKalah()) {
                    tabuleiro.jogada(1, tabuleiro.j1, tabuleiro.k1, tabuleiro.j2, j, Comp, nivel, IA, tabuleiro);

                    atualiza(botaoj1, botaoj2, kalah1, kalah2);


                    for (int k = 0; k < 6; k++) {
                        int indicecasinha = tabuleiro.j1[k].nFeijoes;
                        //verifica o numero de feijoes da casinha e define os ícones
                        botaoj1[k].setIcon(icones.casinhas[indicecasinha > 4 ? 5 : indicecasinha]);
                        indicecasinha = tabuleiro.j2[k].nFeijoes;
                        botaoj2[k].setIcon(icones.casinhas[indicecasinha > 4 ? 5 : indicecasinha]);
                    }

                    int indicekalah = tabuleiro.k1.nFeijoes;
                    kalah1.setIcon(icones.kalahs[indicekalah > 4 ? 5 : indicekalah]);

                    while (tabuleiro.turno == 2 && Comp == 1) {

                        int casinha = -1;

                        if (nivel == 3) casinha = IA.escolheCasinhaN3(tabuleiro);
                        else if (nivel == 2) casinha = IA.escolheCasinhaN2(tabuleiro);
                        else casinha = IA.escolheCasinhaN1(tabuleiro);
                        //System.out.println("entrein " + casinha);

                        tabuleiro.jogada(2, tabuleiro.j2, tabuleiro.k2, tabuleiro.j1, casinha, 1, nivel, IA, tabuleiro);
                        //atualizado = 0;
                        //System.out.println("sei lah neh");
                        atualiza(botaoj1, botaoj2, kalah1, kalah2);

                        for (int k = 0; k < 6; k++) {
                            int indicecasinha = tabuleiro.j1[k].nFeijoes;
                            //verifica o numero de feijoes da casinha e define os ícones
                            botaoj1[k].setIcon(icones.casinhas[indicecasinha > 4 ? 5 : indicecasinha]);
                            indicecasinha = tabuleiro.j2[k].nFeijoes;
                            botaoj2[k].setIcon(icones.casinhas[indicecasinha > 4 ? 5 : indicecasinha]);
                        }

                        indicekalah = tabuleiro.k2.nFeijoes;
                        kalah2.setIcon(icones.kalahs[indicekalah > 4 ? 5 : indicekalah]);

                    }

                }
                if (!tabuleiro.temFeijoes() || tabuleiro.limiteKalah()) {
                    panel.add(feijaod);
                    //verifica qual kalah tem mais feijões para definir o vencedor
                    int Vencedor = tabuleiro.k1.nFeijoes < tabuleiro.k2.nFeijoes ? 2 : 1;
                    JOptionPane.showMessageDialog(null, "O jogador "+ Vencedor + " venceu!!",
                            "Parabens!", JOptionPane.PLAIN_MESSAGE);
                    querJogarNovamente(botaoj1, botaoj2, kalah1, kalah2);
                    panel.remove(feijaod);
                }

            });
            botaoj1[i].setBounds((i * 150) + 200, 250, 150, 150);
            botaoj1[i].setText(Integer.toString(tabuleiro.j1[i].nFeijoes));

        }
        panel.add(kalah1);
        kalah1.setText(Integer.toString(k1.nFeijoes));
        panel.add(kalah2);
        kalah2.setText(Integer.toString(k2.nFeijoes));

        kalah1.setBounds(1125, 115, 150, 200);
        kalah2.setBounds(25, 115, 150, 200);

        feijaod.setBounds(100, 260, 500, 500);

        this.setSize(1300, 720);
        this.setVisible(true);
        this.getContentPane().add(panel);
    }

}
