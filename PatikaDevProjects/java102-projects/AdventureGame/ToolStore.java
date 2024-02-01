package AdventureGame;

import java.util.InputMismatchException;

public class ToolStore extends NormalLoc {
    public ToolStore(Player player) {
        super(2, player, "Tool Store");
    }

    @Override
    public boolean onLocation() {
        System.out.println("\nYou are in the Tool Store now.");
        System.out.println("1- Weapons\n2- Armors\n3- Exit the store");
        System.out.print("Sir " + super.getPlayer().getPlayerName() + ", press a valid key to select a tool: ");
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
                printWeapon();
                break;
            case 2:
                printArmor();
                break;
            case 3:
                System.out.println("See you!");
                break;
        }

        return true;
    }

    private void printWeapon() {
        Revolver revolver = new Revolver();
        Sword sword = new Sword();
        Rifle rifle = new Rifle();

        Weapon[] weapons = {revolver, sword, rifle};

        for (Weapon weapon : weapons) {
            System.out.print("\n" + weapon.getId() + "- " + weapon.getName() + "\tDamage: " + weapon.getDamage() + "\tPrice: " + weapon.getPrice());
        }
        System.out.print("\nSir " + super.getPlayer().getPlayerName() + ", press a valid key to select a weapon: ");
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
                buyWeapon(revolver);
                break;
            case 2:
                buyWeapon(sword);
                break;
            case 3:
                buyWeapon(rifle);
                break;
        }
    }

    private void buyWeapon(Weapon weapon) {
        if (super.getPlayer().getMoney() < weapon.getPrice()) {
            System.out.println("\nYou have no budget to afford " + weapon.getName());
        } else {
            super.getPlayer().setMoney(super.getPlayer().getMoney() - weapon.getPrice());
            System.out.println("\n-- Payment is done --");
            super.getPlayer().getInventory().setWeapon(weapon);
            super.getPlayer().setDamage(super.getPlayer().getDamage()+weapon.getDamage());
        }
    }

    private void printArmor() {
        LightArmor lightArmor = new LightArmor();
        MediumArmor mediumArmor = new MediumArmor();
        HeavyArmor heavyArmor = new HeavyArmor();

        Armor[] armors = {lightArmor, mediumArmor, heavyArmor};

        for (Armor armor : armors) {
            System.out.print("\n" + armor.getId() + "- " + armor.getName() + "\tDefense: " + armor.getDefense() + "\tPrice: " + armor.getPrice());
        }
        System.out.print("\nSir " + super.getPlayer().getPlayerName() + ", press a valid key to select an armor: ");
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
                buyArmor(lightArmor);
                break;
            case 2:
                buyArmor(mediumArmor);
                break;
            case 3:
                buyArmor(heavyArmor);
                break;
        }
    }

    private void buyArmor(Armor armor) {
        if (super.getPlayer().getMoney() < armor.getPrice()) {
            System.out.println("\nYou have no budget to afford " + armor.getName());
        } else {
            super.getPlayer().setMoney(super.getPlayer().getMoney() - armor.getPrice());
            System.out.println("\n-- Payment is done --");
            super.getPlayer().getInventory().setArmor(armor);
            super.getPlayer().setDefense(super.getPlayer().getDefense()+armor.getDefense());
        }
    }
}
