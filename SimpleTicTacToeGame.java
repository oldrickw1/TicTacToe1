import javax.swing.*;

public class SimpleTicTacToeGame implements Controller{
    private Board model;
    private final GUI view;
    private final Opponent opponent;
    private boolean myTurn;

    public SimpleTicTacToeGame(Board model) {

        this.model = model;
        this.view = new GUI(this);
        this.opponent = new SmarterOpponent();
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
        if (myTurn && model.isEmptyCell(position)) {
            makePlayerMove(position, Cell.CROSS);
            if (isGameOver()) {
                return;
            }
            makeOpponentMove();
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
            isGameOver();
        });

        timer.setRepeats(false);
        timer.start();
    }


    private boolean isGameOver() {
        GameStatus gameStatus = model.checkGameStatus();
        if (gameStatus != GameStatus.RUNNING) {
            handleGameOver(gameStatus);
            return true;
        }
        return false;
    }

    private void handleGameOver(GameStatus gameStatus) {
        view.displayMessage(gameStatus.getMessage());
        resetGame();
    }
}

