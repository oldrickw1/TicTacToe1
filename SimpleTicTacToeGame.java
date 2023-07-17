import javax.swing.*;

public class SimpleTicTacToeGame implements Controller{
    private Board model;
    private final GUI view;
    private final Opponent opponent;
    private boolean myTurn;

    public SimpleTicTacToeGame(Board model) {

        this.model = model;
        this.view = new GUI(this);
        this.opponent = new NaiveOpponent();
        myTurn = true;
    }

    @Override
    public void run() {
        view.setVisible(true);
    }

    private void resetGame() {
        myTurn = true;
        model = new Board();
        view.updateBoard(model.getBoard());
    }

    @Override
    public void handleBoardClick(int position) {
        try {
            if (myTurn) {
                makePlayerMove(position, Cell.CROSS);
                if (checkIfGameOver()) return;

                makeOpponentMove();
                checkIfGameOver();
            }
        } catch (IllegalStateException exception) {
            view.displayMessage("Illegal move");
        }
    }

    private void makePlayerMove(int position, Cell cross) {
        model.makeMove(cross, position);
        view.updateBoard(model.getBoard());
        myTurn = false;
    }

    private void makeOpponentMove() {
        Timer timer = new Timer(opponent.getDelayTime(), (e) -> {
            makePlayerMove(opponent.makeChoice(model.getBoard()), Cell.NOUGHT);
            view.updateBoard(model.getBoard());
            myTurn = true;
        });

        timer.setRepeats(false);
        timer.start();
    };



    private boolean checkIfGameOver() {
        Board.GameStatus gameStatus = model.checkGameStatus();
        if (gameStatus != Board.GameStatus.RUNNING) {
            handleGameOver(gameStatus);
            return true;
        }
        return false;
    }

    private void handleGameOver(Board.GameStatus gameStatus) {
        view.displayMessage(gameStatus.getMessage());
        resetGame();
    }
}

