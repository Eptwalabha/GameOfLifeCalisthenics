package jdv.rule;

import jdv.CellState;
import jdv.ConwayCell;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Eptwalabha
 * Date: 17/12/13
 * Time: 00:43
 */
public class UnderPopulateRule implements ConwayRule {

    @Override
    public void applyRule(ConwayCell cell, List<ConwayCell> neighbour) {
        if (neighbour.size() < 2)
            cell.setNextStepState(CellState.DEAD);
    }
}
