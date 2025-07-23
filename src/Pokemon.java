package src;
public class Pokemon {
    private int pokedexNumber;
    private String nickname;
    private int type1;
    private int type2;
    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private int currentHp;

    // Default constructor - creates MissingNo
    public Pokemon() {
        this.pokedexNumber = 0;
        this.nickname = "MissingNo";
        this.type1 = Type.NORMAL;
        this.type2 = -1; // No second type
        this.hp = 33;
        this.attack = 136;
        this.defense = 0;
        this.speed = 29;
        this.currentHp = hp;
    }

    // Full constructor
    public Pokemon(int pokedexNumber, String nickname, int type1, int type2, 
                  int hp, int attack, int defense, int speed) {
        this.pokedexNumber = pokedexNumber;
        this.nickname = nickname;
        this.type1 = type1;
        this.type2 = type2;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.currentHp = hp;
    }

    // Getters and setters
    public int getPokedexNumber() { return pokedexNumber; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public int getType1() { return type1; }
    public int getType2() { return type2; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }
    public int getCurrentHp() { return currentHp; }

    // Returns the species name from the Pokédex
    public String getSpecies() {
        return Type.getPokemonName(pokedexNumber);
    }

    // Returns true if the Pokémon has a second type
    public boolean hasSecondType() {
        return type2 != -1;
    }

    // Checks if the Pokémon is still alive
    public boolean isAlive() {
        return currentHp > 0;
    }

    // Calculates damage dealt to another Pokémon
    private int calculateDamage(Pokemon defender) {
        // Base damage formula
        int damage = this.attack - defender.defense;
        
        // Ensure minimum damage of 1
        if (damage < 1) {
            damage = 1;
        }
        
        // Apply type effectiveness
        double effectiveness1 = Type.getEffectiveness(this.type1, defender.type1);
        double effectiveness2 = 1.0;
        
        if (defender.hasSecondType()) {
            effectiveness1 *= Type.getEffectiveness(this.type1, defender.type2);
        }
        
        if (this.hasSecondType()) {
            effectiveness2 = Type.getEffectiveness(this.type2, defender.type1);
            if (defender.hasSecondType()) {
                effectiveness2 *= Type.getEffectiveness(this.type2, defender.type2);
            }
        }
        
        // Use the higher effectiveness
        double maxEffectiveness = Math.max(effectiveness1, effectiveness2);
        damage = (int)(damage * maxEffectiveness);
        
        return damage;
    }

    // Attacks another Pokémon
    public void attack(Pokemon defender) {
        if (!this.isAlive() || !defender.isAlive()) {
            return;
        }
        
        int damage = calculateDamage(defender);
        defender.currentHp -= damage;
        
        if (defender.currentHp < 0) {
            defender.currentHp = 0;
        }
        
        System.out.println(this.nickname + " (" + this.getSpecies() + ") attaque " + 
                         defender.nickname + " (" + defender.getSpecies() + ") et inflige " + 
                         damage + " dégâts!");
        
        if (!defender.isAlive()) {
            System.out.println(defender.nickname + " (" + defender.getSpecies() + ") est K.O.!");
        }
    }

    // Performs a battle round between two Pokémon
    public static void battleRound(Pokemon p1, Pokemon p2) {
        if (p1.speed >= p2.speed) {
            p1.attack(p2);
            if (p2.isAlive()) {
                p2.attack(p1);
            }
        } else {
            p2.attack(p1);
            if (p1.isAlive()) {
                p1.attack(p2);
            }
        }
    }

    // Checks equality based on Pokédex number and nickname
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pokemon other = (Pokemon) obj;
        return pokedexNumber == other.pokedexNumber && 
               nickname.equals(other.nickname);
    }

    // String representation of the Pokémon
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Numéro : ").append(pokedexNumber).append("\n");
        sb.append("Espèce : ").append(getSpecies()).append("\n");
        sb.append("Nom : ").append(nickname).append("\n");
        sb.append("Type 1 : ").append(Type.getTypeName(type1)).append("\n");
        if (hasSecondType()) {
            sb.append("Type 2 : ").append(Type.getTypeName(type2)).append("\n");
        } else {
            sb.append("Type 2 : Ø\n");
        }
        sb.append("PV : ").append(currentHp).append("/").append(hp).append("\n");
        sb.append("Att : ").append(attack).append("\n");
        sb.append("Def : ").append(defense).append("\n");
        sb.append("Vit : ").append(speed);
        return sb.toString();
    }
}
public Pokemon(int pokedexNumber, String nickname) {
    this.pokedexNumber = pokedexNumber;
    this.nickname = nickname;
    
    // Read from CSV file (pseudo-code - actual implementation depends on CSV format)
    try (BufferedReader br = new BufferedReader(new FileReader("pokedex.csv"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            int currentNum = Integer.parseInt(values[0]);
            if (currentNum == pokedexNumber) {
                this.type1 = Type.valueOf(values[1].toUpperCase());
                this.type2 = values[2].isEmpty() ? -1 : Type.valueOf(values[2].toUpperCase());
                this.hp = Integer.parseInt(values[3]);
                this.attack = Integer.parseInt(values[4]);
                this.defense = Integer.parseInt(values[5]);
                this.speed = Integer.parseInt(values[6]);
                this.currentHp = hp;
                break;
            }
        }
    } catch (IOException e) {
        // If reading fails, create a MissingNo
        this.type1 = Type.NORMAL;
        this.type2 = -1;
        this.hp = 33;
        this.attack = 136;
        this.defense = 0;
        this.speed = 29;
        this.currentHp = hp;
    }
}// Add this constructor to the Pokemon clas