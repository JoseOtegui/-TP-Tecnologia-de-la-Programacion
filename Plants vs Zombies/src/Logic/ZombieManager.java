package Logic;

import java.util.Random;


public class ZombieManager {

    private Level level;
    private int zombiesLeft;
    private double prob;

    public ZombieManager(Level level) {
        this.level = level;
        switch (level) {
            case EASY:
                zombiesLeft = 3;
                prob = 0.1;
                break;
            case HARD:
                zombiesLeft = 5;
                prob = 0.2;
                break;
            case INSANE:
                zombiesLeft = 10;
                prob = 0.3;
                break;
        }
    }

    public int zombiesLeft() {
        return zombiesLeft;
    }
    public boolean isZombieAdded(Random rnd) {
        boolean added = zombiesLeft>0 && rnd.nextDouble() <= prob;
        if (added) {
            zombiesLeft--;
        }
        return added;
    }
    
    public void setZombiesRemaining(int n)
    {
    	zombiesLeft = n;
    }
}
