package sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GameBoardPanel extends JPanel {
	
   //========= declaring ==========
   private static final long serialVersionUID = 1L;  // to prevent serial warning
   public static final int GRID_SIZE = 9;    // Size of the board
   public static final int SUBGRID_SIZE = 3; // Size of the sub-grid
   public static final int CELL_SIZE = 80;   // Cell width/height in pixels
   public static final int BOARD_WIDTH  = CELL_SIZE * GRID_SIZE;
   public static final int BOARD_HEIGHT = CELL_SIZE * GRID_SIZE; // Board width/height in pixels
   private JPanel GB_main_Panel, GB_cells_panel;
   private JButton[] buttons;
   private MistakesCounter mistakeCounter; // Create a new MistakeCounter object
   private CountdownClock countdownclock; // Create a new MistakeCounter object
   public Cell[][] cells = new Cell[GRID_SIZE][GRID_SIZE];//宣告九乘九的格子，以cell為單元
   private Puzzle my_puzzle = new Puzzle();//宣告九乘九的格子的邏輯版
   //==============================================
 
   
   
   //========= getters & setters ==========
   public JPanel getGB_main_Panel() {
	   return GB_main_Panel;
   }
   
   public JPanel getGB_cells_Panel() {
	   return GB_cells_panel;
   }
  
   
   public Cell currentlyFocusedCell = null;
   
   
   public GameBoardPanel getGameBoardPanel() {
	   return this;
   }
   
   public CountdownClock getCountdownClock() {
	   return countdownclock;
   }
   
   public Cell[][] getCells() {
	   return cells;
   }
  //=========================================
  
 

   public GameBoardPanel(int difficulty_reference_index_time, int difficulty_reference_index_chances) {
	
	 GB_cells_panel = new JPanel(); 
	   

	 GB_main_Panel = new JPanel(new BorderLayout());
	 mistakeCounter = new MistakesCounter(difficulty_reference_index_chances);
	 countdownclock = new CountdownClock(difficulty_reference_index_time, this);
	
	 
	 //======= GB_top ========
	 JPanel GB_top_Panel = new JPanel();
	 GB_top_Panel.setLayout(new GridLayout(1, 2));
	 GB_top_Panel.setPreferredSize(new Dimension(0, 40));
	
	 
	 GB_top_Panel.add(mistakeCounter);
	 GB_top_Panel.add(countdownclock);
	 mistakeCounter.setBackground(new Color(150,75,0));
	 countdownclock.setBackground(new Color(210, 180, 140));
	 //========================
	   
	 
	 //======= GB_cells ========
	 GB_cells_panel = new JPanel(); 
	 GB_cells_panel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE)); //創造九乘九格子
	 GB_cells_panel.setBackground(Color.ORANGE); //創造九乘九格子
	 
     for (int row = 0; row < GRID_SIZE; ++row) { //使格子有意義了，數字、顏色
         for (int col = 0; col < GRID_SIZE; ++col) {
            cells[row][col] = new Cell(row, col);
            GB_cells_panel.add(cells[row][col]); 
         }
      }
     
      CellInputListener input_listener = new CellInputListener(); //創造偵測器
      
      for (int row = 0; row < GRID_SIZE; ++row) { //為每個格子新增偵測器
         for (int col = 0; col < GRID_SIZE; ++col) {
        	 
        	 Cell cell = cells[row][col];
        	 
        	 //focus_listener
             cell.addFocusListener(new FocusListener() {
                @Override
        	    public void focusGained(FocusEvent e) {
        		  // Set the currently focused cell to the cell that just gained focus
        		  currentlyFocusedCell = cell;
        	    }
        		  @Override
                public void focusLost(FocusEvent e) {
        		  // Do nothing when a cell loses focus
            	}
            });
        	 
            //input_listener
            if (cells[row][col].isEditable()) {
               cells[row][col].addActionListener(input_listener);   
            }
         }
      }
      //==============================
      

      //======= GB_digit_keys ========
     JPanel GB_key_Panel = new JPanel();
    
 	 GB_key_Panel.setLayout(new GridLayout(1, 9));
 	 buttons = new JButton[9];
 	 
     for (int i = 0; i < buttons.length; i++) {
    	 
         buttons[i] = new JButton(i + 1 + "");
         buttons[i].setPreferredSize(new Dimension(60,60));
         buttons[i].setFont(new Font("Calibri", Font.BOLD, 24));
         buttons[i].setOpaque(true);
         buttons[i].setBackground(new Color(245, 222, 179));
         buttons[i].setForeground(Color.BLACK);

         
         GB_key_Panel.add(buttons[i]);
        
         
         final int intI = i;//不這樣做，跑不過
         
         buttons[i].addActionListener(new ActionListener() {
        	 @Override
             public void actionPerformed(ActionEvent e) {
                if (currentlyFocusedCell != null) {         
                   currentlyFocusedCell.setText((intI+1) + "");
                   currentlyFocusedCell.requestFocusInWindow(); // Set focus back to the previous cell
                }
             }
         });  
     }
     //===============================
     
     
     
      add(GB_main_Panel); // Add GB_main_Panel to the GameBoardPanel
      GB_main_Panel.add(GB_top_Panel, BorderLayout.NORTH);
      GB_main_Panel.add(GB_cells_panel, BorderLayout.CENTER);
      GB_cells_panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50)); // add some empty space to the east and west
      GB_main_Panel.add(GB_key_Panel, BorderLayout.SOUTH);
      
     // add(GB_main_Panel); // Add GB_main_Panel to the GameBoardPanel
      GB_main_Panel.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
      
     
   }

   public void newGame(int difficulty_reference_index) {
	   
	      my_puzzle.newPuzzle(difficulty_reference_index);
	      

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
            System.out.printf("You entered %d (for debugging)\n", numberIn);// For debugging

      
             //* [TODO 5] (later - after TODO 3 and 4)
             if (numberIn == sourceCell.number) {
                sourceCell.status = CellStatus.CORRECT_GUESS;
                sourceCell.paint(); 
             } else {
            	 
           	    sourceCell.status = CellStatus.WRONG_GUESS;
           	    sourceCell.paint(); 
           	    mistakeCounter.increment();
           	  
             	Timer timer = new Timer(1000, new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e) {
                      sourceCell.status = CellStatus.TO_GUESS;
                      sourceCell.paint();
                      sourceCell.setText("");
                    } 
                });
             	
                timer.setRepeats(false);  
                timer.start();
           	
             }
             

             if (isSolved()) {
           	    JOptionPane.showMessageDialog(null, "Congratulations! You have solved the puzzle.");
           	    System.exit(0); // optional - exit the application after showing the congratulatory message
             }
             
      }
   }
   
   
}