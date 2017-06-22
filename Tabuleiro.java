

import java.io.IOException;

/**
 * Created by caroles on 30/05/2017.
 */
public class Tabuleiro {
    public Casinha[] j1;
    public Casinha[] j2;
    public Kalah k1;
    public Kalah k2;
    public int turno = 1;
    //public int atualizado = 1;

    public Tabuleiro() {
        j1 = new Casinha[6];
        j2 = new Casinha[6];
        k1 = new Kalah();
        k2 = new Kalah();
        reinicia();
    }

    public void reinicia() {
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

    public boolean limiteKalah() {
        if (k1.nFeijoes > 20 || k2.nFeijoes > 20) {
            return true;
        }
        return false;
    }

   /* public int jogada (int jogador, Casinha[] cJogador, Kalah kJogador, Casinha[] oponente, int casinha) throws IOException {
        int quantidade = cJogador[casinha].nFeijoes;
    	cJogador[casinha].nFeijoes = 0;
    	boolean flagOponente = false;
    	boolean kalah = false;
    	int fei;
    	// checa se a casinha final vai estar vazia
    	casinha++;

      */


    public void jogada(int jogador, Casinha[] cJogador, Kalah kJogador, Casinha[] oponente, int casinha, int Comp, int nivel, Computador IA, Tabuleiro tabuleiro) {
        if (jogador != turno) return;
        //if(tabuleiro.limiteKalah() || !tabuleiro.temFeijoes()) return;
        if (cJogador[casinha].nFeijoes == 0) return;


        int quantidade = cJogador[casinha].nFeijoes;
        cJogador[casinha].nFeijoes = 0;
        boolean flagOponente = false;
        boolean kalah = false;
        int fei;
        // checa se a casinha final vai estar vazia
        casinha++;

        for (int i = 0; i < quantidade; i++) {

            if (i == quantidade - 1 && casinha != 6) {// se for o último feijão e não for em nenhum kalah
                if (casinha < 6 && !flagOponente && cJogador[casinha].nFeijoes == 0) {
                    //System.out.println("chegou");
                    fei = oponente[5 - casinha].nFeijoes;
                    oponente[5 - casinha].nFeijoes = 0;
                    kJogador.nFeijoes += fei;
                    cJogador[casinha].nFeijoes++;
                } else if (casinha < 6 && flagOponente && oponente[casinha].nFeijoes == 0) {
                    fei = cJogador[5 - casinha].nFeijoes;
                    cJogador[5 - casinha].nFeijoes = 0;
                    kJogador.nFeijoes += fei;
                    oponente[casinha].nFeijoes++;

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
            } else if (casinha == 6 && !flagOponente) {//se for a última casinha do lado do tabuleiro do jogador atual
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

        if (kalah) { // joga de novo
            //System.out.println("Sua vez novamente");
            turno = jogador;
            //return jogador;

        } else {
            if (jogador == 1) turno = 2;// return 2;
            else turno = 1;//return 1;
        }


        //atualizado = 1;
         /*if (turno == 2 && Comp == 1){
                if(nivel == 3)casinha = IA.escolheCasinhaN3(tabuleiro);
                else if(nivel == 2) casinha = IA.escolheCasinhaN2(tabuleiro);
                else casinha = IA.escolheCasinhaN1(tabuleiro);
                tabuleiro.jogada (2, tabuleiro.j2, tabuleiro.k2, tabuleiro.j1, casinha,1,nivel,IA,tabuleiro);
                atualizado = 0;
                
        }*/

    }
        
        
       /*
        for (int i = 0; i < quantidade; i++) {
	
    		if(i == quantidade-1 && casinha != 6){
    			if(casinha < 6 && !flagOponente && cJogador[casinha].nFeijoes == 0){
    				fei = oponente[5-casinha].nFeijoes;
    				oponente[5-casinha].nFeijoes = 0;
    				kJogador.nFeijoes += fei;
    			}
    			else if(casinha < 6 && flagOponente && oponente[casinha].nFeijoes == 0){
    				fei = cJogador[5-casinha].nFeijoes;
    				cJogador[5-casinha].nFeijoes = 0;
    				kJogador.nFeijoes += fei;
		
    			}
    			else if(casinha < 6 && flagOponente){
        			oponente[casinha].nFeijoes++;
        			casinha++;
        		}
    			else if(casinha < 6 && !flagOponente) {
        			cJogador[casinha].nFeijoes++;
        			casinha++;
        		}
    		}
    		else if(casinha == 6 && !flagOponente){
    			casinha = 0;
    			kJogador.nFeijoes++;
    			if(i == quantidade - 1 )kalah = true;
    			if(i+1 < quantidade) flagOponente = true;
    			
    		}
    		else if(casinha == 6){
    			casinha = 0;
    			flagOponente = false;
    		}
    		else if(!flagOponente) {
    			cJogador[casinha].nFeijoes++;
    			casinha++;
    		}
    		else if(flagOponente){
    			oponente[casinha].nFeijoes++;
    			casinha++;
    		}
 
    	}
    	
    	if (kalah) { // joga de novo
    		System.out.println("Sua vez novamente");
    		
    		return jogador;
	
    	}
    	else{
    		if(jogador == 1) return 2;
    		else return 1;
    	}

    }*/

    @Override

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

    public boolean temFeijoes() {
        int jogador1 = 0, jogador2 = 0;

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

 