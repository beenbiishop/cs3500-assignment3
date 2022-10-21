package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a marble solitaire game with the European rules.
 *
 * <p>The class will instantiate the game/board based on three given parameters:
 * <ol>
 *   <li>The side length</li>
 *   <li>The empty slot row</li>
 *   <li>The empty slot column</li>
 * </ol>
 * The user must either specify no parameters, just the side length, just the empty slot
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
public class TriangleSolitaireModel extends AbstractSolitaireModel {

  /**
   * Constructs a new model with the default bottom row dimension of 5 and the empty slot at (0,
   * 0).
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }

  /**
   * Constructs a new model with the default bottom row dimension of 5 and the empty slot at the
   * specified position.
   *
   * @param sRow the row of the starting empty slot
   * @param sCol the column of the starting empty slot
   * @throws IllegalArgumentException if the empty slot position is invalid
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(5, sRow, sCol);
  }

  /**
   * Constructs a new model with the specified bottom row dimension and the empty slot at (0, 0).
   *
   * @param dimension the bottom row dimension of the board
   * @throws IllegalArgumentException if the bottom row dimension is not a positive number
   */
  public TriangleSolitaireModel(int dimension) throws IllegalArgumentException {
    super(dimension, 0, 0);
  }

  /**
   * Constructs a new model with the specified bottom row dimension and the empty slot at the
   * specified position.
   *
   * @param dimension the bottom row dimension of the board
   * @param sRow      the row of the starting empty slot
   * @param sCol      the column of the starting empty slot
   * @throws IllegalArgumentException if the bottom row dimension is not a positive number or the
   *                                  empty slot position is invalid
   */
  public TriangleSolitaireModel(int dimension, int sRow, int sCol) throws IllegalArgumentException {
    super(dimension, sRow, sCol);
  }

  @Override
  protected void checkParams(int armThickness, int sRow, int sCol) {
    if (armThickness < 1) {
      throw new IllegalArgumentException("Arm thickness must be a positive number.");
    }
    if (!isValidSlot(sRow, sCol, armThickness)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
  }

  @Override
  public int getBoardSize() {
    return this.armThickness;
  }

  @Override
  protected boolean isValidSlot(int row, int col, int dimension) {
    return (row < dimension) && (row >= 0) && (col >= 0) && (row >= col);
  }

  @Override
  protected boolean isValidSlot(int row, int col) {
    return this.isValidSlot(row, col, this.armThickness);
  }

  @Override
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    int midRow = (fromRow + toRow) / 2;
    int midCol = (fromCol + toCol) / 2;
    return this.isValidSlot(fromRow, fromCol) && this.isValidSlot(toRow, toCol) && this.isValidSlot(
        midRow, midCol) && this.getSlotAt(fromRow, fromCol) == SlotState.Marble
        && this.getSlotAt(toRow, toCol) == SlotState.Empty
        && this.getSlotAt(midRow, midCol) == SlotState.Marble && !(((fromRow + toRow) % 2) != 0
        || ((fromCol + toCol) % 2) != 0 || ((fromCol - toCol) * (fromRow - toRow) < 0) || ((
        Math.abs(fromRow - toRow) > 2)) || (Math.abs(fromCol - toCol) > 2));

  }

}
