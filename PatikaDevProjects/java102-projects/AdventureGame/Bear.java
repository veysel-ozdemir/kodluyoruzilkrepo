package AdventureGame;

public class Bear extends Obstacle {
    public static final int DEFAULT_HEALTH = 20;
    public Bear() {
        super("Bear", 3, 7, DEFAULT_HEALTH, 12, 2, DEFAULT_HEALTH);
    }
}
