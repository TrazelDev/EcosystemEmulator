public class Direction {
    private int direction;
    public Direction() {
        this.direction = DOWN;
    }

    public int getDirection()
    {
        return direction;
    }

    public void setDirection(int newDirection)
    {
        this.direction = newDirection;
    }

    public static final int DOWN  = 0;
    public static final int LEFT  = 1;
    public static final int UP    = 2;
    public static final int RIGHT = 3;
    public static final int[] xDirectionChange = { 0, -1, 0, 1 };
    public static final int[] yDirectionChange = { 1, 0, -1, 0 };
}
