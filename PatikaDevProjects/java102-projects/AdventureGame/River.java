package AdventureGame;

public class River extends BattleLoc {
    public River(Player player) {
        super(3, player, "River", new Bear(), new Water(7));
    }
}
