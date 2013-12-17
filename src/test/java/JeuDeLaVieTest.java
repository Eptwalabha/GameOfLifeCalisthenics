import jdv.CellPosition;
import jdv.CellState;
import jdv.Conway;
import jdv.ConwayCell;
import jdv.rule.OverPopulateRule;
import jdv.rule.ResurectionRule;
import jdv.rule.UnderPopulateRule;
import org.junit.Before;
import org.junit.Test;

import static jdv.CellState.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/*
Règles du jeu de la vie de Conway:

règle 1: si une cellule vivante est entourée de moins de deux cellule vivante, elle meurt au prochain tour.
règle 2: si une cellule est entourée de deux cellules vivantes, elle concerve son état au prochain tour.
règle 3: si une cellule est entourée de trois cellules vivantes, elle devient vivante au prochain tour.
règle 4: si une cellule vivante est entourée de plus de trois cellules vivantes, elle meurt au prochain tour.
 */

/**
 * Created with IntelliJ IDEA.
 * User: Eptwalabha
 * Date: 16/12/13
 * Time: 23:14
 */
public class JeuDeLaVieTest {

    private Conway conway;
    private ConwayCell[][] cells = new ConwayCell[3][3];

    @Before
    public void setUp() {
        conway = new Conway();
        conway.addConwayRule(new UnderPopulateRule());
        conway.addConwayRule(new ResurectionRule());
        conway.addConwayRule(new OverPopulateRule());

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new ConwayCell(CellState.DEAD, new CellPosition(i, j));
                conway.addNewCell(cells[i][j]);
            }
        }
    }

    @Test
    public void canKillACellThatFeelsAlone() {

//        *00    000
//        0*0 -> 000
//        000    000

        setDispositionOfCells(
                ALIVE, DEAD, DEAD,
                DEAD, ALIVE, DEAD,
                DEAD, DEAD, DEAD);

        conway.newtStep();
        // the cell should dies because it has no living cells around
        assertThat(cells[1][1].getCellState(), is(DEAD));
    }

    @Test
    public void keepItsStateWhenSuroundByTwoLivingCells() {

//        **0    000
//        000 -> 000
//        000    000

        setDispositionOfCells(
                ALIVE, ALIVE, DEAD,
                DEAD, DEAD, DEAD,
                DEAD, DEAD, DEAD);

        conway.newtStep();
        assertThat(cells[1][1].getCellState(), is(DEAD));

//        **0    **0    **0
//        0*0 -> **0 -> **0
//        000    000    000

        setDispositionOfCells(
                ALIVE, ALIVE, DEAD,
                DEAD, ALIVE, DEAD,
                DEAD, DEAD, DEAD);

        conway.newtStep();
        assertThat(cells[1][1].getCellState(), is(ALIVE));

        assertThat(cells[0][0].getCellState(), is(ALIVE));
        assertThat(cells[0][1].getCellState(), is(ALIVE));
        assertThat(cells[1][0].getCellState(), is(ALIVE));

        conway.newtStep();
        assertThat(cells[0][0].getCellState(), is(ALIVE));
        assertThat(cells[0][1].getCellState(), is(ALIVE));
        assertThat(cells[1][0].getCellState(), is(ALIVE));
        assertThat(cells[1][1].getCellState(), is(ALIVE));

    }

    @Test
    public void canResurectADeadCell() {

//        111    010    000
//        000 -> 010 -> 000
//        000    000    000

        setDispositionOfCells(
                ALIVE, ALIVE, ALIVE,
                DEAD, DEAD, DEAD,
                DEAD, DEAD, DEAD);

        conway.newtStep();
        assertThat(cells[0][0].getCellState(), is(DEAD));
        assertThat(cells[0][1].getCellState(), is(ALIVE));
        assertThat(cells[0][2].getCellState(), is(DEAD));
        assertThat(cells[1][0].getCellState(), is(DEAD));
        assertThat(cells[1][1].getCellState(), is(ALIVE));
        assertThat(cells[1][2].getCellState(), is(DEAD));

        conway.newtStep();
        assertThat(cells[0][0].getCellState(), is(DEAD));
        assertThat(cells[0][1].getCellState(), is(DEAD));
        assertThat(cells[0][2].getCellState(), is(DEAD));
        assertThat(cells[1][0].getCellState(), is(DEAD));
        assertThat(cells[1][1].getCellState(), is(DEAD));
        assertThat(cells[1][2].getCellState(), is(DEAD));
    }

    @Test
    public void canKillACellThatCannotBreath() {

//        ***    ***    0*0    000
//        0*0 -> 000 -> 0*0 -> 000
//        0*0    000    000    000

        setDispositionOfCells(
                ALIVE, ALIVE, ALIVE,
                DEAD, ALIVE, DEAD,
                DEAD, ALIVE, DEAD );

        conway.newtStep();
        assertThat(cells[1][1].getCellState(), is(DEAD));
        conway.newtStep();
        assertThat(cells[1][1].getCellState(), is(ALIVE));
        conway.newtStep();
        assertThat(cells[1][1].getCellState(), is(DEAD));
    }

    public void setDispositionOfCells(
            CellState cell0_0, CellState cell0_1, CellState cell0_2,
            CellState cell1_0, CellState cell1_1, CellState cell1_2,
            CellState cell2_0, CellState cell2_1, CellState cell2_2 ) {

        cells[0][0].setNextStepState(cell0_0);
        cells[0][1].setNextStepState(cell0_1);
        cells[0][2].setNextStepState(cell0_2);

        cells[1][0].setNextStepState(cell1_0);
        cells[1][1].setNextStepState(cell1_1);
        cells[1][2].setNextStepState(cell1_2);

        cells[2][0].setNextStepState(cell2_0);
        cells[2][1].setNextStepState(cell2_1);
        cells[2][2].setNextStepState(cell2_2);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                cells[i][j].validateStep();

    }
}
