/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;

/**
 * Created by caroles on 13/06/2017.
 */

public class Computador {

    public Computador() {

    }

    /*verifica qual a maior casinha que uma jogada do opoente pode chegar*/
    int checaMaxOponente(Casinha[] oponente) {
        int casa = -1, max = 0;

        for (int i = 0; i < 6; i++) {
            if (oponente[i].nFeijoes + i > max) {
                casa = i;
                max = oponente[i].nFeijoes + i;
            }
        }

        return casa;
    }

    /*verifica se existe alguma jogada cuja ultimo feijao caia no kalah*/
    int checaKalah(Casinha[] j2) {

        for (int i = 0; i < 6; i++) {
            if (j2[i].nFeijoes + i == 6) return i;
        }

        return -1;
    }

    /*verifica se existe alguma jogada cuja ultimo feijao caia sobre uma casinha do seu lado do tabuleiro*/
    int checaJogadaOponente(Casinha[] j1, Casinha[] j2, int casaOp) {
        for (int i = 0; i < 6; i++) {
            if ((j2[i].nFeijoes + i) - 7 == casaOp) return i;
        }
        return -1;
    }

    /*verifica se existe alguma jogada cuja ultimo feijao caia sobre uma casinha do seu lado do tabuleiro*/
    int checaJogada(Casinha[] j2, int casa) {

        for (int i = 0; i < 6; i++) {

            if ((j2[i].nFeijoes + i) == casa) return i;
        }

        return -1;
    }

    private int escolheCasaRand(Casinha[] casinhas) {
        Random rand = new Random();
        int max = casinhas.length, casa = rand.getIntRand(max), i;
        for (i = 0; casinhas[casa].nFeijoes == 0 && i < 15; i++) {
            casa = rand.getIntRand(max);
        }
        if (i == 15) {
            for (i = 0; casinhas[i].nFeijoes == 0; i++);
            casa = i;
        }
        return casa;
    }

    int escolheCasinhaN1(Tabuleiro jogo)  {


        int K = checaKalah(jogo.j2);

        for (int i = 0; i < 6; i++) {
            /*Descobre se alguma jogada cai no kalah*/
            if (K != -1) {
                return K;
            }
        }
        
        /*descobre qual é a maior casinha que uma jogada do oponente pode chegar e escolhe retirar da casinha depois dela*/

        int casa, max = checaMaxOponente(jogo.j1);
        //System.out.print("Caiu em else\n");
        if (max != -1 && jogo.j1[max].nFeijoes + max > 6) { /*se uma jogada do oponente chegar no lado oposto do tabuleiro*/

            casa = (jogo.j1[max].nFeijoes + max) - 6;

            if (casa != -1 && (casa + 1) <= 5 && jogo.j2[casa + 1].nFeijoes > 0) return casa + 1;
            else {
                return escolheCasaRand(jogo.j2);
            }
        } else {
            return escolheCasaRand(jogo.j2);
        }

    }

    int escolheCasinhaN2(Tabuleiro jogo) {

        int K = checaKalah(jogo.j2);

        for (int i = 0; i < 6; i++) {
            
        	/*Descobre se existe alguma casinha vazia no vetor do usuario*/
            if (jogo.j1[i].nFeijoes == 0 && jogo.j2[5 - i].nFeijoes != 0) {
            	/*descobre se há alguma jogada que faz que o ultimo feijao vaia na casa vazia*/

                int casa = checaJogadaOponente(jogo.j1, jogo.j2, i);
                if (casa != -1 && jogo.j2[casa].nFeijoes > 0)
                    return casa;//loop = jogo.jogada(2, jogo.j2, jogo.k2, jogo.j1, casa);

            }
        	/*Descobre se alguma jogada cai no kalah*/
            if (K != -1) {

                return K;
            }
        }

        return escolheCasaRand(jogo.j2);
    }

    /*verifica as melhores jogadas para serem feitas*/
    int escolheCasinhaN3(Tabuleiro jogo){

        int K = checaKalah(jogo.j2);

        for (int i = 0; i < 6; i++) {
            /*Descobre se existe alguma casinha vazia no vetor do computador*/
            if (jogo.j2[i].nFeijoes == 0 && jogo.j1[5 - i].nFeijoes != 0) {
                int casa = checaJogada(jogo.j2, i);
                if (casa != -1 && jogo.j2[casa].nFeijoes > 0) return casa;
            }
        	/*Descobre se existe alguma casinha vazia no vetor do usuario*/
            if (jogo.j1[i].nFeijoes == 0 && jogo.j2[5 - i].nFeijoes != 0) {
            	/*descobre se há alguma jogada que faz que o ultimo feijao vaia na casa vazia*/
                int casa = checaJogadaOponente(jogo.j1, jogo.j2, i);
                if (casa != -1 && jogo.j2[casa].nFeijoes > 0)
                    return casa;//loop = jogo.jogada(2, jogo.j2, jogo.k2, jogo.j1, casa);

            }
        	/*Descobre se alguma jogada cai no kalah*/
            if (K != -1) {
                return K;
            }
        }
        
        /*descobre qual é a maior casinha que uma jogada do oponente pode chegar e escolhe retirar da casinha depois dela*/

        int casa, max = checaMaxOponente(jogo.j1);

        if (max != -1 && jogo.j1[max].nFeijoes + max > 6) { /*se uma jogada do oponente chegar no lado oposto do tabuleiro*/
            casa = (jogo.j1[max].nFeijoes + max) - 6;

            if ((casa + 1) <= 5 && jogo.j2[casa + 1].nFeijoes > 0)
                return casa + 1;//loop = jogo.jogada(2, jogo.j2, jogo.k2, jogo.j1, casa); /*chama jogo com casa */
            else {
                return escolheCasaRand(jogo.j2);
            }
        }
        return escolheCasaRand(jogo.j2);
    }
}