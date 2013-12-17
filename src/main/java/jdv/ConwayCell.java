package jdv;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Eptwalabha
 * Date: 16/12/13
 * Time: 23:17
 */
public class ConwayCell {
    private CellState[] states;
    private CellPosition cellPosition;

    public ConwayCell(CellState cellState, CellPosition cellPosition) {
        states = new CellState[2];
        states[0] = cellState;
        states[1] = cellState;
        this.cellPosition = cellPosition;
    }

    public void validateStep() {
        states[0] = states[1];
    }

    public CellState getCellState() {
        return states[0];
    }

    public CellPosition getCellPosition() {
        return cellPosition;
    }

    public List<ConwayCell> addAsNeighbour(ConwayCell cell, List<ConwayCell> neighbours) {
        if (cell.states[0] == CellState.ALIVE && cellPosition.getNeighbourStatus(cell.getCellPosition()) == CellState.NEIGHBOUR)
            neighbours.add(cell);
        return neighbours;
    }

    public void setNextStepState(CellState state) {
        states[1] = state;
    }
}
