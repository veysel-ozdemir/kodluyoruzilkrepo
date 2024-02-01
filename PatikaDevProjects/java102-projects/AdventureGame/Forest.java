package AdventureGame;

public class Forest extends BattleLoc {
    public Forest(Player player) {
        super(4, player, "Forest", new Vampire(), new Firewood(6));
    }
}
