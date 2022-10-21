package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

/**
 * Represents a textual view of a Marble Solitaire game following the triangle rules.
 *
 * <p>The class takes in a MarbleSolitaireModel and generates a textual representation of the model
 * based on the slot state of each slot on the board. Marbles are represented as "O", empty slots
 * are represented as "_", and invalid slots are represented as " ". At the end of each row, if only
 * invalid slots remain, a new line is started.</p>
 */
public class TriangleSolitaireTextView extends AbstractSolitaireTextView {

  /**
   * Constructs a new text view of the given model with a given appendable.
   *
   * @param model      the model object to be represented
   * @param appendable the appendable to append the text to
   * @throws IllegalArgumentException if the model or appendable are null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable appendable)
      throws IllegalArgumentException {
    super(model, appendable);
  }

  /**
   * Constructs a new text view of the given model.
   *
   * @param model the model object to be represented
   * @throws IllegalArgumentException if the model is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
      throws IllegalArgumentException {
    super(model);
  }

  @Override
  public String toString() {
    StringBuilder state = new StringBuilder();
    for (int row = 0; row < this.model.getBoardSize(); row++) {
      for (int space = model.getBoardSize(); space > row + 1; space--) {
        state.append(" ");
      }
      for (int col = 0; col < this.model.getBoardSize(); col++) {
        if ((this.model.getSlotAt(row, col)) == SlotState.Invalid) {
          if (col < row) {
            state.append(" ");
          }
        }
        if ((this.model.getSlotAt(row, col)) == SlotState.Empty) {
          if (col == this.model.getBoardSize() - 1) {
            state.append("_");
          } else {
            if (needsInnerSpace(row, col)) {
              state.append("_");
            } else {
              state.append("_ ");
            }
          }
        }
        if ((this.model.getSlotAt(row, col)) == SlotState.Marble) {
          if (col == this.model.getBoardSize() - 1) {
            state.append("O");
          } else {
            if (needsInnerSpace(row, col)) {
              state.append("O");
            } else {
              state.append("O ");
            }
          }
        }
      }
      if (row < this.model.getBoardSize() - 1) {
        state.append("\n");
      }
    }
    return state.toString();
  }

  @Override
  protected boolean needsInnerSpace(int row, int col) {
    if (col + 2 <= this.model.getBoardSize()) {
      SlotState slot = this.model.getSlotAt(row, col + 1);
      return slot == SlotState.Invalid;
    } else {
      return false;
    }
  }

}
