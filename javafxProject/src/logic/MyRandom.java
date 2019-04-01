package logic;

import java.util.Random;

/**
 * Class overides the random class to make it possible to test
 */
public class MyRandom extends Random {

    @Override
    public int nextInt(int bound) {
        return bound - 1;
    }
}
