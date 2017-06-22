import javax.swing.*;

/**
 * Created by Ana Maia, Beatriz Monteiro, carolesj, Giovana Craveiro on 22/06/2017.
 * Seta os vetores de ícones a serem usados nos botões
 */
public class Icones {
    public Icon casinhas[] = new Icon[6];
    public Icon kalahs[] = new Icon[6];
    public Icon iconeniveis[] = new Icon[3];

    Icones () {
        casinhas[0] = new ImageIcon("casinha3.png");
        casinhas[1] = new ImageIcon("Casinha1f.png");
        casinhas[2] = new ImageIcon("Casinha2f.png");
        casinhas[3] = new ImageIcon("Casinha3f.png");
        casinhas[4] = new ImageIcon("Casinha4f.png");
        casinhas[5] = new ImageIcon("Casinhamf.png");
        kalahs[0] = new ImageIcon("Kalah1.png");
        kalahs[1] = new ImageIcon("kalah1f.png");
        kalahs[2] = new ImageIcon("Kalah2f.png");
        kalahs[3] = new ImageIcon("kalah3.png");
        kalahs[4] = new ImageIcon("Kalah4f.png");
        kalahs[5] = new ImageIcon("kalahm.png");
        iconeniveis[0] = new ImageIcon("nivel1.png");
        iconeniveis[1] = new ImageIcon("nivel2.png");
        iconeniveis[2] = new ImageIcon("nivel3.png");
    }
}
