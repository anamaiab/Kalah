/**
 * Created by caroles on 30/05/2017.
 */
public class Jogadores {

    public int jogada (int jogador, Casinha[] cJogador, Kalah kJogador,
                       Casinha[] oponente, int casinha) {
        int quantidade = cJogador[casinha].nFeijoes;
        boolean flagOponente = false;
        boolean kalah = false;

        for (int i = 0; i < quantidade; i++) {
            if (casinha == 6 && !flagOponente) {
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
            else{
                oponente[casinha].nFeijoes++;
                casinha++;
            }

        }
        if () {

        }
    }
}
