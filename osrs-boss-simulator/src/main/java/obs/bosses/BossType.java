package obs.bosses;

public enum BossType {
    BLOAT(new Boss(9156, 1800, 5)),
    MAIDEN(new Boss(13376, 2625, 6)),
    NYLOCAS_MATOMENOS(new Boss(6976, 150, 2)),
    NYLOCAS_PRINKIPAS(new Boss(2176, 400, 3)),
    NYLOCAS_VASILIAS(new Boss(3776, 2500, 4)),
    SOTETSEG(new Boss(28006, 3000, 5)),
    VERZIK_P2(new Boss(25916, 2625, 3)),
    VERZIK_P3(new Boss(14946, 2625, 7)),
    XARPUS(new Boss(13376, 4500, 5));

    private final Boss boss;

    BossType(Boss boss) {
        this.boss = boss;
    }

    public Boss getBoss() {
        return boss;
    }
}
