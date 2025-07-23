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
        this.type1 = Type.NORMAL;
        this.type2 = -1; // No second type
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
        
       
        this.type1 = Type.NORMAL;
        this.type2 = -1;
        this.hp = 33;
        this.attack = 136;
        this.defense = 0;
        this.speed = 29;
        
        
        try (BufferedReader br = new BufferedReader(new FileReader("data/pokedex.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int currentNum = Integer.parseInt(values[0]);
                if (currentNum == pokedexNumber) {
                    this.type1 = getTypeConstant(values[1]);
                    this.type2 = values[2].isEmpty() ? -1 : getTypeConstant(values[2]);
                    this.hp = Integer.parseInt(values[3]);
                    this.attack = Integer.parseInt(values[4]);
                    this.defense = Integer.parseInt(values[5]);
                    this.speed = Integer.parseInt(values[6]);
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
        for (int i = 0; i < Type.TYPE_NAMES.length; i++) {
            if (Type.TYPE_NAMES[i].equalsIgnoreCase(typeName)) {
                return i;
            }
        }
        return Type.NORMAL; 
    }

    // ... [rest of the existing Pokemon class methods remain unchanged]
    // Getters, setters, attack methods, toString, etc.
}
