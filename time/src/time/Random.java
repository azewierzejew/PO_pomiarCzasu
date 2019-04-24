package time;

/**
 * Class giving pseudo-random numbers.
 * 
 * @author Antoni Żewierżejew
 */
public class Random {

    /**
     * Current state.
     */
    private int state;
    /**
     * Generators multiplier.
     */
    private static final int MULTIPLIER = 1103515245;
    /**
     * Generators summand.
     */
    private static final int SUMMAND = 12345;
    /**
     * Generators mask (and maximum value).
     */
    private static final int MASK = 0x7fffffff;

    /**
     * Generic constructor.
     * 
     * @param seed seed
     */
    public Random(int seed) {
        state = seed;
    }

    /**
     * Returns next pseudo-random number. Number is from 0 up to {@value #MASK}.
     * 
     * @return pseudo-random number
     */
    public int get() {
        state = (state * MULTIPLIER + SUMMAND) & MASK;
        return state;
    }

}
