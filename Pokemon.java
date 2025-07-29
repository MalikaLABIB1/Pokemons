public class Pokemon {
    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private int movement;
    private boolean isPlayer2;

    public Pokemon(String name, int[] stats, boolean isPlayer2) {
        this.name = name;
        this.maxHp = stats[0];
        this.hp = stats[0];
        this.attack = stats[1];
        this.movement = stats[2];
        this.isPlayer2 = isPlayer2;
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttack() { return attack; }
    public int getMovement() { return movement; }
    public boolean isPlayer2() { return isPlayer2; }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }
}

