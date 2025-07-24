package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainCombat {
    public static void main(String[] args) {
        System.out.println("=== Pokémon Chess – Combat Principal ===");
        CombatPokemon combat = new CombatPokemon();
        Scanner scanner = new Scanner(System.in);
      List<Pokemon> joueur1Pokemons = new ArrayList<>();
        List<Pokemon> joueur2Pokemons = new ArrayList<>();

        initialiserGrille(combat, joueur1Pokemons, joueur2Pokemons);

        jouerCombat(combat, joueur1Pokemons, joueur2Pokemons, scanner);

        scanner.close();
    }
  private static void jouerCombat(CombatPokemon combat, List<Pokemon> joueur1Pokemons,
                                   List<Pokemon> joueur2Pokemons, Scanner scanner) {
        boolean joueur1Tour = true;

        while (combat.mewtwoEstVivant()) {
            combat.afficherGrille();

            System.out.println();
            System.out.println((joueur1Tour ? "Joueur 1" : "Joueur 2") + ", c'est votre tour.");
            System.out.println("Entrez votre action : <x1> <y1> <action> <x2> <y2>");
            System.out.println("Actions possibles : move / attack");
            System.out.println("Tapez 'exit' pour quitter.");

            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Partie terminée par l'utilisateur.");
                break;
            }
            boolean actionValide = traiterAction(input, joueur1Tour, combat, joueur1Pokemons, joueur2Pokemons);
            if (!actionValide) {
                continue;
            }
            nettoyerPokemons(joueur1Pokemons);
            nettoyerPokemons(joueur2Pokemons);

            if (!combat.mewtwoEstVivant()) {
                break;
            }

            joueur1Tour = !joueur1Tour;
        }

        System.out.println("\nLe combat est terminé !");
        if (!combat.mewtwoEstVivant()) {
            System.out.println("Le Mewtwo adverse est K.O. !");
            System.out.println(joueur1Tour ? "Joueur 2 gagne !" : "Joueur 1 gagne !");
        }
    }

    private static boolean traiterAction(String input, boolean joueur1Tour, CombatPokemon combat,
                                         List<Pokemon> joueur1Pokemons, List<Pokemon> joueur2Pokemons) {
        String[] parts = input.split("\\s+");
        if (parts.length != 5) {
            System.out.println("Format incorrect. Réessayez.");
            return false;
        }

        try {
            int x1 = Integer.parseInt(parts[0]);
            int y1 = Integer.parseInt(parts[1]);
            String action = parts[2].toLowerCase();
            int x2 = Integer.parseInt(parts[3]);
            int y2 = Integer.parseInt(parts[4]);

            Pokemon p = combat.getPokemon(x1, y1);

            if (p == null) {
                System.out.println("Pas de Pokémon à cette position.");
                return false;
            }
            if (joueur1Tour && !joueur1Pokemons.contains(p)) {
                System.out.println("Ce Pokémon n'appartient pas au Joueur 1.");
                return false;
            }
            if (!joueur1Tour && !joueur2Pokemons.contains(p)) {
                System.out.println("Ce Pokémon n'appartient pas au Joueur 2.");
                return false;
            }

            switch (action) {
                case "move" -> combat.deplacerPokemon(x1, y1, x2, y2);
                case "attack" -> combat.attaquer(x1, y1, x2, y2);
                default -> {
                    System.out.println("Action inconnue : utilisez 'move' ou 'attack'");
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Veuillez entrer des coordonnées valides.");
            return false;
        }

        return true;
    }

    private static void initialiserGrille(CombatPokemon combat, List<Pokemon> joueur1Pokemons,
                                          List<Pokemon> joueur2Pokemons) {
        for (int j = 0; j < 9; j++) {
            Pokemon p1 = new Pokemon(j + 1, "J1-" + (j + 1));
            combat.placerPokemon(p1, 0, j);
            joueur1Pokemons.add(p1);

             if (j != 4) {
            Pokemon p2 = new Pokemon(j + 20, "J1-" + (j + 20));
            combat.placerPokemon(p2, 1, j);
            joueur1Pokemons.add(p2);
          }

            Pokemon p3 = new Pokemon(j + 40, "J1-" + (j + 40));
            combat.placerPokemon(p3, 2, j);
            joueur1Pokemons.add(p3);
        }

        for (int j = 0; j < 9; j++) {
            Pokemon p1 = new Pokemon(j + 60, "J2-" + (j + 60));
            combat.placerPokemon(p1, 8, j);
            joueur2Pokemons.add(p1);

            if (j != 4) {
            Pokemon p2 = new Pokemon(j + 80, "J2-" + (j + 80));
            combat.placerPokemon(p2, 7, j);
            joueur2Pokemons.add(p2);
         }
         
            Pokemon p3 = new Pokemon(j + 100, "J2-" + (j + 100));
            combat.placerPokemon(p3, 6, j);
            joueur2Pokemons.add(p3);
        }

        Pokemon mewtwo1 = new Pokemon(150, "J1-Mewtwo");
        combat.placerPokemon(mewtwo1, 1, 4);
        joueur1Pokemons.add(mewtwo1);

        Pokemon mewtwo2 = new Pokemon(150, "J2-Mewtwo");
        combat.placerPokemon(mewtwo2, 7, 4);
        joueur2Pokemons.add(mewtwo2);
    }

    private static void nettoyerPokemons(List<Pokemon> pokemons) {
        pokemons.removeIf(p -> !p.isAlive());
    }
}


