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
    Readable input = new InputStreamReader(System.in);
    int size = 0;
    int sRow = 0;
    int sCol = 0;
    boolean userSize = false;
    boolean userSPosn = false;
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
            userSPosn = true;
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
    MarbleSolitaireModel model;
    MarbleSolitaireView view;
    switch (args[0]) {
      case "english":
        if (!userSize) {
          size = 3;
        }
        if (!userSPosn) {
          sRow = 3;
          sCol = 3;
        }
        model = new EnglishSolitaireModel(size, sRow, sCol);
        view = new MarbleSolitaireTextView(model);
        break;
      case "european":
        if (!userSize) {
          size = 3;
        }
        if (!userSPosn) {
          sRow = 3;
          sCol = 3;
        }
        model = new EuropeanSolitaireModel(size, sRow, sCol);
        view = new MarbleSolitaireTextView(model);
        break;
      case "triangular":
        if (!userSize) {
          size = 5;
        }
        if (!userSPosn) {
          sRow = 0;
          sCol = 0;
        }
        model = new TriangleSolitaireModel(size, sRow, sCol);
        view = new TriangleSolitaireTextView(model);
        break;
      default:
        System.out.println("Invalid game type");
        return;
    }
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();
  }
}