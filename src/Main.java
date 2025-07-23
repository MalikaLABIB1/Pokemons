package src;
public class Main {
    public static void main(String[] args) {
        // Create two Pokémon
        Pokemon colossinge = new Pokemon(
            57, "Robert", Type.COMBAT, -1, 
            65, 105, 60, 95
        );
        
        Pokemon ectoplasma = new Pokemon(
            94, "Gustave", Type.SPECTRE, Type.POISON, 
            60, 65, 60, 110
        );
        
        // Display their info
        System.out.println("Pokémon 1:");
        System.out.println(colossinge);
        System.out.println("\nPokémon 2:");
        System.out.println(ectoplasma);
        
        // Battle until one Pokémon faints
        System.out.println("\nLe combat commence!");
        int round = 1;
        
        while (colossinge.isAlive() && ectoplasma.isAlive()) {
            System.out.println("\n--- Round " + round + " ---");
            Pokemon.battleRound(colossinge, ectoplasma);
            round++;
            
            // Display current status
            System.out.println("\nÉtat après le round:");
            System.out.println(colossinge.getNickname() + ": " + 
                             colossinge.getCurrentHp() + "/" + colossinge.getHp() + " PV");
            System.out.println(ectoplasma.getNickname() + ": " + 
                             ectoplasma.getCurrentHp() + "/" + ectoplasma.getHp() + " PV");
        }
        
        // Announce the winner
        System.out.println("\nLe combat est terminé!");
        if (colossinge.isAlive()) {
            System.out.println(colossinge.getNickname() + " (" + colossinge.getSpecies() + ") remporte le combat!");
        } else {
            System.out.println(ectoplasma.getNickname() + " (" + ectoplasma.getSpecies() + ") remporte le combat!");
        }
    }
}