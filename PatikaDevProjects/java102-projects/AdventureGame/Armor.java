package AdventureGame;

public abstract class Armor {
    private String name;
    private int id, defense, price;

    public Armor(String name, int id, int defense, int price) {
        this.name = name;
        this.id = id;
        this.defense = defense;
        this.price = price;
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

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
