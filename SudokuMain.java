package sudoku;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



/**
 * The main Sudoku program
 */
public class SudokuMain extends JFrame {
   private static final long serialVersionUID = 1L;  // to prevent serial warning

   // private variables
   GameBoardPanel board = new GameBoardPanel();
   JButton btnHomePage = new JButton("Back to Home");
   

   // Constructor
   public SudokuMain() {
	   
	    JPanel main_Panel = new JPanel(); 
	    main_Panel.setLayout(new BorderLayout());
	    
	    
	    
	    //======= top ========
	    JPanel GB_Panel = new JPanel();
	    main_Panel.add(GB_Panel, BorderLayout.NORTH);
	    GB_Panel.add(board);

	    //=======

	    
        //======= bottom =======
	    JPanel bottom_panel = new JPanel();
	    bottom_panel.add(btnHomePage);
	    //=======
	    
	    
	    
	    
	    main_Panel.add(bottom_panel, BorderLayout.CENTER);
	    
	    
	    ButtonListener listener = new ButtonListener();
	    btnHomePage.addActionListener(listener);

	    board.newGame();
	    
	    
        main_Panel.setBackground(Color.YELLOW);
	    add(main_Panel);
	    pack();
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("Sudoku");
	    setVisible(true);
	}

   
   private class ButtonListener implements ActionListener {
	      @Override
	      public void actionPerformed(ActionEvent e) {
	    	  SudokuWelcomePage welcomePage = new SudokuWelcomePage();
	          welcomePage.setLocationRelativeTo(null); // center window relative to screen
	          welcomePage.setVisible(true);
	          dispose();
	    	  
	      }
   }
	
   /** The entry main() entry method */
   public static void main(String[] args) {
	   
      // [TODO 1] Check "Swing program template" on how to run
      //  the constructor of "SudokuMain"
	   SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new SudokuMain();
				//SudokuWelcomePage welcomePage = new SudokuWelcomePage()
			}
		}); 
	   
   }
}