package MG2D.geometrie;

import java.util.ArrayList;
import java.util.List;

public abstract class Jeu {
    protected List<Dessin> listeDessins = new ArrayList<>();

    public void ajouter(Dessin d) {
        listeDessins.add(d);
    }

    public List<Dessin> getListeDessins() {
        return listeDessins;
    }

    protected abstract void dessiner();
    protected abstract void gererClic(int x, int y);

    public void rafraichir() {
        listeDessins.clear();
        dessiner();
    }
}
