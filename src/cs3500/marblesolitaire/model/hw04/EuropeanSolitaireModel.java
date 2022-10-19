package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a marble solitaire game with the European rules.
 *
 * <p>The class will instantiate the game/board based on three given parameters:
 * <ol>
 *   <li>The arm thickness</li>
 *   <li>The empty slot row</li>
 *   <li>The empty slot column</li>
 * </ol>
 * The user must either specify no parameters, just the arm thickness, just the empty slot
 * position, or all three. The class will then initialize a new board with the given parameters,
 * and return an exception error if any of the parameters are invalid.
 * </p>
 * <p>
 *   After initialization, the class will then handle the logic of any moves made by the user. The
 *   user can move a marble by specifying a starting and ending position. The user can also get
 *   the size of the board, get the state of a slot on the board, get the current score of the
 *   game, and check if the game is over. Though the user will never directly interact with this
 *   class, it will be used by the controller to handle the logic of the game.
 * </p>
 */
public class EuropeanSolitaireModel extends AbstractSolitaireModel {

  /**
   * Constructs a new model with the default arm thickness of 3 and the empty slot in the center.
   */
  public EuropeanSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Constructs a new model with an adjustable arm thickness and the empty slot in the center.
   *
   * @param sideLength the side length of the board
   * @throws IllegalArgumentException if the side length is not a positive odd number
   */
  public EuropeanSolitaireModel(int sideLength) throws IllegalArgumentException {
    super(sideLength, (sideLength - 1) / 2, (sideLength - 1) / 2);
  }

  /**
   * Constructs a new model with the default arm thickness of 3 and the empty slot at the specified
   * row and column.
   *
   * @param sRow the row of the starting empty slot
   * @param sCol the column of the starting empty slot
   * @throws IllegalArgumentException if the empty slot position is invalid
   */
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);
  }

  /**
   * Constructs a new model with the specified arm thickness and the empty slot at the specified row
   * and column.
   *
   * @param sideLength the side length of the board
   * @param sRow       the row of the starting empty slot
   * @param sCol       the column of the starting empty slot
   * @throws IllegalArgumentException if the empty slot position is invalid
   */
  public EuropeanSolitaireModel(int sideLength, int sRow, int sCol)
      throws IllegalArgumentException {
    super(sideLength, sRow, sCol);
  }

//  /**
//   * Returns whether a move from a given slot position to a given slot position is valid.
//   *
//   * <p>A move is valid when:
//   * <ul>
//   *   <li>The from slot is valid and on the board</li>
//   *   <li>The to slot is valid on the board</li>
//   *   <li>The from slot has a slot state of marble</li>
//   *   <li>The to slot has a slot state of empty</li>
//   *   <li>The from slot and the to slot are exactly two slots apart in one direction</li>
//   *   <li>The slot in between the from slot and to slot has a state of marble</li>
//   * </ul></p>
//   *
//   * @param fromRow the row number of the slot to move from (starts at 0)
//   * @param fromCol the column number of the slot to move from (starts at 0)
//   * @param toRow   the row number of the slot to move to (starts at 0)
//   * @param toCol   the column number of the slot to move to (starts at 0)
//   * @return true if the given move is valid, false otherwise
//   */
//  private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
//    int horDiff = Math.abs(fromRow - toRow);
//    int verDiff = Math.abs(fromCol - toCol);
//    int midRow = (fromRow + toRow) / 2;
//    int midCol = (fromCol + toCol) / 2;
//    return this.isValidSlot(fromRow, fromCol) && this.isValidSlot(toRow, toCol)
//        && this.getSlotAt(fromRow, fromCol) == SlotState.Marble
//        && this.getSlotAt(toRow, toCol) == SlotState.Empty && ((horDiff == 2 && verDiff == 0) ^ (
//        horDiff == 0 && verDiff == 2)) && this.getSlotAt(midRow, midCol) == SlotState.Marble;
//  }

  /**
   * Returns whether the given slot position is valid based on the given arm thickness.
   *
   * @param row        the row of the slot to validate
   * @param col        the column of the slot to validate
   * @param sideLength the side length of the board
   * @return true if the slot position is valid, false otherwise
   */
  @Override
  protected boolean isValidSlot(int row, int col, int sideLength) {
//    int armFirstRowCol = sideLength - 1;
//    int armLastRowCol = sideLength * 2 - 2;
//    int boardSize = sideLength * 3 - 2;
    int a = ((sideLength - 1) / 2) + sideLength - 1;
    int b = sideLength / 2;
    return Math.abs(a - col) + Math.abs(a - row) <= a + b;
  }

}
