import java.io.IOException;
import javax.swing.*;
import java.awt.event.WindowEvent;

/**
 * Created by Ana Maia, Beatriz Monteiro, carolesj, Giovana Craveiro on 18/06/2017.
 * Roda a interface do jogo
 */
public class Interface extends JFrame {

    Tabuleiro tabuleiro;
    Kalah k1;
    Kalah k2;
    Computador IA;
    Icones icones = new Icones();
    int nivel;
    int Comp = 0;

    /**
     * Atualiza os valores quando mudados
     * */
    public void atualiza(JButton[] botaoj1, JButton[] botaoj2, JLabel kalah1, JLabel kalah2) {
        for (int i = 0; i < 6; i++) {
            botaoj1[i].setText(Integer.toString(tabuleiro.j1[i].nFeijoes));
            botaoj2[i].setText(Integer.toString(tabuleiro.j2[i].nFeijoes));
        }
        kalah1.setText(Integer.toString(k1.nFeijoes));
        kalah2.setText(Integer.toString(k2.nFeijoes));
    }

    /**
     * Define qual é o nível da IA
     * */
    public void decidirnivel(int i) {
        if(this.Comp == 1) this.nivel = i; 
    }

    /**
     * Define se o jogo será de um ou dois jogadores
     * */
    public void decidirjogadores(int i) {
        this.Comp = i;
    }

    /**
     * Mostra janela pop up que pergunta se o jogador deseja jogar de novo.
     * Se sim, reinicia o jogo, se não, sai do jogo.
     * */
    public void querJogarNovamente(JButton j1[], JButton j2[], JLabel k1, JLabel k2) {
        //recebe o resultado da janela pop up (0 para sim)
        int resposta = JOptionPane.showConfirmDialog(null, "Você quer jogar novamente?",
                "Acabou!", JOptionPane.YES_NO_OPTION);
        // reinicia  o jogo se o sim foi clicado
        if (resposta == 0) {
            tabuleiro.reinicia();
            for (int i = 0; i < 6; i++) {
                j1[i].setIcon(icones.casinhas[tabuleiro.j1[i].nFeijoes]);
                j2[i].setIcon(icones.casinhas[tabuleiro.j2[i].nFeijoes]);
            }
            k1.setIcon(icones.kalahs[0]);
            k2.setIcon(icones.kalahs[0]);
            atualiza(j1, j2, k1, k2);
        //caso contrário, fecha a janela, o que encerra o processo
        } else {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    /**
     * Contém toda a interface. As decisões de tamanho foram feitas por tentativa e erro
     * */
    public Interface(Tabuleiro tabuleiro, Kalah k1, Kalah k2, Computador IA) throws IOException {
        this.tabuleiro = tabuleiro;
        this.k1 = k1;
        this.k2 = k2;
        this.IA = IA;
        int i;
        //quando a janela é fechada, o programa se encerra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //define o painel, os vetores de botões de cada jogador e os ícones
        JPanel panel = new JPanel(null);
        JButton botaoj1[] = new JButton[6];
        JButton botaoj2[] = new JButton[6];

        Icon feijaodance = new ImageIcon("feijaodancante.gif"); 
        Icon umjogador = new ImageIcon("1jogador.png");
        Icon doisjogadores = new ImageIcon("2jogadores.png");
        JLabel feijaod = new JLabel(feijaodance);
        JLabel feijaod2 = new JLabel(feijaodance);
        JLabel kalah1 = new JLabel(icones.kalahs[0]);
        JLabel kalah2 = new JLabel(icones.kalahs[0]);

        //botões para o nível
        JButton botaoniveis[] = new JButton[3];
        //botão pra selecionar  2 jogadores ou contra o computador
        JButton botaoplayer[] = new JButton[2];

        //Adiciona no painel os botões de níveis e de selecionar número de jogadores
        //e define os action listeners
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
                    
                    tabuleiro.turno=1;

                });


            }

