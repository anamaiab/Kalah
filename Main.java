/**
 * Created by Ana Maia, Beatriz Monteiro, carolesj, Giovana Craveir on 30/05/2017.
 */

import java.io.IOException;


public class Main {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //System.out.println("BEM VINDO AO KALAH");
        int casinha;
        int jogador = 1;
        Tabuleiro jogo = new Tabuleiro();
        Computador IA = new Computador();
        Interface i = new Interface(jogo, jogo.k1, jogo.k2, IA);
    }

}
