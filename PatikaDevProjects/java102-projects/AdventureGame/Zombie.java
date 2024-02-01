package AdventureGame;

public class Zombie extends Obstacle {
    public static final int DEFAULT_HEALTH = 10;
    public Zombie() {
        super("Zombie", 1, 3, DEFAULT_HEALTH, 4, 4, DEFAULT_HEALTH);
    }
}
