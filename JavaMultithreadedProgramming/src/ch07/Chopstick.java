package ch07;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-17 14:53
 */
public class Chopstick {
    public final int id;
    private Status status = Status.PUT_DOWN;

    public static enum Status {
        PICKED_UP,
        PUT_DOWN
    }

    public Chopstick(int id) {
        super();
        this.id = id;
    }

    public void pickUp() {
        status = Status.PICKED_UP;
    }

    public void putDown() {
        status = Status.PUT_DOWN;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Chopstick-" + id;
    }
}
