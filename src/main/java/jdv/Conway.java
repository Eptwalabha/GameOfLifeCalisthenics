package jdv;
import jdv.rule.ConwayRule;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Eptwalabha
 * Date: 16/12/13
 * Time: 23:20
 */
public class Conway {

    private List<ConwayCell> cells = new ArrayList<ConwayCell>();
    private List<ConwayRule> rules = new ArrayList<ConwayRule>();

    public void addConwayRule(ConwayRule rule) {
        rules.add(rule);
    }


    public void addNewCell(ConwayCell conwayCell) {
        cells.add(conwayCell);
    }

    public void newtStep() {
        for (ConwayCell c : cells)
            this.applyRules(c);
        for (ConwayCell c : cells)
            c.validateStep();
    }

    private void applyRules(ConwayCell cell) {
        List<ConwayCell> neighbours = this.getCellsNeighbours(cell);
        for (ConwayRule rule : rules)
            rule.applyRule(cell, neighbours);
    }

    private List<ConwayCell> getCellsNeighbours(ConwayCell cell) {
        List<ConwayCell> neighbours = new ArrayList<ConwayCell>();
        for (ConwayCell c : cells)
            neighbours = cell.addAsNeighbour(c, neighbours);
        return neighbours;
    }

    public void removeAllCells() {
        cells = new ArrayList<ConwayCell>();
    }
}
