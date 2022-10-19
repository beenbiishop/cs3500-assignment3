package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a textual view of a Marble Solitaire game.
 *
 * <p>The class takes in a MarbleSolitaireModel and generates a textual representation of the model
 * based on the slot state of each slot on the board. Marbles are represented as "O", empty slots
 * are represented as "_", and invalid slots are represented as " ". At the end of each row, if only
 * invalid slots remain, a new line is started.</p>
 */
public class MarbleSolitaireTextView extends AbstractSolitaireTextView {

  /**
   * Constructs a new text view of the given model with a given appendable.
   *
   * @param model      the model object to be represented
   * @param appendable the appendable to append the text to
   * @throws IllegalArgumentException if the model or appendable are null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable appendable)
      throws IllegalArgumentException {
    super(model, appendable);
  }

  /**
   * Constructs a new text view of the given model.
   *
   * @param model the model object to be represented
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    super(model);
  }

}
