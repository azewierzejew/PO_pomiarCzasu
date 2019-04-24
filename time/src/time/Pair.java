package time;

/**
 * Class representing pair of objects. Generic class capable of storing and
 * accessing two objects of any kind.
 * 
 * @author Antoni Żewierżejew
 *
 * @param <T1> type of first object
 * @param <T2> type of second object
 */
public class Pair<T1, T2> {
    /**
     * First object.
     */
    private final T1 first;
    /**
     * Second object.
     */
    private final T2 second;

    /**
     * Generic constructor.
     * 
     * @param first  first object
     * @param second second object
     */
    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Accesses first object stored.
     * 
     * @return first object stored
     */
    public T1 first() {
        return first;
    }

    /**
     * Accesses second object stored.
     * 
     * @return second object stored
     */
    public T2 second() {
        return second;
    }

}
