public class Pokemon {
    private String name;
    private int maxHp;
    private int hp;
    private int attack;
    private boolean player1;

    public Pokemon(String name, int maxHp, int attack, boolean player1) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.player1 = player1;
    }

    public void takeDamage(int damage) {
        hp = Math.max(0, hp - damage);
    }

    
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttack() { return attack; }
    public boolean isPlayer1() { return player1; }
}