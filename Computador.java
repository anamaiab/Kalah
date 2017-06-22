/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;

/**
 * Created by Ana Maia Baptistão, Caroline Jesuíno, Beatriz Monteiro e Giovana Craveiro on 13/06/2017.
 * 
 *  Classe usada para implementar a IA
 */

public class Computador {

    public Computador() {

    }
    
    /**
     * Verifica qual a maior casinha que uma jogada do opoente pode chegar
     * 
     * @param oponente: vetor de casinhas do usuário*/
    
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

    /**
     * Verifica se existe alguma jogada cuja ultimo feijao caia no kalah
     * 
     * @param j2: vetor de casinhas da IA*/
    
    int checaKalah(Casinha[] j2) {

        for (int i = 0; i < 6; i++) {
            if (j2[i].nFeijoes + i == 6) return i;
        }

        return -1;
    }

    /**Verifica se existe alguma jogada cuja ultimo feijao caia sobre uma casinha do seu lado do tabuleiro
     * 
     * @param j1: vetor de casinhas do usuario
     * @param j2: vetor de casinhas da IA
     * @param casaOP: casinha vazia do usuário*/
    
    int checaJogadaOponente(Casinha[] j1, Casinha[] j2, int casaOp) {
        for (int i = 0; i < 6; i++) {
            if ((j2[i].nFeijoes + i) - 7 == casaOp) return i;
        }
        return -1;
    }

    /**Verifica se existe alguma jogada cuja ultimo feijao caia sobre uma casinha do lado do tabuleiro da IA
     * 
     * @param j2: vetor de casinhas da IA
     * @param casa: casinha vazia encontrada em j2*/
    
    int checaJogada(Casinha[] j2, int casa) {
        for (int i = 0; i < 6; i++) {
            if ((j2[i].nFeijoes + i) == casa) return i;
        }

        return -1;
    }

    /**Gera um número aleatorio entre 0 e 5
     * @param casinhas: vetor de casinhas da IA*/
    
    private int escolheCasaRand(Casinha[] casinhas) {
        Random rand = new Random();
        int max = casinhas.length, casa = rand.getIntRand(max), i;
        
        for (i = 0; casinhas[casa].nFeijoes == 0 && i < 15; i++) {
            casa = rand.getIntRand(max);
        }
        
        // se o getinRand gerar 15 casinhas vazias, o vetor é percorrido e é escolhida a primeira casinha com feijoes
        if (i == 15) {
            for (i = 0; casinhas[i].nFeijoes == 0; i++);
            casa = i;
        }
        return casa;
    }

    
    /**Escolhe casinha para o nivel 1
     * 
     * @param jogo: tabuleiro*/
    
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
       
        /*se uma jogada do oponente chegar no lado oposto do tabuleiro*/
        if (max != -1 && jogo.j1[max].nFeijoes + max > 6) {

            casa = (jogo.j1[max].nFeijoes + max) - 6;

            if (casa != -1 && (casa + 1) <= 5 && jogo.j2[casa + 1].nFeijoes > 0) return casa + 1;
            
            /*caso nao encontre um valor valido no lado da IA, é gerada uma casinha aleatoria*/
            else {
                return escolheCasaRand(jogo.j2);
            }
        } else {
            return escolheCasaRand(jogo.j2);
        }

    }

    
    /**Escolhe casinha para o nivel 2
     * 
     * @param jogo: tabuleiro*/
    
    int escolheCasinhaN2(Tabuleiro jogo) {

        int K = checaKalah(jogo.j2);

        for (int i = 0; i < 6; i++) {
            
        	/*Descobre se existe alguma casinha vazia no vetor do usuario*/
            if (jogo.j1[i].nFeijoes == 0 && jogo.j2[5 - i].nFeijoes != 0) {
            	/*descobre se há alguma jogada que faz que o ultimo feijao vaia na casa vazia*/

                int casa = checaJogadaOponente(jogo.j1, jogo.j2, i);
                if (casa != -1 && jogo.j2[casa].nFeijoes > 0)
                    return casa;
            }
        	/*Descobre se alguma jogada cai no kalah*/
            if (K != -1) {

                return K;
            }
        }
        /*se nenhuma das condiçoes antriores for atendida, é escolhida uma casinha aleatoria*/
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
            	/*descobre se há alguma jogada que faz que o ultimo feijao vaia na casa vazia do usuario*/
                int casa = checaJogadaOponente(jogo.j1, jogo.j2, i);
                if (casa != -1 && jogo.j2[casa].nFeijoes > 0)
                    return casa;

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

            /*se a casa mais longe que o usuario pode chegar é menor que sua última casinha, ele escolje a casinha depois dela*/
            if ((casa + 1) <= 5 && jogo.j2[casa + 1].nFeijoes > 0)
                return casa + 1;
            /*caso contrario, escolhe uma casinha aleatoria*/
            else {
                return escolheCasaRand(jogo.j2);
            }
        }
        // se a maior jogada do oponente nao chegar no tabuleiro da ia, é escolhida uma casinha aleatoria
        return escolheCasaRand(jogo.j2);
    }
}