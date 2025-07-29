// MG2D/Fenetre.java
package MG2D;

import javax.swing.JFrame;

public class Fenetre extends JFrame {
    private Panneau p;
    
    public Fenetre(String titre, int largeur, int hauteur) {
        super(titre);
        p = new Panneau();
        setContentPane(p);
        setSize(largeur, hauteur);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public Panneau getP() { return p; }
}