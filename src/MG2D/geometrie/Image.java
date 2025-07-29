// MG2D/geometrie/Image.java
package MG2D.geometrie;

import MG2D.Couleur;

public class Image extends Dessin {
    private Couleur couleur;
    private String chemin;
    private Point position;
    private int largeur, hauteur;

    public Image(Couleur couleur, String chemin, Point position) {
        this.couleur = couleur;
        this.chemin = chemin;
        this.position = position;
    }

    public void redimensionner(int largeur, int hauteur) {
    this.largeur = largeur;
    this.hauteur = hauteur;
}

    @Override public Point getPosition() { return position; }
    @Override public void setPosition(Point p) { this.position = p; }

    public Couleur getCouleur() {
        return couleur;
    }

    public String getChemin() {
        return chemin;
    }
    
    public int getLargeur() { return largeur; }
    public int getHauteur() { return hauteur; }

    public void setCouleur(Couleur couleur2) {
        this.couleur=couleur2;
    }
}