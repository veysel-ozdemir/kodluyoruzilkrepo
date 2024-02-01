package AdventureGame;

public class Obstacle {
    private String name;
    private int id, damage, health, money, maxCount, defaultHealth;

    public Obstacle(String name, int id, int damage, int health, int money, int maxCount, int defaultHealth) {
        this.name = name;
        this.id = id;
        this.damage = damage;
        this.health = health;
        this.money = money;
        this.maxCount = maxCount;
        this.defaultHealth = defaultHealth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }

    public void printInfo() {
        System.out.println("\n-- " + this.getName() + "'s Current Status --");
        System.out.println("Damage: " + this.getDamage());
        System.out.println("Health: " + this.getHealth());
        System.out.println("Money: " + this.getMoney());
    }
}
