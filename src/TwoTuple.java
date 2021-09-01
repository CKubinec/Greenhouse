import java.io.Serializable;

/**
 * A class to store Objects using a Key and Value.
 *
 * @param <A> the Key
 * @param <B> the Value
 * @author Craig Kubinec ID:3433193
 */
public class TwoTuple<A, B> implements Serializable {

    public final A first;
    public final B second;

    /**
     * Instantiates a new Two tuple.
     *
     * @param a the Key
     * @param b the Value
     */
    public TwoTuple(A a, B b) {
        first = a;
        second = b;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
