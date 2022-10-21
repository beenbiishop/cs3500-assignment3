package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
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
    // Set the input stream
    Readable input = new InputStreamReader(System.in);

    // Initialize the parameter variables
    int size = 0;
    int sRow = 0;
    int sCol = 0;
    boolean userSize = false;
    boolean userSlot = false;

    // Check the arguments for the size and slot
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-size")) {
        if (i + 1 < args.length) {
          try {
            size = Integer.parseInt(args[i + 1]);
            userSize = true;
          } catch (NumberFormatException e) {
            System.out.println("Invalid size. Size must be an integer.");
            return;
          }
        } else {
          System.out.println("Invalid size. Size must be an integer.");
          return;
        }
      }
      if (args[i].equals("-hole")) {
        if (i + 2 < args.length) {
          try {
            sRow = Integer.parseInt(args[i + 1]);
            sCol = Integer.parseInt(args[i + 2]);
            userSlot = true;
          } catch (NumberFormatException e) {
            System.out.println("Invalid hole. Hole must be two integers.");
            return;
          }
        } else {
          System.out.println("Invalid hole. Hole must be two integers.");
          return;
        }
      }
    }

    // Initialize the model and view variables
    MarbleSolitaireModel model;
    MarbleSolitaireView view;

    // Check the arguments for the game type and initialize the model and view
    switch (args[0]) {
      case "english":
        if (!userSize && userSlot) {
          model = new EnglishSolitaireModel(sRow, sCol);
        } else if (userSize && !userSlot) {
          model = new EnglishSolitaireModel(size);
        } else if (userSize && userSlot) {
          model = new EnglishSolitaireModel(size, sRow, sCol);
        } else {
          model = new EnglishSolitaireModel();
        }
        view = new MarbleSolitaireTextView(model);
        break;
      case "european":
        if (!userSize && userSlot) {
          model = new EuropeanSolitaireModel(sRow, sCol);
        } else if (userSize && !userSlot) {
          model = new EuropeanSolitaireModel(size);
        } else if (userSize && userSlot) {
          model = new EuropeanSolitaireModel(size, sRow, sCol);
        } else {
          model = new EuropeanSolitaireModel();
        }
        view = new MarbleSolitaireTextView(model);
        break;
      case "triangular":
        if (!userSize && userSlot) {
          model = new TriangleSolitaireModel(sRow, sCol);
        } else if (userSize && !userSlot) {
          model = new TriangleSolitaireModel(size);
        } else if (userSize && userSlot) {
          model = new TriangleSolitaireModel(size, sRow, sCol);
        } else {
          model = new TriangleSolitaireModel();
        }
        view = new TriangleSolitaireTextView(model);
        break;
      default:
        System.out.println("Invalid game type");
        return;
    }

    // Initialize the controller and play the game
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();
  }
}