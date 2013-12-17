package jdv.rule;

import jdv.ConwayCell;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Eptwalabha
 * Date: 17/12/13
 * Time: 00:04
 */
public interface ConwayRule {
    public void applyRule(ConwayCell cell, List<ConwayCell> neighbour);
}
