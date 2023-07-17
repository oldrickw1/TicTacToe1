public enum GameStatus {
    RUNNING(null),
    CROSS_WINS("Player X has won!"),
    NOUGHT_WINS("Player O has won!"),
    TIE("Tie!");

    private final String message;

    GameStatus(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}