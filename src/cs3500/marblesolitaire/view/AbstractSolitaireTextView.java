package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import java.io.IOException;

public class AbstractSolitaireTextView implements MarbleSolitaireView {

  protected final MarbleSolitaireModelState model;
  protected final Appendable appendable;

  /**
   * Constructs a new text view of the given model with a given appendable.
   *
   * @param model      the model object to be represented
   * @param appendable the appendable to append the text to
   * @throws IllegalArgumentException if the model or appendable are null
   */
  public AbstractSolitaireTextView(MarbleSolitaireModelState model, Appendable appendable)
      throws IllegalArgumentException {
    if (model == null || appendable == null) {
      throw new IllegalArgumentException("The model and appendable cannot be null");
    }
    this.model = model;
    this.appendable = appendable;
  }

  /**
   * Constructs a new text view of the given model.
   *
   * @param model the model object to be represented
   */
  public AbstractSolitaireTextView(MarbleSolitaireModelState model) {
    this(model, System.out);
  }

  @Override
  public String toString() {
    StringBuilder state = new StringBuilder();
    for (int row = 0; row < this.model.getBoardSize(); row++) {
      for (int col = 0; col < this.model.getBoardSize(); col++) {
        int firstRowCol = (this.model.getBoardSize() - 1) / 3;
        int lastRowCol = (2 * (this.model.getBoardSize() - 1)) / 3;
        switch (this.model.getSlotAt(row, col)) {
          case Marble:
            state.append("O");
            break;
          case Invalid:
            if ((row < firstRowCol && col < lastRowCol) || (row > lastRowCol && col < lastRowCol)) {
              state.append(" ");
            }
            break;
          case Empty:
            state.append("_");
            break;
          default:
            throw new IllegalArgumentException("Invalid slot state");
        }
        if (needsInnerSpace(row, col)) {
          state.append(" ");
        }
      }
      if (row != this.model.getBoardSize() - 1) {
        state.append("\n");
      }
    }
    return state.toString();
  }

  /**
   * Determines whether a space is needed after a slot in the text view.
   *
   * @param row the row of the current slot
   * @param col the column of the current slot
   * @return true if a space is needed after the current slot, false otherwise
   */
  protected boolean needsInnerSpace(int row, int col) {
    if (col < (this.model.getBoardSize() - 1) / 2) {
      return true;
    }
    if (col + 2 <= this.model.getBoardSize()) {
      SlotState slot = this.model.getSlotAt(row, col + 1);
      return slot == SlotState.Marble || slot == SlotState.Empty;
    } else {
      return false;
    }
  }

  @Override
  public void renderBoard() throws IOException {
    try {
      this.appendable.append(this.toString());
    } catch (IOException e) {
      throw new IOException("Error when attempting to render board");
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.appendable.append(message);
    } catch (IOException e) {
      throw new IOException("Error when attempting to render message");
    }
  }

}