            botaoniveis[i].addActionListener(event -> {
                decidirnivel(nivel);

            });
            botaoniveis[i].setBounds(((5 - i) * 150), 550, 110, 110);
        }

        //Define os botões do lado do jogador 2, seus ícones, seus action listeners
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
                        //verifica o número de feijoes da casinha e define os ícones
                        botaoj1[k].setIcon(icones.casinhas[indicecasinha > 4 ? 5 : indicecasinha]);
                        indicecasinha = tabuleiro.j2[k].nFeijoes;
                        botaoj2[k].setIcon(icones.casinhas[indicecasinha > 4 ? 5 : indicecasinha]);
                    }

                    int indicekalah = tabuleiro.k2.nFeijoes;
                    kalah2.setIcon(icones.kalahs[indicekalah > 4 ? 5 : indicekalah]);

                }
                if (!tabuleiro.temFeijoes() || tabuleiro.limiteKalah()) {
                    panel.add(feijaod);
                    panel.add(feijaod2);
                    //verifica qual kalah tem mais feijões para definir o vencedor
                    int Vencedor = tabuleiro.k1.nFeijoes < tabuleiro.k2.nFeijoes ? 2 : 1;
                    JOptionPane.showMessageDialog(null, "O jogador "+ Vencedor + " venceu!!",
                            "Parabéns!", JOptionPane.PLAIN_MESSAGE);
                    querJogarNovamente(botaoj1, botaoj2, kalah1, kalah2);
                    panel.remove(feijaod);
                    panel.remove(feijaod2);
                }
            });
            botaoj2[i].setBounds(((5 - i) * 150) + 200, 50, 150, 150);

            botaoj2[i].setText(Integer.toString(tabuleiro.j2[i].nFeijoes));

        }
        //Define os botões do lado do jogador 1, seus ícones, seu action listeners
        for (i = 0; i < 6; i++) {
            if (i == 2 || i == 3) botaoj1[i] = new JButton(icones.casinhas[4]);
            else botaoj1[i] = new JButton(icones.casinhas[3]);
            panel.add(botaoj1[i]);
            final int j = i; 
 
            botaoj1[i].addActionListener(event -> {

                if (tabuleiro.temFeijoes() && !tabuleiro.limiteKalah()) {
                    tabuleiro.jogada(1, tabuleiro.j1, tabuleiro.k1, tabuleiro.j2, j, Comp, nivel, IA, tabuleiro);

                    atualiza(botaoj1, botaoj2, kalah1, kalah2);


                    for (int k = 0; k < 6; k++) {
                        int indicecasinha = tabuleiro.j1[k].nFeijoes;
                        //verifica o número de feijões da casinha e define os ícones
                        botaoj1[k].setIcon(icones.casinhas[indicecasinha > 4 ? 5 : indicecasinha]);
                        indicecasinha = tabuleiro.j2[k].nFeijoes;
                        botaoj2[k].setIcon(icones.casinhas[indicecasinha > 4 ? 5 : indicecasinha]);
                    }

                    int indicekalah = tabuleiro.k1.nFeijoes;
                    kalah1.setIcon(icones.kalahs[indicekalah > 4 ? 5 : indicekalah]);

                    //Define as jogadas da IA caso o jogador tenha feito essa opção
                    while (tabuleiro.turno == 2 && Comp == 1) {

                        int casinha = -1;

                        if (nivel == 3) casinha = IA.escolheCasinhaN3(tabuleiro);
                        else if (nivel == 2) casinha = IA.escolheCasinhaN2(tabuleiro);
                        else casinha = IA.escolheCasinhaN1(tabuleiro); 

                        tabuleiro.jogada(2, tabuleiro.j2, tabuleiro.k2, tabuleiro.j1, casinha, 1, nivel, IA, tabuleiro);
 
                        atualiza(botaoj1, botaoj2, kalah1, kalah2);

                        for (int k = 0; k < 6; k++) {
                            int indicecasinha = tabuleiro.j1[k].nFeijoes;
                            //verifica o número de feijões da casinha e define os ícones
                            botaoj1[k].setIcon(icones.casinhas[indicecasinha > 4 ? 5 : indicecasinha]);
                            indicecasinha = tabuleiro.j2[k].nFeijoes;
                            botaoj2[k].setIcon(icones.casinhas[indicecasinha > 4 ? 5 : indicecasinha]);
                        }

                        indicekalah = tabuleiro.k2.nFeijoes;
                        kalah2.setIcon(icones.kalahs[indicekalah > 4 ? 5 : indicekalah]);

                    }

                }
                //Verifica o fim do jogo
                if (!tabuleiro.temFeijoes() || tabuleiro.limiteKalah()) {
                    panel.add(feijaod);
                    panel.add(feijaod2);
                    //verifica qual kalah tem mais feijões para definir o vencedor
                    int Vencedor = tabuleiro.k1.nFeijoes < tabuleiro.k2.nFeijoes ? 2 : 1;
                    JOptionPane.showMessageDialog(null, "O jogador "+ Vencedor + " venceu!!",
                            "Parabéns!", JOptionPane.PLAIN_MESSAGE);
                    querJogarNovamente(botaoj1, botaoj2, kalah1, kalah2);
                    panel.remove(feijaod);
                    panel.remove(feijaod2);
                }

            });
            //define os tramanhos dos botões do jogador 1 e seus textos
            botaoj1[i].setBounds((i * 150) + 200, 250, 150, 150);
            botaoj1[i].setText(Integer.toString(tabuleiro.j1[i].nFeijoes));

        }

        //adiciona as labels de kalah e o texto com o número de feijões no painel
        panel.add(kalah1);
        kalah1.setText(Integer.toString(k1.nFeijoes));
        panel.add(kalah2);
        kalah2.setText(Integer.toString(k2.nFeijoes));

        //define seus tamanhos
        kalah1.setBounds(1125, 115, 150, 200);
        kalah2.setBounds(25, 115, 150, 200);

        //adiciona um gif de feijão dançante quando o jogo acaba
        feijaod.setBounds(10, 260, 500, 500);
        feijaod2.setBounds(800,270,500,500);

        //define o tamanho inicial da janela
        this.setSize(1300, 720);
        //deixa a frame visível
        this.setVisible(true);
        this.getContentPane().add(panel);
    }

}
