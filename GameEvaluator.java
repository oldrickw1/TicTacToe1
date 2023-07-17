import java.util.*;

public class GameEvaluator {

    private static final List<Set<Integer>> WINNING_COMBINATIONS = new ArrayList<>(List.of(
            new HashSet<>(Set.of(0, 1, 2)),
            new HashSet<>(Set.of(3, 4, 5)),
            new HashSet<>(Set.of(6, 7, 8)),
            new HashSet<>(Set.of(0, 3, 6)),
            new HashSet<>(Set.of(1, 4, 7)),
            new HashSet<>(Set.of(2, 5, 8)),
            new HashSet<>(Set.of(0, 4, 8)),
            new HashSet<>(Set.of(2, 4, 6))
    ));


    public GameStatus checkGameStatus(Cell[] board) {
        if (checkIfWinner(Cell.CROSS, board)) {
            return GameStatus.CROSS_WINS;
        } else if (checkIfWinner(Cell.NOUGHT, board)) {
            return GameStatus.NOUGHT_WINS;
        } else if (isTie(board)) {
            return GameStatus.TIE;
        } else {
            return GameStatus.RUNNING;
        }
    }

    private boolean isTie(Cell[] board) {
        return (!Arrays.asList(board).contains(Cell.EMPTY));
    }

    private boolean checkIfWinner(Cell mark, Cell[] board) {
        Set<Integer> positions = getMarkPositions(board, mark);
        return containsSubSet(positions);
    }

    private Set<Integer> getMarkPositions(Cell[] board, Cell cell) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == cell) {
                set.add(i);
            }
        }
        return set;
    }

    private boolean containsSubSet(Set<Integer> targetSet) {
        return GameEvaluator.WINNING_COMBINATIONS.stream().anyMatch(targetSet::containsAll);
    }

}
