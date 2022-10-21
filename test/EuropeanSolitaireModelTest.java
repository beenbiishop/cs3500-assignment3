import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the EuropeanSolitaireModel class and its methods.
 */
public class EuropeanSolitaireModelTest {

  MarbleSolitaireModel noParams;
  MarbleSolitaireModel sideLength5;
  MarbleSolitaireModel emptySlot0by4;
  MarbleSolitaireModel armThickness5EmptySlot4by4;

  @Before
  public void setUp() {
    noParams = new EuropeanSolitaireModel();
    sideLength5 = new EuropeanSolitaireModel(5);
    emptySlot0by4 = new EuropeanSolitaireModel(0, 4);
    armThickness5EmptySlot4by4 = new EuropeanSolitaireModel(5, 4, 4);
  }

  @Test
  public void testDefaultConstructor() {
    assertEquals(SlotState.Empty, noParams.getSlotAt(3, 3));
    assertEquals(SlotState.Invalid, noParams.getSlotAt(0, 0));
    assertEquals(SlotState.Invalid, noParams.getSlotAt(1, 6));
    assertEquals(SlotState.Marble, noParams.getSlotAt(3, 0));
    assertEquals(SlotState.Marble, noParams.getSlotAt(5, 4));
  }

  @Test
  public void testOneArgumentConstructor() {
    assertEquals(SlotState.Empty, sideLength5.getSlotAt(6, 6));
    assertEquals(SlotState.Invalid, sideLength5.getSlotAt(2, 1));
    assertEquals(SlotState.Invalid, sideLength5.getSlotAt(12, 12));
    assertEquals(SlotState.Marble, sideLength5.getSlotAt(4, 3));
    assertEquals(SlotState.Marble, sideLength5.getSlotAt(0, 8));
  }

  @Test
  public void testTwoArgumentConstructor() {
    assertEquals(SlotState.Empty, emptySlot0by4.getSlotAt(0, 4));
    assertEquals(SlotState.Invalid, emptySlot0by4.getSlotAt(1, 0));
    assertEquals(SlotState.Invalid, emptySlot0by4.getSlotAt(5, 6));
    assertEquals(SlotState.Marble, emptySlot0by4.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, emptySlot0by4.getSlotAt(4, 3));
  }

  @Test
  public void testThreeArgumentConstructor() {
    assertEquals(SlotState.Empty, armThickness5EmptySlot4by4.getSlotAt(4, 4));
    assertEquals(SlotState.Invalid, armThickness5EmptySlot4by4.getSlotAt(12, 12));
    assertEquals(SlotState.Invalid, armThickness5EmptySlot4by4.getSlotAt(1, 11));
    assertEquals(SlotState.Marble, armThickness5EmptySlot4by4.getSlotAt(5, 3));
    assertEquals(SlotState.Marble, armThickness5EmptySlot4by4.getSlotAt(2, 8));
  }

  @Test
  public void testInvalidConstructors() {
    try {
      new EuropeanSolitaireModel(8, 3);
      fail("Did not throw exception when passed invalid empty cell");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid empty cell position (8, 3)", error.getMessage());
    }

    try {
      new EuropeanSolitaireModel(2);
      fail("Did not throw exception when passed even arm thickness");
    } catch (IllegalArgumentException error) {
      assertEquals("Arm thickness must be a positive odd number", error.getMessage());
    }

    try {
      new EuropeanSolitaireModel(-1);
      fail("Did not throw exception when passed negative arm thickness");
    } catch (IllegalArgumentException error) {
      assertEquals("Arm thickness must be a positive odd number", error.getMessage());
    }

    try {
      new EuropeanSolitaireModel(-4);
      fail("Did not throw exception when passed negative and even arm thickness");
    } catch (IllegalArgumentException error) {
      assertEquals("Arm thickness must be a positive odd number", error.getMessage());
    }

    try {
      new EuropeanSolitaireModel(2, 3, 3);
      fail("Did not throw exception when passed invalid arm thickness");
    } catch (IllegalArgumentException error) {
      assertEquals("Arm thickness must be a positive odd number", error.getMessage());
    }

    try {
      new EuropeanSolitaireModel(3, 4, 8);
      fail("Did not throw exception when passed invalid empty cell for valid arm thickness");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid empty cell position (4, 8)", error.getMessage());
    }
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, emptySlot0by4.getBoardSize());
    assertEquals(7, noParams.getBoardSize());
    assertEquals(13, armThickness5EmptySlot4by4.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    Assert.assertEquals(SlotState.Empty, new EuropeanSolitaireModel(1).getSlotAt(0, 0));
    assertEquals(SlotState.Invalid, new EuropeanSolitaireModel(5).getSlotAt(1, 1));
    assertEquals(SlotState.Marble, noParams.getSlotAt(2, 0));
  }

