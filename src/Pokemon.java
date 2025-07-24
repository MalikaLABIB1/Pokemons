package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

  
    public Pokemon() {
        this.pokedexNumber = 0;
        this.nickname = "MissingNo";
        this.type1 = type.NORMAL;
        this.type2 = -1; 
        this.hp = 33;
        this.attack = 136;
        this.defense = 0;
        this.speed = 29;
        this.currentHp = hp;
    }

  
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

 
    public Pokemon(int pokedexNumber, String nickname) {
        this.pokedexNumber = pokedexNumber;
        this.nickname = nickname;
        
        
        this.type1 = type.NORMAL;
        this.type2 = -1;
        this.hp = 33;
        this.attack = 136;
        this.defense = 0;
        this.speed = 29;
        this.currentHp = hp;
        try (BufferedReader br = new BufferedReader(new FileReader("data/pokedex.csv"))) {
            String line; br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                int currentNum = Integer.parseInt(values[0]);
                if (currentNum == pokedexNumber) {
                    this.type1 = getTypeConstant(values[2]);
                    this.type2 = values[3].isEmpty() ? -1 : getTypeConstant(values[3]);
                    this.hp = Integer.parseInt(values[4]);
                    this.attack = Integer.parseInt(values[5]);
                    this.defense = Integer.parseInt(values[6]);
                    this.speed = Integer.parseInt(values[7]);
                    this.currentHp = hp;
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading pokedex.csv: " + e.getMessage());
            System.err.println("Using default MissingNo stats");
        }
    }

    private int getTypeConstant(String typeName) {
        for (int i = 0; i < type.TYPE_NAMES.length; i++) {
            if (type.TYPE_NAMES[i].equalsIgnoreCase(typeName)) {
                return i;
            }
        }
        return type.NORMAL; 
    }

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

    public String getSpecies() {
        return type.getPokemonName(pokedexNumber);
    }

    public boolean hasSecondType() {
        return type2 != -1;
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

    private int calculateDamage(Pokemon defender) {

        int damage = this.attack - defender.defense;
        if (damage < 1) {
            damage = 1;
        }
        
        double effectiveness1 = type.getEffectiveness(this.type1, defender.type1);
        double effectiveness2 = 1.0;
        
        if (defender.hasSecondType()) {
            effectiveness1 *= type.getEffectiveness(this.type1, defender.type2);
        }
        
        if (this.hasSecondType()) {
            effectiveness2 = type.getEffectiveness(this.type2, defender.type1);
            if (defender.hasSecondType()) {
                effectiveness2 *= type.getEffectiveness(this.type2, defender.type2);
            }
        }
        
        double maxEffectiveness = Math.max(effectiveness1, effectiveness2);
        damage = (int)(damage * maxEffectiveness);
        
        return damage;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pokemon other = (Pokemon) obj;
        return pokedexNumber == other.pokedexNumber && 
               nickname.equals(other.nickname);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Numéro : ").append(pokedexNumber).append("\n");
        sb.append("Espèce : ").append(getSpecies()).append("\n");
        sb.append("Nom : ").append(nickname).append("\n");
        sb.append("Type 1 : ").append(type.getTypeName(type1)).append("\n");
        if (hasSecondType()) {
            sb.append("Type 2 : ").append(type.getTypeName(type2)).append("\n");
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
