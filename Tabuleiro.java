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


}
