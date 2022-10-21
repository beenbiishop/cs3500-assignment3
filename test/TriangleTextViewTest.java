import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import org.junit.Before;
import org.junit.Test;

public class TriangleTextViewTest {

  MarbleSolitaireModel noParams;
  MarbleSolitaireModel dimension20;
  MarbleSolitaireView viewNoParams;
  MarbleSolitaireView viewDimension20;

  @Before
  public void setUp() {
    noParams = new TriangleSolitaireModel();
    dimension20 = new TriangleSolitaireModel(20);
    viewNoParams = new TriangleSolitaireTextView(noParams);
    viewDimension20 = new TriangleSolitaireTextView(dimension20);
  }

  @Test
  public void testInvalidConstructor() {
    try {
      new TriangleSolitaireTextView(null);
    } catch (IllegalArgumentException e) {
      assertEquals("The model and appendable cannot be null", e.getMessage());
    }
  }

  @Test
  public void testToString() {
    assertEquals(
        "    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O", viewNoParams.toString());
    assertEquals(
        "                   _\n"
            + "                  O O\n"
            + "                 O O O\n"
            + "                O O O O\n"
            + "               O O O O O\n"
            + "              O O O O O O\n"
            + "             O O O O O O O\n"
            + "            O O O O O O O O\n"
            + "           O O O O O O O O O\n"
            + "          O O O O O O O O O O\n"
            + "         O O O O O O O O O O O\n"
            + "        O O O O O O O O O O O O\n"
            + "       O O O O O O O O O O O O O\n"
            + "      O O O O O O O O O O O O O O\n"
            + "     O O O O O O O O O O O O O O O\n"
            + "    O O O O O O O O O O O O O O O O\n"
            + "   O O O O O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O O O O O O O O\n"
            + " O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O", viewDimension20.toString());
  }

}
