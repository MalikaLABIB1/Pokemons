package MG2D.geometrie;

import MG2D.Couleur;

public class Rectangle extends Dessin {
    private Couleur couleur;
    private Point position;
    private int largeur, hauteur;
    private boolean plein;

    public Rectangle(Couleur couleur, Point position, int largeur, int hauteur, boolean plein) {
        this.couleur = couleur;
        this.position = position;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.plein = plein;
    }

    // Add these getters
    public Couleur getCouleur() { return couleur; }
    public boolean isPlein() { return plein; }
    public int getLargeur() { return largeur; }
    public int getHauteur() { return hauteur; }

    @Override public Point getPosition() { return position; }
    @Override public void setPosition(Point p) { this.position = p; }
}