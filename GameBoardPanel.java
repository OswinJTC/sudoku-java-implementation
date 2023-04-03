package sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameBoardPanel extends JPanel {
   private static final long serialVersionUID = 1L;  // to prevent serial warning

   public static final int GRID_SIZE = 9;    // Size of the board
   public static final int SUBGRID_SIZE = 3; // Size of the sub-grid
   public static final int CELL_SIZE = 60;   // Cell width/height in pixels
   public static final int BOARD_WIDTH  = CELL_SIZE * GRID_SIZE;
   public static final int BOARD_HEIGHT = CELL_SIZE * GRID_SIZE; // Board width/height in pixels
   
   private Cell[][] cells = new Cell[GRID_SIZE][GRID_SIZE];//宣告九乘九的格子，以cell為單元
   private Puzzle my_puzzle = new Puzzle();//宣告九乘九的格子的邏輯版
   

   public GameBoardPanel() {
	   
      super.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));  //創造九乘九格子

      for (int row = 0; row < GRID_SIZE; ++row) { //使格子有意義了，數字、顏色
         for (int col = 0; col < GRID_SIZE; ++col) {
            cells[row][col] = new Cell(row, col);
            super.add(cells[row][col]); 
         }
      }
      
      // [TODO 3] 
      CellInputListener listener = new CellInputListener(); //創造偵測器
      
      // [TODO 4]
      for (int row = 0; row < GRID_SIZE; ++row) { //為每個格子新增偵測器
         for (int col = 0; col < GRID_SIZE; ++col) {
            if (cells[row][col].isEditable()) {
               cells[row][col].addActionListener(listener);   
            }
         }
      }
      super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
   }
   
   

   public void newGame() {
	   
      my_puzzle.newPuzzle(2);//這個2沒用啊

      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            cells[row][col].newGame(my_puzzle.numbers[row][col], my_puzzle.isGiven[row][col]);
         }
      }
   }
   
   
   public boolean isSolved() {
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
               return false;
            }
         }
      }
      return true;
   }

   
   private class CellInputListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         // Get a reference of the JTextField that triggers this action event
         Cell sourceCell = (Cell)(e.getSource());//讓我們可以得到真正cell裡的所有資訊
		 
         
         int numberIn = Integer.parseInt(sourceCell.getText());// Retrieve the int entered
         System.out.println("You entered " + numberIn);// For debugging

   
          //* [TODO 5] (later - after TODO 3 and 4)
          if (numberIn == sourceCell.number) {
             sourceCell.status = CellStatus.CORRECT_GUESS;
             sourceCell.paint(); 
          } else {
        	  sourceCell.status = CellStatus.WRONG_GUESS;
        	  sourceCell.paint(); 
              
          }


         /*
          * [TODO 6] (later)
          * Check if the player has solved the puzzle after this move,
          *   by calling isSolved(). Put up a congratulation JOptionPane, if so.
          */
          if (isSolved()) {
        	  JOptionPane.showMessageDialog(null, "Congratulations! You have solved the puzzle.");
        	  //System.exit(0); // optional - exit the application after showing the congratulatory message
        }
          
          
          
          
      }
   }
}