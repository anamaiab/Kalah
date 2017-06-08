import java.io.IOException;

/**
 * Created by caroles on 30/05/2017.
 */
public class Jogadores {
	
    /*public void jogada (int jogador, Casinha[] cJogador, Kalah kJogador,
                       Casinha[] oponente, int casinha) throws IOException {
        int quantidade = cJogador[casinha].nFeijoes;
        boolean flagOponente = false;
        boolean kalah = false;
        int fei;
        // checa se a casinha final vai estar vazia
        
    
        for (int i = 0; i < quantidade; i++) {
        	
        	if(i == quantidade-1){
        		if(!flagOponente && cJogador[casinha].nFeijoes == 0){
        			fei = oponente[5-casinha].nFeijoes;
        			oponente[5-casinha].nFeijoes = 0;
        			kJogador.nFeijoes += fei;
        		}
        		else if(flagOponente && oponente[casinha].nFeijoes == 0){
        			fei = cJogador[5-casinha].nFeijoes;
        			cJogador[5-casinha].nFeijoes = 0;
        			kJogador.nFeijoes += fei;
        		
        		}
        	}

        	else if (casinha == 6 && !flagOponente) {
                casinha = 0;
                kJogador.nFeijoes++;
                if(i+1 < quantidade) flagOponente = true;
                kalah = true;

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
        	System.out.println("Sua vez novamente, em que casinha você deseja comecar?");
        	casinha = EntradaTeclado.leInt();
        	
        	jogada (jogador, cJogador, kJogador, oponente, casinha);
        	
        	
        	
        }
   
    }*/
}
