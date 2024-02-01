package AdventureGame;

public abstract class GameCharacter {
    private int id, damage, health, money;

    private String charName;

    public GameCharacter() {
    }

    public GameCharacter(String charName, int id, int damage, int health, int money) {
        this.charName = charName;
        this.id = id;
        this.damage = damage;
        this.health = health;
        this.money = money;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
