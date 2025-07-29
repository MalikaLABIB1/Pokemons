package MG2D.geometrie;

import MG2D.Couleur;
import java.awt.Font;

public class Texte extends Dessin {
    private Couleur couleur;
    private String texte;
    private Font police;
    private Point position;

    public Texte(Couleur couleur, String texte, Font police, Point position) {
        this.couleur = couleur;
        this.texte = texte;
        this.police = police;
        this.position = position;
    }

    // Add these getters
    public Couleur getCouleur() { return couleur; }
    public String getTexte() { return texte; }
    public Font getPolice() { return police; }

    @Override public Point getPosition() { return position; }
    @Override public void setPosition(Point p) { this.position = p; }

    public void setTexte(String message) {
    this.texte = message;  // Update the text content
     // Optional: Update text dimensions if needed
    // If using MG2D's Texte class, you might need:
    // super.setTexte(message);
}
}