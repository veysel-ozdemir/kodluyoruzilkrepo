package AdventureGame;

public class Vampire extends Obstacle {
    public static final int DEFAULT_HEALTH = 14;
    public Vampire() {
        super("Vampire", 2, 4, DEFAULT_HEALTH, 7, 3, DEFAULT_HEALTH);
    }
}
