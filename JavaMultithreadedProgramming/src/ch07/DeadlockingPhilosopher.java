package ch07;

import util.Debug;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-17 15:07
 */
public class DeadlockingPhilosopher extends AbstractPhilosopher {
    public DeadlockingPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    public void eat() {
        synchronized (left) {
            Debug.info("%s is picking up %s on his left...%n", this, left);
            left.pickUp();
            synchronized (right) {
                Debug.info("%s is picking up %s on his right...%n", this, right);
                right.pickUp();
                doEat();
                right.putDown();
            }
            left.putDown();
        }
    }
}
