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

