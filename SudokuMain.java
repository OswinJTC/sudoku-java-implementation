package sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SudokuMain extends JFrame {
	
    private static final long serialVersionUID = 1L;
    private WelcomePage welcomePage;
    
    public SudokuMain() {
    	
        welcomePage = new WelcomePage();
        
        welcomePage.addEasyButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Easy button clicked");
                welcomePage.setVisible(false); // hide the welcome page
                remove(welcomePage);
                GamePageMain gamePage = new GamePageMain();
                add(gamePage);
                pack();
                revalidate();
                repaint();
            }
        });

        welcomePage.addMediumButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Medium button clicked");
                welcomePage.setVisible(false); // hide the welcome page
                remove(welcomePage);
                GamePageMain gamePage = new GamePageMain();
                add(gamePage);
                pack();
                revalidate();
                repaint();
            }
        });

        welcomePage.addHardButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hard button clicked");
                welcomePage.setVisible(false); // hide the welcome page
                remove(welcomePage);
                GamePageMain gamePage = new GamePageMain();
                add(gamePage);
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
            // Set up the frame
            setTitle("Sudoku Warriors");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 400);
            setLocationRelativeTo(null);
            
            // Set up the components
            JLabel nameLabel = new JLabel("Sudoku Warriors");
            nameLabel.setFont(new Font("Arial", Font.BOLD, 36));
            nameLabel.setHorizontalAlignment(JLabel.CENTER);
            nameLabel.setForeground(Color.WHITE);
            
            JLabel quoteLabel = new JLabel("Try the Numbers Game, Minus the Math");
            quoteLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            quoteLabel.setHorizontalAlignment(JLabel.CENTER);
            quoteLabel.setForeground(Color.WHITE);
            
            easyButton = new JButton("Easy");
            easyButton.setBackground(new Color(69, 142, 227)); // blue
            easyButton.setForeground(Color.WHITE);
            easyButton.setFont(new Font("Arial", Font.BOLD, 18));
            easyButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            
            mediumButton = new JButton("Medium");
            mediumButton.setBackground(new Color(248, 148, 6)); // orange
            mediumButton.setForeground(Color.WHITE);
            mediumButton.setFont(new Font("Arial", Font.BOLD, 18));
            mediumButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            
            hardButton = new JButton("Hard");
            hardButton.setBackground(new Color(255, 62, 62)); // red
            hardButton.setForeground(Color.WHITE);
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
            
            easyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // handle easy button click
                    System.out.println("Easy button clicked");
                }
            });

            mediumButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // handle medium button click
                    System.out.println("Medium button clicked");
                }
            });

            hardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // handle hard button click
                    System.out.println("Hard button clicked");
                }
            });
        }
        
        public void addEasyButtonListener(ActionListener listener) {
            easyButton.addActionListener(listener);
        }

        public void addMediumButtonListener(ActionListener listener) {
            mediumButton.addActionListener(listener);
        }

        public void addHardButtonListener(ActionListener listener) {
            hardButton.addActionListener(listener);
        }
        
       
        
    }
    
    public class GamePageMain extends JFrame {
        private static final long serialVersionUID = 1L;

        
        private GameBoardPanel board = new GameBoardPanel();
        private JButton btnHomePage = new JButton("Back to Home");
     

        // Constructor
        public GamePageMain() {
          

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

            board.newGame();

            main_Panel.setBackground(Color.YELLOW);
            add(main_Panel);
            pack();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Sudoku");
            setVisible(true);
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

    

    
