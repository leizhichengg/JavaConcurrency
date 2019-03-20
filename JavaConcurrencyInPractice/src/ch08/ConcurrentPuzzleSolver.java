package ch08;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

/**
 * @Author: lei
 * @Description:
 * @Date: 2019-03-20
 */
public class ConcurrentPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final ExecutorService exec;
    private final ConcurrentMap<P, Boolean> seen;
    final ValueLatch<SequentialPuzzleSolver.Node<P, M>> solution
            = new ValueLatch<SequentialPuzzleSolver.Node<P, M>>();

    public List<M> solve() throws InterruptedException {
        try {
            P p = puzzle.initialPosition();
            exec.execute(newTask(p, null, null));
            SequentialPuzzleSolver.Node<P, M> solnNode = solution.getValue();
            return (solnNode == null) ? null : solnNode.asMoveList();
        } finally {
            exec.shutdown();
        }
    }

    protected Runnable newTask(P p, M m, SequentialPuzzleSolver.Node<P, M> n) {
        return new SolverTask(p, m, n);
    }

    class SolverTask extends SequentialPuzzleSolver.Node<P, M> implements Runnable {

        @Override
        public void run() {
            if (solution.isSet()
                    || seen.putIfAbsent(pos, true) != null) {
                return;
            }
            if (puzzle.isGoal(pos)) {
                solution.setValue(this);
            } else {
                for (M m : puzzle.legalMoves(pos)) {
                    exec.execute(
                            newTask(puzzle.move(pos, m), m, this)
                    );
                }
            }
        }
    }

}
