package obs.bosses;

public class Boss {
    private final int defenceRoll;
    private final int health;
    private final int size;

    public Boss(int defenceRoll, int health, int size) {
        this.defenceRoll = defenceRoll;
        this.health = health;
        this.size = size;
    }

    public int getDefenceRoll() {
        return defenceRoll;
    }

    public int getHP() {
        return health;
    }

    public int getSize() {
        return size;
    }
}
