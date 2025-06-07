package Simulator;

public class BoardNotBigEnoughException extends RuntimeException {
    public BoardNotBigEnoughException() {
        super("Organisms must not exceed more then 80% of the board");
    }
}
