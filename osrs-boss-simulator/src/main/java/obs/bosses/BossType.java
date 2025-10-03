package obs.bosses;

public enum BossType {
    BLOAT(new Boss(9156, 1800, 5)),
    MAIDEN(new Boss(13376, 2625, 6)),
    NYLOCAS_MATOMENOS(new Boss(6976, 150, 2)),
    SOTETSEG(new Boss(28006, 1000, 5)),
    VERZIK_P2(new Boss(25916, 2625, 3));

    private final Boss boss;

    BossType(Boss boss) {
        this.boss = boss;
    }

    public Boss getBoss() {
        return boss;
    }
}
