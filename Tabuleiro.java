import java.io.IOException;

/**
 * Created by caroles on 30/05/2017.
 */
public class Tabuleiro {
    Casinha[] j1;
    Casinha[] j2;
    Kalah k1;
    Kalah k2;

    public Tabuleiro () {
        j1 = new Casinha[6];
        j2 = new Casinha[6];
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
        k1 = new Kalah();
        k2 = new Kalah();
    }
    
    public int totalKalah(){
    	return 40 - (k1.nFeijoes + k2.nFeijoes);
    }

    public int jogada (int jogador, Casinha[] cJogador, Kalah kJogador, Casinha[] oponente, int casinha) throws IOException {
    	int quantidade = cJogador[casinha].nFeijoes;
    	cJogador[casinha].nFeijoes = 0;
    	boolean flagOponente = false;
    	boolean kalah = false;
    	int fei;
    	// checa se a casinha final vai estar vazia
    	casinha++;

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

    }
    
    @Override
    
    public java.lang.String toString(){
    	String r;
    	
    	r = "      ";
    	
    	for(int i=5; i>=0; i--) r += j2[i].nFeijoes+ "  ";
    	
    	r += "\n| "+k2.nFeijoes+" |                  | "+k1.nFeijoes+" |\n";
    	
    	r += "      ";
    	
    	for(int i=0; i<6; i++) r += j1[i].nFeijoes+ "  ";
    	
    	r += "\n";
    	
    	return r;
    }

}
