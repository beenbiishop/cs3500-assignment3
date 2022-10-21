package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import java.io.InputStreamReader;

/**
 * Runs the game in the terminal for the user.
 */
public final class MarbleSolitaire {

  /**
   * Initializes a new playable game for the user.
   *
   * @param args the arguments taken in by the main method
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model = new TriangleSolitaireModel(7, 3, 3);
    MarbleSolitaireView view = new TriangleSolitaireTextView(model);
    Readable input = new InputStreamReader(System.in);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);

    controller.playGame();
  }

}
