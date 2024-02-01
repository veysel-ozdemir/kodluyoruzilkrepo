package AdventureGame;

public class Inventory {
    private Water water;
    private Food food;
    private Firewood firewood;
    private Weapon weapon;
    private Armor armor;

    public Inventory(Water water, Food food, Firewood firewood, Weapon weapon, Armor armor) {
        this.water = water;
        this.food = food;
        this.firewood = firewood;
        this.weapon = weapon;
        this.armor = armor;
    }

    public Water getWater() {
        return water;
    }

    public void setWater(Water water) {
        this.water = water;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Firewood getFirewood() {
        return firewood;
    }

    public void setFirewood(Firewood firewood) {
        this.firewood = firewood;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
}
