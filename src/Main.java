package src;
public class Main {
    public static void main(String[] args) {
        Pokemon colossinge = new Pokemon(
            57, "Robert", type.COMBAT, -1, 
            65, 105, 60, 95
        );
        
        Pokemon ectoplasma = new Pokemon(
            94, "Gustave", type.SPECTRE, type.POISON, 
            60, 65, 60, 110
        );
        
        System.out.println("Pokémon 1:");
        System.out.println(colossinge);
        System.out.println("\nPokémon 2:");
        System.out.println(ectoplasma);
        
        System.out.println("\nLe combat commence!");
        int round = 1;
        
        while (colossinge.isAlive() && ectoplasma.isAlive()) {
            System.out.println("\n--- Round " + round + " ---");
            Pokemon.battleRound(colossinge, ectoplasma);
            round++;
            
            System.out.println("\nÉtat après le round:");
            System.out.println(colossinge.getNickname() + ": " + 
                             colossinge.getCurrentHp() + "/" + colossinge.getHp() + " PV");
            System.out.println(ectoplasma.getNickname() + ": " + 
                             ectoplasma.getCurrentHp() + "/" + ectoplasma.getHp() + " PV");
        }
        
        System.out.println("\nLe combat est terminé!");
        if (colossinge.isAlive()) {
            System.out.println(colossinge.getNickname() + " (" + colossinge.getSpecies() + ") remporte le combat!");
        } else {
            System.out.println(ectoplasma.getNickname() + " (" + ectoplasma.getSpecies() + ") remporte le combat!");
        }
    }
}
