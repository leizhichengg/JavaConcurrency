package ch08;

import java.util.Set;

/**
 * @Author: lei
 * @Description:
 * @Date: 2019-03-20
 */
public interface Puzzle<P, M> {
    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    P move(P position, M move);
}
