package AdventureGame;

import java.util.InputMismatchException;

public class Game {
    private Player player;
    private Location location;

    public Game() {
        start();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void start() {
        System.out.println("Welcome to the Adventure Game!");
        System.out.print("Please enter a name: ");
        String playerName = PlayGround.SCANNER.nextLine();
        player = new Player(playerName);
        player.selectCharacter();

        while (true){
            SafeHouse safeHouse = new SafeHouse(player);
            ToolStore toolStore = new ToolStore(player);
            River river = new River(player);
            Forest forest = new Forest(player);
            Cave cave = new Cave(player);

            Location[] locList = {safeHouse, toolStore, river, forest, cave};

            for (Location loc : locList) {
                System.out.print("\n" + loc.getId() + "- " + loc.getLocName());
            }
            System.out.print("\nSir " + playerName + ", press a valid key to select a location: ");
            byte selection = 0;

            try {
                selection = PlayGround.SCANNER.nextByte();
                if (selection < 1 || selection > 5) throw new InputMismatchException();
            } catch (InputMismatchException e) {
                System.out.println("Invalid value entered");
                System.exit(0);
            }

            // Polymorphism
            location = switch (selection) {
                case 1 -> safeHouse;
                case 2 -> toolStore;
                case 3 -> river;
                case 4 -> forest;
                case 5 -> cave;
                default -> null;
            };

            if (!location.onLocation()) {
                System.out.println("GAME OVER!");
                break;
            }

            player.printInfo();
        }
    }
}
