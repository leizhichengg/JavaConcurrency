package ch02;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-01-25 19:24
 */
public class SafeCircularSeqGenerator implements CircularSeqGenerator {
    private short sequence = -1;

    @Override
    public synchronized short nextSequence() {
        if (sequence >= 999) {
            sequence = 0;
        } else {
            sequence++;
        }
        return sequence;
    }
}
