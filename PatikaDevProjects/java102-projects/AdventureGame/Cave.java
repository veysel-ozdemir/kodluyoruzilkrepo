package AdventureGame;

public class Cave extends BattleLoc {
    public Cave(Player player) {
        super(5, player, "Cave", new Zombie(), new Food(5));
    }
}
