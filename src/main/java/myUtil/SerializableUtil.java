package myUtil;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by SilverNarcissus on 2017/2/13.
 */
public class SerializableUtil {

    /**
     * for concurrent safety
     */
    private final AtomicReference<State> init = new AtomicReference<State>(State.NEW);
    /**
     * some field
     */
    private int x, y;

    public SerializableUtil(int x, int y) {
        initialize(x, y);
    }

    /**
     * for child can implements serializable
     */
    protected SerializableUtil() {

    }

    protected final void initialize(int x, int y) {
        if (!init.compareAndSet(State.NEW, State.INITIALIZING)) {
            throw new IllegalStateException("Already initialized");
        }
        //initialize
        this.x = x;
        this.y = y;
    }

    public int getX() {
        checkInit();
        return x;
    }

    public int getY() {
        checkInit();
        return y;
    }

    /**
     * check if the object is initialized
     */
    private void checkInit() {
        if (init.get() != State.INITIALIZED) {
            throw new IllegalStateException("Uninitialized");
        }
    }

    /**
     * enum for record initializing state
     */
    private enum State {
        NEW,
        INITIALIZING,
        INITIALIZED
    }
}
