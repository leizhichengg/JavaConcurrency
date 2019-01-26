package ch02;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-01-25 19:23
 */
public class NoRaceCondition {

    public int nwxtSequence(int sequence) {

        if (sequence >= 999) {
            sequence = 0;
        } else {
            sequence++;
        }
        return sequence;
    }
}
