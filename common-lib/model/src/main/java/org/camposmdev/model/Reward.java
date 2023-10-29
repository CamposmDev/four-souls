package org.camposmdev.model;

public class Reward {
    public static RewardBuilder create() {
        return new RewardBuilder();
    }
    private int loot;
    private int treasure;
    private int cents;

    private Reward() { }

    public int loot() {
        return loot;
    }

    public int treasure() {
        return treasure;
    }

    public int cents() {
        return cents;
    }

    public static class RewardBuilder {
        private Reward r;
        private RewardBuilder() {
            r = new Reward();
        }

        public void loot(int n) {
            r.loot = n;
        }

        public void treasure(int n) {
            r.treasure = n;
        }

        public void cents(int n) {
            r.cents = n;
        }

        public Reward build() {
            return r;
        }
    }
}
