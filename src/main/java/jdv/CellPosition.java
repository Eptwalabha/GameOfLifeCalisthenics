package jdv;

import static java.lang.Math.*;

/**
 * Created with IntelliJ IDEA.
 * User: Eptwalabha
 * Date: 16/12/13
 * Time: 23:17
 */
public class CellPosition {
    int x;
    int y;
    public CellPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CellState getNeighbourStatus(CellPosition position) {
        if (abs(position.x - x) < 2 && abs(position.y - y) < 2 && !(position.x == x && position.y == y))
            return CellState.NEIGHBOUR;
        return CellState.NOT_NEIGHBOUR;
    }
}
