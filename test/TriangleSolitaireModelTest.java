import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the TriangleSolitaireModel class and its methods.
 */
public class TriangleSolitaireModelTest {

  MarbleSolitaireModel noParams;
  MarbleSolitaireModel dimension6;
  MarbleSolitaireModel emptySlot3by3;
  MarbleSolitaireModel dimension7EmptySlot3by3;

  @Before
  public void setUp() {
    noParams = new TriangleSolitaireModel();
    dimension6 = new TriangleSolitaireModel(6);
    emptySlot3by3 = new TriangleSolitaireModel(3, 3);
    dimension7EmptySlot3by3 = new TriangleSolitaireModel(7, 3, 3);
  }

  @Test
  public void testDefaultConstructor() {
    assertEquals(SlotState.Empty, noParams.getSlotAt(0, 0));
    assertEquals(SlotState.Invalid, noParams.getSlotAt(3, 4));
    assertEquals(SlotState.Invalid, noParams.getSlotAt(2, 4));
    assertEquals(SlotState.Marble, noParams.getSlotAt(3, 0));
    assertEquals(SlotState.Marble, noParams.getSlotAt(4, 2));
  }

  @Test
  public void testOneArgumentConstructor() {
    assertEquals(SlotState.Empty, dimension6.getSlotAt(0, 0));
    assertEquals(SlotState.Invalid, dimension6.getSlotAt(0, 2));
    assertEquals(SlotState.Invalid, dimension6.getSlotAt(1, 3));
    assertEquals(SlotState.Marble, dimension6.getSlotAt(4, 0));
    assertEquals(SlotState.Marble, dimension6.getSlotAt(5, 3));
  }

  @Test
  public void testTwoArgumentConstructor() {
    assertEquals(SlotState.Empty, emptySlot3by3.getSlotAt(3, 3));
    assertEquals(SlotState.Invalid, emptySlot3by3.getSlotAt(0, 1));
    assertEquals(SlotState.Invalid, emptySlot3by3.getSlotAt(1, 2));
    assertEquals(SlotState.Marble, emptySlot3by3.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, emptySlot3by3.getSlotAt(4, 4));
  }

  @Test
  public void testThreeArgumentConstructor() {
    assertEquals(SlotState.Empty, dimension7EmptySlot3by3.getSlotAt(3, 3));
    assertEquals(SlotState.Invalid, dimension7EmptySlot3by3.getSlotAt(5, 6));
    assertEquals(SlotState.Invalid, dimension7EmptySlot3by3.getSlotAt(3, 5));
    assertEquals(SlotState.Marble, dimension7EmptySlot3by3.getSlotAt(5, 3));
    assertEquals(SlotState.Marble, dimension7EmptySlot3by3.getSlotAt(4, 2));
  }

}
