import java.util.HashMap;
import java.util.List;

public class SmarterOpponent implements Opponent{
    private static final int DELAY_TIME = 2000;
    private final GameEvaluator gameEvaluator = new GameEvaluator();
    private final HashMap<GameStatus, Integer> gameValues = new HashMap<>();

    public SmarterOpponent() {
        gameValues.put(GameStatus.CROSS_WINS, 1);
        gameValues.put(GameStatus.NOUGHT_WINS,-1);
        gameValues.put(GameStatus.TIE,0);
    }

    @Override
    public int makeChoice(Cell[] board) {
        Cell[] copyOfBoard = board.clone();
        return minimax(copyOfBoard,8,false).getPosition();
    }

    private MiniMaxReturnThing minimax(Cell[] board, int depth, boolean maximizingPlayer) {

        // todo: fix bug. Opponent plays to tie, not to win
        var gameStatus = gameEvaluator.checkGameStatus(board);
        if (depth == 0 || (gameStatus != GameStatus.RUNNING)) {
            return new MiniMaxReturnThing(0, gameValues.get(gameStatus));
        }
        var best = new MiniMaxReturnThing(0,(maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE));
        for (int i = 0; i < board.length; i++) {
            if (board[i] == Cell.EMPTY) {
                board[i] = (maximizingPlayer ? Cell.CROSS : Cell.NOUGHT);
                var eval = minimax(board, depth-1, !maximizingPlayer);
                board[i] = Cell.EMPTY;
                if (maximizingPlayer) {
                    if (eval.getScore() > best.getScore()) {
                        best = eval;
                        eval.setPosition(i);
                    }
                } else if (!maximizingPlayer) {
                    if (eval.getScore() < best.getScore()) {
                        best = eval;
                        eval.setPosition(i);
                    }
                }
            }
        }
        return best ;
    }

    private int eval(Cell[] board) {
        return gameValues.getOrDefault(gameEvaluator.checkGameStatus(board), 10);
    }

    @Override
    public int getDelayTime() {
        return DELAY_TIME;
    }
}
