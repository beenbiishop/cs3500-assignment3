import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import org.junit.Assert;
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

  @Test
  public void testInvalidConstructors() {
    try {
      new TriangleSolitaireModel(8, 3);
      fail("Did not throw exception when passed invalid empty cell");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid empty cell position (8, 3)", error.getMessage());
    }

    try {
      new TriangleSolitaireModel(-1);
      fail("Did not throw exception when passed negative dimension");
    } catch (IllegalArgumentException error) {
      assertEquals("Arm thickness must be a positive number", error.getMessage());
    }

    try {
      new TriangleSolitaireModel(0, 3, 3);
      fail("Did not throw exception when passed invalid dimension");
    } catch (IllegalArgumentException error) {
      assertEquals("Arm thickness must be a positive number", error.getMessage());
    }

    try {
      new TriangleSolitaireModel(6, 6, 6);
      fail("Did not throw exception when passed invalid empty cell for valid dimension");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid empty cell position (6, 6)", error.getMessage());
    }
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(6, dimension6.getBoardSize());
    assertEquals(5, noParams.getBoardSize());
    assertEquals(7, dimension7EmptySlot3by3.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    Assert.assertEquals(SlotState.Empty, new TriangleSolitaireModel(1).getSlotAt(0, 0));
    assertEquals(SlotState.Invalid, dimension7EmptySlot3by3.getSlotAt(1, 2));
    assertEquals(SlotState.Marble, noParams.getSlotAt(2, 0));
  }

  @Test
  public void testGetSlotAtBeyondBoard() {
    try {
      dimension7EmptySlot3by3.getSlotAt(-1, 0);
      fail("Did not throw exception for negative slot");
    } catch (IllegalArgumentException error) {
      assertEquals("Slot position is beyond board dimensions", error.getMessage());
    }

    try {
      noParams.getSlotAt(7, 7);
      fail("Did not throw exception for slot beyond board");
    } catch (IllegalArgumentException error) {
      assertEquals("Slot position is beyond board dimensions", error.getMessage());
    }
  }

  @Test
  public void testGetScore() {
    assertEquals(20, dimension6.getScore());
    assertEquals(27, dimension7EmptySlot3by3.getScore());
    assertEquals(14, noParams.getScore());
    noParams.move(2, 2, 0, 0);
    noParams.move(2, 0, 2, 2);
    noParams.move(4, 3, 2, 1);
    assertEquals(11, noParams.getScore());
  }

  @Test
  public void testMove() {
    emptySlot3by3.move(3, 1, 3, 3);
    assertSame(emptySlot3by3.getSlotAt(3, 1), SlotState.Empty);
    assertSame(emptySlot3by3.getSlotAt(3, 2), SlotState.Empty);
    assertSame(emptySlot3by3.getSlotAt(3, 3), SlotState.Marble);

    noParams.move(2, 0, 0, 0);
    noParams.move(2, 2, 2, 0);
    assertSame(noParams.getSlotAt(1, 0), SlotState.Empty);
    assertSame(noParams.getSlotAt(2, 1), SlotState.Empty);
    assertSame(noParams.getSlotAt(2, 2), SlotState.Empty);
    assertSame(noParams.getSlotAt(0, 0), SlotState.Marble);
  }

  @Test
  public void testMoveInvalid() {
    try {
      MarbleSolitaireModel startTop = new TriangleSolitaireModel(3, 1);
      startTop.move(3, -1, 3, 1);
      fail("Did not throw exception for invalid from position");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (3, -1) to (3, 1)", error.getMessage());
    }

    try {
      MarbleSolitaireModel startBottom = new TriangleSolitaireModel(1, 0);
      startBottom.move(2, 1, 2, -1);
      fail("Did not throw exception for invalid to position");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (2, 1) to (2, -1)", error.getMessage());
    }

    try {
      noParams.move(0, 0, 2, 2);
      fail("Did not throw exception for from slot that is not a marble");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (0, 0) to (2, 2)", error.getMessage());
    }

    try {
      noParams.move(2, 0, 2, 2);
      fail("Did not throw an exception for to slot that is not empty");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (2, 0) to (2, 2)", error.getMessage());
    }

    try {
      noParams.move(3, 0, 3, 3);
      fail("Did not throw an exception for move that is not 2 spaces in one direction");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (3, 0) to (3, 3)", error.getMessage());
    }

    try {
      dimension7EmptySlot3by3.move(3, 1, 3, 3);
      dimension7EmptySlot3by3.move(3, 3, 3, 1);
      fail("Did not throw an exception for move that does not have a marble in between");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (3, 3) to (3, 1)", error.getMessage());
    }
  }

}
