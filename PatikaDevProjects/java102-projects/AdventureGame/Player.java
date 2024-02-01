package AdventureGame;

import java.util.InputMismatchException;

public class Player extends GameCharacter {
    private String playerName;
    private Inventory inventory;
    private int defense;

    public Player(String playerName) {
        super();
        this.playerName = playerName;
        this.inventory = new Inventory(new Water(0), new Food(0), new Firewood(0), null, null);
        this.defense = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void selectCharacter() {
        Samurai samurai = new Samurai();
        Archer archer = new Archer();
        Knight knight = new Knight();

        // Polymorphism
        GameCharacter[] charList = {samurai, archer, knight};

        for (GameCharacter gameChar : charList) {
            System.out.print("\n" + gameChar.getId() + "- " + gameChar.getCharName() + "\tDamage: " + gameChar.getDamage() +
                    "\tHealth: " + gameChar.getHealth() + "\tMoney: " + gameChar.getMoney());
        }

        System.out.print("\nSir " + this.playerName + ", press a valid key to select a character: ");
        byte selection = 0;

        try {
            selection = PlayGround.SCANNER.nextByte();
            if (selection < 1 || selection > 3) throw new InputMismatchException();
        } catch (InputMismatchException e) {
            System.out.println("Invalid value entered");
            System.exit(0);
        }

        switch (selection) {
            case 1:
                initPlayer(samurai);
                break;
            case 2:
                initPlayer(archer);
                break;
            case 3:
                initPlayer(knight);
                break;
        }

        System.out.println("\nYou have selected the " + super.getCharName());
        printInfo();
    }

    private void initPlayer(GameCharacter gameChar) {
        super.setId(gameChar.getId());
        super.setCharName(gameChar.getCharName());
        super.setDamage(gameChar.getDamage());
        super.setHealth(gameChar.getHealth());
        super.setMoney(gameChar.getMoney());
    }

    public void printInfo() {
        System.out.println("\n-- Your Current Status --");
        System.out.println("Weapon: " + (this.inventory.getWeapon() == null ? "No weapon" : this.inventory.getWeapon().getName()));
        System.out.println("Armor: " + (this.inventory.getArmor() == null ? "No armor" : this.inventory.getArmor().getName()));
        System.out.println("Money: " + this.getMoney());
        System.out.println("Water: " + this.inventory.getWater().getAmount());
        System.out.println("Food: " + this.inventory.getFood().getAmount());
        System.out.println("Firewood: " + this.inventory.getFirewood().getAmount());
        System.out.println("Damage: " + this.getDamage());
        System.out.println("Defense: " + this.getDefense());
        System.out.println("Health: " + this.getHealth());
    }
}
