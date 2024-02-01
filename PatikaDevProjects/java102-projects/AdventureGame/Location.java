package AdventureGame;

public abstract class Location {
    private int id;
    private Player player;
    private String locName;

    public Location(int id, Player player, String locName) {
        this.id = id;
        this.player = player;
        this.locName = locName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public abstract boolean onLocation();
}
