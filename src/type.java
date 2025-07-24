package src;

public class type {
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
    public static final String[] POKEMON_NAMES = {
        "Bulbizarre", "Herbizarre", "Florizarre", "Salamèche", "Reptincel",
        "Dracaufeu", "Carapuce", "Carabaffe", "Tortank", "Chenipan",
        "Chrysacier", "Papilusion", "Aspicot", "Coconfort", "Dardargnan",
        "Roucool", "Roucoups", "Roucarnage", "Rattata", "Rattatac",
        "Piafabec", "Rapasdepic", "Abo", "Arbok", "Pikachu",
        "Raichu", "Sabelette", "Sablaireau", "Nidoran♀", "Nidorina",
        "Nidoqueen", "Nidoran♂", "Nidorino", "Nidoking", "Mélofée",
        "Mélodelfe", "Goupix", "Feunard", "Rondoudou", "Grodoudou",
        "Nosferapti", "Nosferalto", "Mystherbe", "Ortide", "Rafflesia",
        "Paras", "Parasect", "Mimitoss", "Aéromite", "Taupiqueur",
        "Triopikeur", "Miaouss", "Persian", "Psykokwak", "Akwakwak",
        "Férosinge", "Colossinge", "Caninos", "Arcanin", "Ptitard",
        "Têtarte", "Tartard", "Abra", "Kadabra", "Alakazam",
        "Machoc", "Machopeur", "Mackogneur", "Chétiflor", "Boustiflor",
        "Empiflor", "Tentacool", "Tentacruel", "Racaillou", "Gravalanch",
        "Grolem", "Ponyta", "Galopa", "Ramoloss", "Flagadoss",
        "Magnéti", "Magnéton", "Canarticho", "Doduo", "Dodrio",
        "Otaria", "Lamantine", "Tadmorv", "Grotadmorv", "Kokiyas",
        "Crustabri", "Fantominus", "Spectrum", "Ectoplasma", "Onix",
        "Soporifik", "Hypnomade", "Krabby", "Krabboss", "Voltorbe",
        "Électrode", "Noeunoeuf", "Noadkoko", "Osselait", "Ossatueur",
        "Kicklee", "Tygnon", "Excelangue", "Smogo", "Smogogo",
        "Rhinocorne", "Rhinoféros", "Leveinard", "Saquedeneu", "Kangourex",
        "Hypotrempe", "Hypocéan", "Poissirène", "Poissoroy", "Stari",
        "Staross", "M. Mime", "Insécateur", "Lippoutou", "Élektek",
        "Magmar", "Scarabrute", "Tauros", "Magicarpe", "Léviator",
        "Lokhlass", "Métamorph", "Évoli", "Aquali", "Voltali",
        "Pyroli", "Porygon", "Amonita", "Amonistar", "Kabuto",
        "Kabutops", "Ptéra", "Ronflex", "Artikodin", "Électhor",
        "Sulfura", "Minidraco", "Draco", "Dracolosse", "Mewtwo",
        "Mew"
    };
    public static final String[] TYPE_NAMES = {
        "Normal", "Feu", "Eau", "Plante", "Electrik",
        "Glace", "Combat", "Poison", "Sol", "Vol",
        "Psy", "Insecte", "Roche", "Spectre", "Dragon"
    };
    public static final double[][] TYPE_EFFECTIVENESS = {
        {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0}, 
        {1.0, 0.5, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5}, 
        {1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5}, 
        {1.0, 0.5, 2.0, 0.5, 1.0, 1.0, 1.0, 0.5, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 0.5}, 
        {1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5}, 
        {1.0, 0.5, 0.5, 2.0, 1.0, 0.5, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0}, 
        {2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 0.5, 0.5, 0.5, 2.0, 0.0, 1.0}, 
        {1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0}, 
        {1.0, 2.0, 1.0, 0.5, 2.0, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0, 0.5, 2.0, 1.0, 1.0}, 
        {1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0}, 
        {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0}, 
        {1.0, 0.5, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0, 0.5, 1.0}, 
        {1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0}, 
        {0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0}, 
        {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0}  
    };
    public static String getPokemonName(int pokedexNumber) {
        if (pokedexNumber < 1 || pokedexNumber > 151) {
            return "MissingNo";
        }
        return POKEMON_NAMES[pokedexNumber - 1];
    }
    public static String getTypeName(int type) {
        if (type < 0 || type >= TYPE_NAMES.length) {
            return "???";
        }
        return TYPE_NAMES[type];
    }
    public static double getEffectiveness(int attackType, int defendType) {
        if (attackType < 0 || attackType >= TYPE_EFFECTIVENESS.length ||
            defendType < 0 || defendType >= TYPE_EFFECTIVENESS[0].length) {
            return 1.0;
        }
        return TYPE_EFFECTIVENESS[attackType][defendType];
    }
}
