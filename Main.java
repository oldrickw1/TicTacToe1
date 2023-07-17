public class Main {
    public static void main(String[] args) {
        Board board  = new Board();
        Controller controller = new SimpleTicTacToeGame(board);

        controller.run();
    }
}
