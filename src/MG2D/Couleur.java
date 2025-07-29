// MG2D/Couleur.java
package MG2D;

public class Couleur {
    public static final Couleur NOIR = new Couleur(0, 0, 0);
    public static final Couleur BLANC = new Couleur(255, 255, 255);
    public static final Couleur GRIS = new Couleur(128, 128, 128);
    public static final Couleur TRANSPARENT = new Couleur(0, 0, 0, 0);
    
    private final int r, g, b, a;
    
    public Couleur(int r, int g, int b) {
        this(r, g, b, 255);
    }
    
    public Couleur(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    
    // Getters
    public int getR() { return r; }
    public int getG() { return g; }
    public int getB() { return b; }
    public int getA() { return a; }
}