/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
