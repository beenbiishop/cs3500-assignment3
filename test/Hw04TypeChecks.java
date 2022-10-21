import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

/**
 * Do not modify this file. This file should compile correctly with your code!
 */
public class Hw04TypeChecks {

  /**
   * Given main method to test the compilation of code.
   *
   * @param args the arguments taken in by the main method
   */
  public static void main(String[] args) {
    Readable rd = null;
    Appendable ap = null;
    helperMarble(new EnglishSolitaireModel(), rd, ap);

    helperTriangle(new TriangleSolitaireModel(3, 3), rd, ap);
  }

  /**
   * Helper method to test the compilation of code.
   *
   * @param model the model to be played
   * @param rd    the input to be read
   * @param ap    the output to be written
   */
  private static void helperMarble(cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel model,
      Readable rd, Appendable ap) {
    new cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl(model,
        new cs3500.marblesolitaire.view.MarbleSolitaireTextView(model, ap), rd);
  }

  /**
   * Helper method to test the compilation of code.
   *
   * @param model the model to be played
   * @param rd    the input to be read
   * @param ap    the output to be written
   */
  private static void helperTriangle(cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel model,
      Readable rd, Appendable ap) {
    new cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl(model,
        new cs3500.marblesolitaire.view.MarbleSolitaireTextView(model, ap), rd);
  }

}
