package sudoku;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
/**
 * The Cell class model the cells of the Sudoku puzzle, by customizing (subclass)
 * the javax.swing.JTextField to include row/column, puzzle number and status.
 */
public class Cell extends JTextField {
   private static final long serialVersionUID = 1L;  // to prevent serial warning

   public static final Color BG_GIVEN = new Color(240, 240, 240); // RGB
   public static final Color FG_GIVEN = Color.BLACK;
   public static final Color FG_NOT_GIVEN = Color.GRAY;
   public static final Color BG_TO_GUESS  = Color.YELLOW;
   public static final Color BG_CORRECT_GUESS = new Color(0, 216, 0);
   public static final Color BG_WRONG_GUESS   = new Color(216, 0, 0);
   public static final Font FONT_NUMBERS = new Font("OCR A Extended", Font.PLAIN, 28);


   int row, col;
   int number;
   CellStatus status;

   public Cell(int row, int col) {
      super();   // JTextField
      this.row = row;
      this.col = col;
      // Inherited from JTextField: Beautify all the cells once for all
      super.setHorizontalAlignment(JTextField.CENTER);
      super.setFont(FONT_NUMBERS);
   }

  
   
   public void newGame(int number, boolean isGiven) {
      this.number = number;
      if (isGiven) {
    	   status = CellStatus.GIVEN;
    	} else {
    	   status = CellStatus.TO_GUESS;
    	}
      paint();    // paint itself
   }

  
   
   
   public void paint() {
      if (status == CellStatus.GIVEN) {
         // Inherited from JTextField: Set display properties
         super.setText(number + "");
         super.setEditable(false);
         super.setBackground(BG_GIVEN);
         super.setForeground(FG_GIVEN);
      } else if (status == CellStatus.TO_GUESS) {
         // Inherited from JTextField: Set display properties
         super.setText("");
         super.setEditable(true);
         super.setBackground(BG_TO_GUESS);
         super.setForeground(FG_NOT_GIVEN);
      } else if (status == CellStatus.CORRECT_GUESS) {  // from TO_GUESS
         super.setBackground(BG_CORRECT_GUESS);
      } else if (status == CellStatus.WRONG_GUESS) {    // from TO_GUESS
         super.setBackground(BG_WRONG_GUESS);
      }
   }
}