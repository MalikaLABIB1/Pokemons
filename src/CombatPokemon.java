
public class CombatPokemon {
    private static final int TAILLE = 9;
    private Pokemon[][] grille;

    public CombatPokemon() {
        grille = new Pokemon[TAILLE][TAILLE];
    }

    public boolean estDansGrille(int x, int y) {
        return x >= 0 && x < TAILLE && y >= 0 && y < TAILLE;
    }

    private boolean estVoisin(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x1 - x2); 
        int dy = Math.abs(y1 - y2); 
        return (dx <= 1) && (dy <= 1) && !(dx == 0 && dy == 0);
    }

    public void placerPokemon(Pokemon p, int x, int y) {
        if (estDansGrille(x, y) && grille[x][y] == null) {
            grille[x][y] = p;
        } else {
           System.out.println("On ne peut pas placer " + p.getNickname() + " à la position (" + x + "," + y + ")");
        }
    }

    public void afficherGrille() {
        System.out.println("\n    0  1  2  3  4  5  6  7  8");
        for (int i = 0; i < TAILLE; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < TAILLE; j++) {
                if (grille[i][j] == null) {
                    System.out.print("[ ]");
                } else {
                    
                    char initial = grille[i][j].getNickname().charAt(0);
                    System.out.print("[" + initial + "]");
                }
            }
            System.out.println();
        }
    }

    public void deplacerPokemon(int x1, int y1, int x2, int y2) {
        if (!estDansGrille(x1, y1) || !estDansGrille(x2, y2)) {
            System.out.println("Coordonnées invalides. Essaie encore !");
            return;
        }

        if (!estVoisin(x1, y1, x2, y2)) {
            System.out.println("Déplacement impossible : tu peux juste avancer d'une case à la fois.");
            return;
        }

        Pokemon p = grille[x1][y1];
        if (p == null) {
            System.out.println("Il n’y a pas de Pokémon à déplacer à la position (" + x1 + "," + y1 + ").");
            return;
        }

        if (grille[x2][y2] == null) {
            grille[x2][y2] = p;  
            grille[x1][y1] = null;
            System.out.println(p.getNickname() + " se déplace en (" + x2 + "," + y2 + ").");
        } else {
            System.out.println("Oops, la case (" + x2 + "," + y2 + ") est déjà occupée.");
        }
    }

    public void attaquer(int x1, int y1, int x2, int y2) {
        if (!estDansGrille(x1, y1) || !estDansGrille(x2, y2)) {
            System.out.println("Coordonnées invalides. Essaie encore !");
            return;
        }

        if (!estVoisin(x1, y1, x2, y2)) {
            System.out.println("Attaque impossible : la cible n’est pas assez proche.");
            return;
        }

        Pokemon attaquant = grille[x1][y1];
        Pokemon cible = grille[x2][y2];

        if (attaquant == null) {
            System.out.println("Tu n’as pas de Pokémon à attaquer ici.");
            return;
        }

        if (cible == null) {
            System.out.println("Il n’y a pas de Pokémon à attaquer sur cette case.");
            return;
        }

        Pokemon.battleRound(attaquant, cible);


        if (!cible.isAlive()) {
            grille[x2][y2] = null;
            System.out.println(cible.getNickname() + " est KO et retiré du terrain.");
        }
    }

    public boolean mewtwoEstVivant() {
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                Pokemon p = grille[i][j];
                if (p != null && p.getPokedexNumber() == 150 && p.isAlive()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Pokemon getPokemon(int x, int y) {
        if (estDansGrille(x, y)) {
            return grille[x][y];
        }
        return null;
    }
}
