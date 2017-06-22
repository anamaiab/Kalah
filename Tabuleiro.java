

import java.io.IOException;

/**
 * Created by Ana Maia, Beatriz Monteiro, carolesj, Giovana Craveiro on 30/05/2017.
 * Classe que representa um tabuleiro de Kalah
 */
public class Tabuleiro {
    public Casinha[] j1;
    public Casinha[] j2;
    public Kalah k1;
    public Kalah k2;
    public int turno = 0;
    //public int atualizado = 1;

    /**
     * Construtor padrão da classe que cria dois vetores de
     * seis casinhas cada e dois Kalahs
     * */
    public Tabuleiro() {
        j1 = new Casinha[6];
        j2 = new Casinha[6];
        k1 = new Kalah();
        k2 = new Kalah();
        reinicia();
    }

    /**
     * Volta o tabuleiro para a condição inicial
     * */
    public void reinicia() {
        this.turno = 0;
        for (int i = 0; i < 6; i++) {
            j1[i] = new Casinha();
            j2[i] = new Casinha();
            if (i != 2 && i != 3) {
                j1[i].nFeijoes = 3;
                j2[i].nFeijoes = 3;
            } else {
                j1[i].nFeijoes = 4;
                j2[i].nFeijoes = 4;
            }
        }
        k1.reinicia();
        k2.reinicia();
    }

    /**
     * Verifica quantos feijões há nos Kalahs e se ainda há possibilidade
     * de jogo
     * */
    public boolean limiteKalah() {
        if (k1.nFeijoes > 20 || k2.nFeijoes > 20) {
            return true;
        }
        return false;
    }

    /**
     * Faz a jogada baseada na casa escolhida
     * @param jogador o jogador atual
     * @param cJogador casinhas do jogador atual
     * @param kJogador kalah do jogador atual
     * @param oponente casinhas do oponente
     * @param casinha a casinha escolhida na última jogada
     * */
    public void jogada(int jogador, Casinha[] cJogador, Kalah kJogador, Casinha[] oponente, int casinha, int Comp, int nivel, Computador IA, Tabuleiro tabuleiro) {
        //verifica se é o turno do jogador
        if (jogador != turno) return;
        //verifica se há feijões na casinha escolhida
        if (cJogador[casinha].nFeijoes == 0) return;

        //salva a quantidade de feijões para distribuí-los e zera a casinha
        int quantidade = cJogador[casinha].nFeijoes;
        cJogador[casinha].nFeijoes = 0;
        boolean flagOponente = false;
        boolean kalah = false;
        //feijões que estão do lado oposto
        int fei;
        // checa se a casinha final vai estar vazia
        casinha++;


        for (int i = 0; i < quantidade; i++) {
            // se for o último feijão e não for em nenhum kalah
            if (i == quantidade - 1 && casinha != 6) {
                //se o último cair numa casinha vazia, pega todos os da frente e manda pro Kalah
                if (casinha < 6 && !flagOponente && cJogador[casinha].nFeijoes == 0) {
                    fei = oponente[5 - casinha].nFeijoes;
                    oponente[5 - casinha].nFeijoes = 0;
                    kJogador.nFeijoes += fei;
                    cJogador[casinha].nFeijoes++;
                } else if (casinha < 6 && flagOponente && oponente[casinha].nFeijoes == 0) {
                    fei = cJogador[5 - casinha].nFeijoes;
                    cJogador[5 - casinha].nFeijoes = 0;
                    kJogador.nFeijoes += fei;
                    oponente[casinha].nFeijoes++;

                //avança na casinha apenas se o i não for o último, para conseguir usar a da frente
                } else if (casinha < 6 && flagOponente) {
                    if (oponente[casinha].nFeijoes == 0 && i == quantidade) oponente[casinha].nFeijoes++;
                    else {
                        oponente[casinha].nFeijoes++;
                        casinha++;
                    }
                } else if (casinha < 6 && !flagOponente) {
                    if (cJogador[casinha].nFeijoes == 0 && i == quantidade) cJogador[casinha].nFeijoes++;
                    else {
                        cJogador[casinha].nFeijoes++;
                        casinha++;
                    }

                }
            //se for a última casinha do lado do tabuleiro do jogador atual, verifica
            //se ainda há feijões para dar a volta no tabuleiro
            } else if (casinha == 6 && !flagOponente) {
                casinha = 0;
                kJogador.nFeijoes++;
                if (i == quantidade - 1) kalah = true;
                flagOponente = true;
            } else if (casinha == 6) {
                casinha = 0;
                cJogador[casinha].nFeijoes++;
                flagOponente = false;
                if (i == quantidade - 1 && cJogador[casinha].nFeijoes == 1) {
                    fei = oponente[5].nFeijoes;
                    oponente[5].nFeijoes = 0;
                    kJogador.nFeijoes += fei;
                }
            } else if (!flagOponente) {
                if (cJogador[casinha].nFeijoes == 0 && i == quantidade)
                    cJogador[casinha].nFeijoes++;    //nunca vai acontecer
                else {
                    cJogador[casinha].nFeijoes++;
                    casinha++;
                }
            } else if (flagOponente) {
                if (oponente[casinha].nFeijoes == 0 && i == quantidade)
                    oponente[casinha].nFeijoes++;    // nunca vai acontecer
                else {
                    oponente[casinha].nFeijoes++;
                    casinha++;
                }
            }

        }
        //troca o turno dos jogadores
        if (kalah) {
            turno = jogador;
        } else {
            if (jogador == 1) turno = 2;
            else turno = 1;
        }
    }

    @Override
    /**
     * Imprime o tabuleiro de forma visual. Método usado no começo
     * da implementação.
     * */
    public java.lang.String toString() {
        String r;

        r = "      ";

        for (int i = 5; i >= 0; i--) r += j2[i].nFeijoes + "  ";

        r += "\n| " + k2.nFeijoes + " |                  | " + k1.nFeijoes + " |\n";

        r += "      ";

        for (int i = 0; i < 6; i++) r += j1[i].nFeijoes + "  ";

        r += "\n";

        return r;
    }

    /**
     * Verifica se algum dos lados do tabuleiro está vazio
     * @return false se algum dos dois jogadores não tem mais feijões
     * */
    public boolean temFeijoes() {
        int jogador1 = 0, jogador2 = 0;

        //percorre o vetor de casinhas de cada jogador e soma quantos
        //feijões existem de cada lado
        for (int i = 0; i < 6; i++) {
            jogador1 += j1[i].nFeijoes;
            jogador2 += j2[i].nFeijoes;
        }
        if (jogador1 == 0 || jogador2 == 0) {
            return false;
        } else {
            return true;
        }
    }
}

 