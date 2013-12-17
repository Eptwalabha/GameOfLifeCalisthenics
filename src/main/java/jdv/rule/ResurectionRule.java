package jdv.rule;

import jdv.CellState;
import jdv.ConwayCell;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Eptwalabha
 * Date: 17/12/13
 * Time: 00:45
 */
public class ResurectionRule implements ConwayRule {
    @Override
    public void applyRule(ConwayCell cell, List<ConwayCell> neighbour) {
        if (neighbour.size() == 3)
            cell.setNextStepState(CellState.ALIVE);
    }
}
