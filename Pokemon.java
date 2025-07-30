public class Pokemon {
    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private int movement;
    private int speed; 
    private boolean isPlayer2;

    public Pokemon(String name, int[] stats, boolean isPlayer2) {
        this.name = name;
        this.maxHp = stats[0];
        this.hp = stats[0];
        this.attack = stats[1];
        this.movement = stats[2];
        this.speed = stats.length > 3 ? stats[3] : stats[2]; 
        this.isPlayer2 = isPlayer2;
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttack() { return attack; }
    public int getMovement() { return movement; }
    public int getSpeed() { return speed; } 
    public boolean isPlayer2() { return isPlayer2; }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public void attaque(Pokemon cible) {
        Pokemon premier, second;
        if (this.getSpeed() >= cible.getSpeed()) {
            premier = this;
            second = cible;
        } else {
            premier = cible;
            second = this;
        }
        second.takeDamage(premier.getAttack());
        if (second.getHp() > 0) {
            premier.takeDamage(second.getAttack());
        }
    }
}