  @Test
  public void testGetSlotAtBeyondBoard() {
    try {
      armThickness5EmptySlot4by4.getSlotAt(-1, 0);
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
    assertEquals(128, armThickness5EmptySlot4by4.getScore());
    assertEquals(128, new EuropeanSolitaireModel(5).getScore());
    assertEquals(36, noParams.getScore());
    noParams.move(3, 5, 3, 3);
    noParams.move(3, 2, 3, 4);
    noParams.move(1, 3, 3, 3);
    assertEquals(33, noParams.getScore());
  }

  @Test
  public void testMove() {
    emptySlot0by4.move(0, 2, 0, 4);
    assertSame(emptySlot0by4.getSlotAt(0, 2), SlotState.Empty);
    assertSame(emptySlot0by4.getSlotAt(0, 3), SlotState.Empty);
    assertSame(emptySlot0by4.getSlotAt(0, 4), SlotState.Marble);

    noParams.move(3, 5, 3, 3);
    noParams.move(3, 2, 3, 4);
    assertSame(noParams.getSlotAt(3, 2), SlotState.Empty);
    assertSame(noParams.getSlotAt(3, 3), SlotState.Empty);
    assertSame(noParams.getSlotAt(3, 5), SlotState.Empty);
    assertSame(noParams.getSlotAt(3, 4), SlotState.Marble);
  }

  @Test
  public void testMoveInvalid() {
    try {
      MarbleSolitaireModel startTop = new EnglishSolitaireModel(0, 3);
      startTop.move(-1, 3, 1, 3);
      fail("Did not throw exception for invalid from row");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (-1, 3) to (1, 3)", error.getMessage());
    }

    try {
      MarbleSolitaireModel startBottom = new EnglishSolitaireModel(6, 3);
      startBottom.move(5, 3, 7, 3);
      fail("Did not throw exception for invalid to row");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (5, 3) to (7, 3)", error.getMessage());
    }

    try {
      noParams.move(3, 3, 3, 5);
      fail("Did not throw exception for from slot that is not a marble");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (3, 3) to (3, 5)", error.getMessage());
    }

    try {
      noParams.move(3, 0, 3, 2);
      fail("Did not throw an exception for to slot that is not empty");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (3, 0) to (3, 2)", error.getMessage());
    }

    try {
      noParams.move(3, 2, 3, 3);
      fail("Did not throw an exception for move that is not 2 spaces in one direction");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (3, 2) to (3, 3)", error.getMessage());
    }

    try {
      noParams.move(1, 4, 3, 3);
      fail("Did not throw exception for move that is 2 spaces in one direction but not in a line");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (1, 4) to (3, 3)", error.getMessage());
    }

    try {
      noParams.move(4, 2, 2, 4);
      fail("Did not throw exception for move that is diagonal");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (4, 2) to (2, 4)", error.getMessage());
    }

    try {
      noParams.move(3, 5, 3, 3);
      noParams.move(3, 3, 3, 5);
      fail("Did not throw an exception for move that does not have a marble in between");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (3, 3) to (3, 5)", error.getMessage());
    }
  }

  @Test
  public void testIsGameOver() {
    assertFalse(noParams.isGameOver());
    assertFalse(armThickness5EmptySlot4by4.isGameOver());
    assertTrue(new EnglishSolitaireModel(1).isGameOver());

//    noParams.move(3, 1, 3, 3);
//    noParams.move(5, 2, 3, 2);
//    noParams.move(4, 0, 4, 2);
//    noParams.move(4, 3, 4, 1);
//    noParams.move(4, 5, 4, 3);
//    noParams.move(6, 4, 4, 4);
//    noParams.move(3, 4, 5, 4);
//    noParams.move(6, 2, 6, 4);
//    noParams.move(6, 4, 4, 4);
//    noParams.move(2, 2, 4, 2);
//    noParams.move(0, 2, 2, 2);
//    noParams.move(1, 4, 3, 4);
//    noParams.move(3, 4, 5, 4);
//    noParams.move(5, 4, 5, 2);
//    noParams.move(5, 2, 3, 2);
//    noParams.move(3, 2, 1, 2);
//    noParams.move(2, 0, 4, 0);
//    noParams.move(4, 0, 4, 2);
//    noParams.move(4, 2, 4, 4);
//    noParams.move(2, 6, 2, 4);
//    noParams.move(2, 3, 2, 5);
//    noParams.move(4, 6, 2, 6);
//    noParams.move(2, 6, 2, 4);
//    noParams.move(0, 4, 0, 2);
//    noParams.move(0, 2, 2, 2);
//    noParams.move(2, 1, 2, 3);
//    noParams.move(2, 3, 2, 5);
//    noParams.move(2, 5, 4, 5);
//    noParams.move(4, 5, 4, 3);
//    noParams.move(4, 3, 2, 3);
//    noParams.move(1, 3, 3, 3);
//    // TODO: fix to ensure that the game is over
//    assertTrue(noParams.isGameOver());
  }
}