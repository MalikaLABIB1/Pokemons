package src;
public class Type {
    // Constants for each type
    public static final int NORMAL = 0;
    public static final int FEU = 1;
    public static final int EAU = 2;
    public static final int PLANTE = 3;
    public static final int ELECTRIK = 4;
    public static final int GLACE = 5;
    public static final int COMBAT = 6;
    public static final int POISON = 7;
    public static final int SOL = 8;
    public static final int VOL = 9;
    public static final int PSY = 10;
    public static final int INSECTE = 11;
    public static final int ROCHE = 12;
    public static final int SPECTRE = 13;
    public static final int DRAGON = 14;

    // Names of all Pokémon in Pokédex order
    public static final String[] POKEMON_NAMES = {
        "Bulbizarre", "Herbizarre", "Florizarre", "Salamèche", "Reptincel", 
        // ... (complete with all 151 Pokémon names)
        "Dracolosse", "Mewtwo", "Mew"
    };

    // Names of all types
    public static final String[] TYPE_NAMES = {
        "Normal", "Feu", "Eau", "Plante", "Electrik", 
        "Glace", "Combat", "Poison", "Sol", "Vol", 
        "Psy", "Insecte", "Roche", "Spectre", "Dragon"
    };

    // Type effectiveness chart [attacker][defender]
    // 1.0 = normal, 2.0 = super effective, 0.5 = not very effective, 0 = immune
    public static final double[][] TYPE_EFFECTIVENESS = {
        // Normal, Feu, Eau, Plante, Electrik, Glace, Combat, Poison, Sol, Vol, Psy, Insecte, Roche, Spectre, Dragon
        {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0}, // Normal
        {1.0, 0.5, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5}, // Feu
        {1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5}, // Eau
        // ... (complete effectiveness chart for all types)
    };

    /**
     * Gets the name of a Pokémon by its Pokédex number
     * @param pokedexNumber The Pokédex number (1-151)
     * @return The Pokémon's species name
     */
    public static String getPokemonName(int pokedexNumber) {
        if (pokedexNumber < 1 || pokedexNumber > 151) {
            return "MissingNo";
        }
        return POKEMON_NAMES[pokedexNumber - 1];
    }

    /**
     * Gets the name of a type by its constant value
     * @param type The type constant
     * @return The type's name
     */
    public static String getTypeName(int type) {
        if (type < 0 || type >= TYPE_NAMES.length) {
            return "???";
        }
        return TYPE_NAMES[type];
    }

    /**
     * Calculates the effectiveness multiplier between two types
     * @param attackType The attacking type
     * @param defendType The defending type
     * @return The effectiveness multiplier (0, 0.5, 1, or 2)
     */
    public static double getEffectiveness(int attackType, int defendType) {
        if (attackType < 0 || attackType >= TYPE_EFFECTIVENESS.length ||
            defendType < 0 || defendType >= TYPE_EFFECTIVENESS[0].length) {
            return 1.0;
        }
        return TYPE_EFFECTIVENESS[attackType][defendType];
    }
}