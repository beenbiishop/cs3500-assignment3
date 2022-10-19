package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractSolitaireModel;

/**
 * Represents a marble solitaire game with the English rules.
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
public class EnglishSolitaireModel extends AbstractSolitaireModel {

  /**
   * Constructs a new model with the default arm thickness of 3 and the empty slot in the center.
   */
  public EnglishSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Constructs a new model with the default arm thickness of 3 and the empty slot at the specified
   * row and column.
   *
   * @param sRow the row of the starting empty slot
   * @param sCol the column of the starting empty slot
   * @throws IllegalArgumentException if the empty slot position is invalid
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);
  }

  /**
   * Constructs a new model with the specified arm thickness and the empty slot in the center.
   *
   * @param armThickness the arm thickness of the board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness, (armThickness * 3 - 2) / 2, (armThickness * 3 - 2) / 2);
  }

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
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
      throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
  }

}
