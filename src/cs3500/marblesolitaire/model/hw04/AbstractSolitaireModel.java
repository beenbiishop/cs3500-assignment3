package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents the abstract class for a marble solitaire model.
 *
 * <p>The class will instantiate the game/board based on three given parameters:
 * <ol>
 *   <li>The arm thickness</li>
 *    <li>The empty slot row</li>
 *    <li>The empty slot column</li>
 *  </ol>
 *  The user must either specify all three parameters. The class will then initialize a new board
 *  with the given parameters, and return an exception error if any of the parameters are invalid.
 *  </p>
 *  <p>
 *    After initialization, the class will then handle the logic of any moves made by the user. The
 *    user can move a marble by specifying a starting and ending position. The user can also get
 *    the size of the board, get the state of a slot on the board, get the current score of the
 *    game, and check if the game is over. Though the user will never directly interact with this
 *    class, it will be used by the controller to handle the logic of the game.
 *  </p>
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {

  private final int armThickness;
  private final int sRow;
  private final int sCol;
  private final SlotState[][] board;

  /**
   * Constructs a new model with the specified arm thickness and the empty slot at the specified row
   * and column.
   *
   * @param armThickness the arm thickness of the board
   * @param sRow         the row of the starting empty slot
   * @param sCol         the column of the starting empty slot
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or the empty
   *                                  slot position is invalid
   */
  public AbstractSolitaireModel(int armThickness, int sRow, int sCol)
      throws IllegalArgumentException {
    if (armThickness % 2 == 0 || armThickness < 1) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number");
    }
    if (!isValidSlot(sRow, sCol, armThickness)) {
      throw new IllegalArgumentException(
          "Invalid empty cell position (" + sRow + ", " + sCol + ")");
    }
    this.armThickness = armThickness;
    this.sRow = sRow;
    this.sCol = sCol;
    this.board = new SlotState[this.getBoardSize()][this.getBoardSize()];
    this.initializeBoard();
  }

  /**
   * Initializes each slot's state on the board.
   *
   * <p>Valid slots are set to Marble. Invalid slots are set to Invalid. The starting empty slot is
   * overridden to Empty.</p>
   */
  private void initializeBoard() {
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (this.isValidSlot(row, col)) {
          this.board[row][col] = SlotState.Marble;
        } else {
          this.board[row][col] = SlotState.Invalid;
        }
      }
    }
    this.board[this.sRow][this.sCol] = SlotState.Empty;
  }

  /**
   * Returns whether the given slot position is valid based on the given arm thickness.
   *
   * @param row          the row of the slot to validate
   * @param col          the column of the slot to validate
   * @param armThickness the board's arm thickness to validate against
   * @return true if the slot position is valid, false otherwise
   */
  private boolean isValidSlot(int row, int col, int armThickness) {
    int armFirstRowCol = armThickness - 1;
    int armLastRowCol = armThickness * 2 - 2;
    int boardSize = armThickness * 3 - 2;
    return row >= 0 && col >= 0 && row < boardSize && col < boardSize && (
        (row >= armFirstRowCol && row <= armLastRowCol) || (col >= armFirstRowCol
            && col <= armLastRowCol));
  }

  /**
   * Works just like {@link AbstractSolitaireModel#isValidSlot(int, int, int)} except
   * {@code armThickness} defaults to the board's {@link AbstractSolitaireModel#armThickness}.
   *
   * @see AbstractSolitaireModel#isValidSlot(int, int, int)
   */
  private boolean isValidSlot(int row, int col) {
    return isValidSlot(row, col, this.armThickness);
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.armThickness * 3 - 2;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond the dimensions of the
   *                                  board
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0 || row >= this.getBoardSize() || col >= this.getBoardSize()) {
      throw new IllegalArgumentException("Slot position is beyond board dimensions");
    }
    return this.board[row][col];
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    int score = 0;
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (this.getSlotAt(row, col) == SlotState.Marble) {
          score++;
        }
      }
    }
    return score;
  }

  /**
   * Returns whether a move from a given slot position to a given slot position is valid.
   *
   * <p>A move is valid when:
   * <ul>
   *   <li>The from slot is valid and on the board</li>
   *   <li>The to slot is valid on the board</li>
   *   <li>The from slot has a slot state of marble</li>
   *   <li>The to slot has a slot state of empty</li>
   *   <li>The from slot and the to slot are exactly two slots apart in one direction</li>
   *   <li>The slot in between the from slot and to slot has a state of marble</li>
   * </ul></p>
   *
   * @param fromRow the row number of the slot to move from (starts at 0)
   * @param fromCol the column number of the slot to move from (starts at 0)
   * @param toRow   the row number of the slot to move to (starts at 0)
   * @param toCol   the column number of the slot to move to (starts at 0)
   * @return true if the given move is valid, false otherwise
   */
  private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    int horDiff = Math.abs(fromRow - toRow);
    int verDiff = Math.abs(fromCol - toCol);
    int midRow = (fromRow + toRow) / 2;
    int midCol = (fromCol + toCol) / 2;
    return this.isValidSlot(fromRow, fromCol) && this.isValidSlot(toRow, toCol)
        && this.getSlotAt(fromRow, fromCol) == SlotState.Marble
        && this.getSlotAt(toRow, toCol) == SlotState.Empty && ((horDiff == 2 && verDiff == 0) ^ (
        horDiff == 0 && verDiff == 2)) && this.getSlotAt(midRow, midCol) == SlotState.Marble;
  }

  /**
   * If a given from position and to position result in a valid move, the move is made on the board.
   * A valid move is checked using {@link #isValidMove(int, int, int, int)}.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the given from position and given to position results in an
   *                                  invalid move according to the isValidMove method
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!this.isValidMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException(
          "Invalid move from (" + fromRow + ", " + fromCol + ") to (" + toRow + ", " + toCol + ")");
    }
    int midRow = (fromRow + toRow) / 2;
    int midCol = (fromCol + toCol) / 2;
    this.board[fromRow][fromCol] = SlotState.Empty;
    this.board[midRow][midCol] = SlotState.Empty;
    this.board[toRow][toCol] = SlotState.Marble;
  }

  @Override
  public boolean isGameOver() {
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (this.getSlotAt(row, col) == SlotState.Marble) {
          if (this.isValidMove(row, col, row + 2, col)) {
            return false;
          }
          if (this.isValidMove(row, col, row - 2, col)) {
            return false;
          }
          if (this.isValidMove(row, col, row, col + 2)) {
            return false;
          }
          if (this.isValidMove(row, col, row, col - 2)) {
            return false;
          }
        }
      }
    }
    return true;
  }

}
