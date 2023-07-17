import java.util.*;

public class Board {
    public static final int TOTAL_POSITIONS = 9;
    private final Cell[] board;
    private GameEvaluator gameEvaluator;

    public Board() {
        gameEvaluator = new GameEvaluator();
        board = new Cell[TOTAL_POSITIONS];
        for (int i = 0; i < TOTAL_POSITIONS; i++) {
            board[i] = Cell.EMPTY;
        }
    }



    public boolean isEmptyCell(int position) {
        return board[position] == Cell.EMPTY;
    }

    public void makeMove(Cell cell, int position) {
        board[position] = cell;
    }

    public Cell[] getBoard() {
        return board;
    }

    public GameStatus checkGameStatus() {
        return gameEvaluator.checkGameStatus(board);
    }

}

