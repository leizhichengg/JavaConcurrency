package ch07;

import java.lang.reflect.Constructor;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-17 15:09
 */
public class DiningPhilosopherProblem {

    public static void main(String[] args) throws Exception {
        int numOfPhilosophers;
        numOfPhilosophers = args.length > 0 ? Integer.valueOf(args[0]) : 2;

        Chopstick[] chopsticks = new Chopstick[numOfPhilosophers];
        for (int i = 0; i < numOfPhilosophers; i++) {
            chopsticks[i] = new Chopstick(i);
        }

        String philosopherImplClassName = System.getProperty("x.philo.impl");
        if (null == philosopherImplClassName) {
            philosopherImplClassName = "FixedPhilosopher";
        }

        for (int i = 0; i < numOfPhilosophers; i++) {
            createPhilosopher(philosopherImplClassName, i, chopsticks);
        }
    }

    private static void createPhilosopher(String philosopherImplClassName,
                                          int id, Chopstick[] chopsticks) throws Exception {
        int numOfPhilosophers = chopsticks.length;
        @SuppressWarnings("unchecked")
        Class<Philosopher> philosopherClass = (Class<Philosopher>) Class
                .forName(DiningPhilosopherProblem.class.getPackageName() + "."
                        + philosopherImplClassName);
        Constructor<Philosopher> constructor = philosopherClass.getConstructor(
                int.class, Chopstick.class, Chopstick.class
        );
        Philosopher philosopher = constructor.newInstance(id, chopsticks[id],
                chopsticks[(id + 1) % numOfPhilosophers]);
        philosopher.start();
    }
}
