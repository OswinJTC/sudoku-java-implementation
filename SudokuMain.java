package sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SudokuMain extends JFrame {
	
    private static final long serialVersionUID = 1L;
    private WelcomePage welcomePage;
    
    public SudokuMain() {
    	
        welcomePage = new WelcomePage();
        
        welcomePage.addButtonsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                welcomePage.setVisible(false); // hide the welcome page
                remove(welcomePage);
                
                String get_command = e.getActionCommand();
    			if(get_command.equals("Easy")) {
    				System.out.println("Easy button clicked(for debugging)");
    				GamePageMain gamePage = new GamePageMain(20, 360, 8); //easy game => 20 empty cells
    				add(gamePage);
    				
    				
    			}else if(get_command.equals("Medium")) {
    				System.out.println("Medium button clicked(for debugging)");
    				GamePageMain gamePage = new GamePageMain(50, 330, 4); //medium game => 50 empty cells
    				add(gamePage);
    				
    				
    			}else if(get_command.equals("Hard")){
    				System.out.println("Hard button clicked(for debugging)");
    				GamePageMain gamePage = new GamePageMain(75, 300, 2); //hard game => 75 empty cells
    				add(gamePage);
    				
    				gamePage.addHomeButtonsListener(new ActionListener() {
    		            @Override
    		            public void actionPerformed(ActionEvent e) {
    		            	
    		                gamePage.setVisible(false);
    		                remove(gamePage);   
    		                add(welcomePage);
    		                welcomePage.setVisible(true);
    		                
    		                pack();
    		                //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		                setTitle("Sudoku");       
    		                revalidate();
    		                repaint();
    		            }
    		        });
    			}

               
                pack();
                revalidate();
                repaint();
            }
        });

        
        
        add(welcomePage);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sudoku");
        setVisible(true);
    }
    
    
    public class WelcomePage extends JFrame {
    	
    	private JButton easyButton;
        private JButton mediumButton;
        private JButton hardButton;
        
        public WelcomePage() {
        	
        	setTitle("Sudoku Warriors");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 400);
            setLocationRelativeTo(null);
            getContentPane().setBackground(new Color(69, 142, 227)); // set the background color of the frame
            
            // Set up the components
            JLabel nameLabel = new JLabel("Sudoku Warriors");
            nameLabel.setFont(new Font("Arial", Font.BOLD, 36));
            nameLabel.setHorizontalAlignment(JLabel.CENTER);
            nameLabel.setForeground(Color.BLACK);
            
            JLabel quoteLabel = new JLabel("Try the Numbers Game, Minus the Math");
            quoteLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            quoteLabel.setHorizontalAlignment(JLabel.CENTER);
            quoteLabel.setForeground(Color.BLACK);
            
            easyButton = new JButton("Easy");
            easyButton.setBackground(new Color(51, 51, 51)); // dark gray
            easyButton.setForeground(Color.BLACK);
            easyButton.setFont(new Font("Arial", Font.BOLD, 18));
            easyButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            
            mediumButton = new JButton("Medium");
            mediumButton.setBackground(new Color(51, 51, 51)); // dark gray
            mediumButton.setForeground(Color.BLACK);
            mediumButton.setFont(new Font("Arial", Font.BOLD, 18));
            mediumButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            
            hardButton = new JButton("Hard");
            hardButton.setBackground(new Color(51, 51, 51)); // dark gray
            hardButton.setForeground(Color.BLACK);
            hardButton.setFont(new Font("Arial", Font.BOLD, 18));
            hardButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            
            // Set up the background image
            ImageIcon backgroundImage = new ImageIcon("sudoku-background.jpg");
            JLabel backgroundLabel = new JLabel(backgroundImage);
            backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
            
            // Add the components to the frame
            JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
            panel.setOpaque(false); // make the panel transparent
            panel.add(nameLabel);
            panel.add(quoteLabel);
            panel.add(easyButton);
            panel.add(mediumButton);
            panel.add(hardButton);
            panel.setBounds(0, 0, getWidth(), getHeight()); // position the panel
            
            // Add the background image and panel to the frame
            add(backgroundLabel);
            add(panel);
            
            // Show the frame
            setLayout(null); // use absolute positioning
            setVisible(true);
        	
            
        
        }
        
        public void addButtonsListener(ActionListener listener) {
            easyButton.addActionListener(listener);
            mediumButton.addActionListener(listener);
            hardButton.addActionListener(listener);
        }

      
        
        
       
        
    }
    
    
    public class GamePageMain extends JFrame {
        private static final long serialVersionUID = 1L;

        
        private GameBoardPanel board;
        private JButton btnHomePage = new JButton("Back to Home");
     

        // Constructor
        public GamePageMain(int difficulty_reference_index, int difficulty_reference_index_time,  int difficulty_reference_index_chances) {
          
        	board = new GameBoardPanel(difficulty_reference_index_time, difficulty_reference_index_chances);
        	
            JPanel main_Panel = new JPanel();
            main_Panel.setLayout(new BorderLayout());

            //======= top ========
            JPanel GB_Panel = new JPanel();
            main_Panel.add(GB_Panel, BorderLayout.NORTH);
            GB_Panel.add(board);
            
            

            //======= bottom =======
            JPanel bottom_panel = new JPanel();
            bottom_panel.add(btnHomePage);

            main_Panel.add(bottom_panel, BorderLayout.CENTER);

            //ButtonListener listener = new ButtonListener();
            //btnHomePage.addActionListener(listener);

            board.newGame(difficulty_reference_index);
          

            main_Panel.setBackground(Color.YELLOW);
            add(main_Panel);
            pack();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Sudoku");
            setVisible(true);
       }
        
        public void addHomeButtonsListener(ActionListener listener) {
        	
        	btnHomePage.addActionListener(listener);
            
        }

  
    
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SudokuMain();
            }
        });
        
        
    }
}

    

    
