package sim.util;

public class ScytheAttackResult {

    private int damage;
    private int hits;
    private int misses;

    public ScytheAttackResult(int damage, int hits, int misses) {
        this.damage = damage;
        this.hits = hits;
        this.misses = misses;
    }

    public int getDamage() {
        return damage;
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }
}
