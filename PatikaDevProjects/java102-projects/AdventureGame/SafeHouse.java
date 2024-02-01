package AdventureGame;

public class SafeHouse extends NormalLoc {
    public SafeHouse(Player player) {
        super(1, player, "Safe House");
    }

    @Override
    public boolean onLocation() {
        System.out.println("\nYou are in the Safe House now.");
        switch (super.getPlayer().getId()) {
            case 1:
                super.getPlayer().setHealth(Samurai.DEFAULT_HEALTH);
                break;
            case 2:
                super.getPlayer().setHealth(Archer.DEFAULT_HEALTH);
                break;
            case 3:
                super.getPlayer().setHealth(Knight.DEFAULT_HEALTH);
                break;
        }
        System.out.println("Your health is fulled: " + super.getPlayer().getHealth());
        return true;
    }
}
