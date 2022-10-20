import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import org.junit.Before;
import org.junit.Test;

public class EuropeanTextViewTest {

  MarbleSolitaireModel noParams;
  MarbleSolitaireModel sideLength5;
  MarbleSolitaireView viewNoParams;
  MarbleSolitaireView viewSideLength5;

  @Before
  public void setUp() {
    noParams = new EuropeanSolitaireModel();
    sideLength5 = new EuropeanSolitaireModel(5);
    viewNoParams = new MarbleSolitaireTextView(noParams);
    viewSideLength5 = new MarbleSolitaireTextView(sideLength5);
  }

  @Test
  public void testInvalidConstructor() {
    try {
      new MarbleSolitaireTextView(null);
    } catch (IllegalArgumentException e) {
      assertEquals("The model and appendable cannot be null", e.getMessage());
    }
  }

  @Test
  public void testToString() {
    assertEquals(
        "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", viewNoParams.toString());
    assertEquals(
        "        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O", viewSideLength5.toString());
  }

}
