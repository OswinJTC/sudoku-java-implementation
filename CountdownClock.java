package sudoku;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CountdownClock extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
    private JLabel label;
    private Timer timer;
    private int countdownTime;
    private JButton stop_start_Button;
    private int run_or_pause = 0;
    private MusicPlayer GameOveralMusic, GameLastSecondsMusic;
    private int initial_time = 0;
    private Color[][] to_store_color = new Color[9][9];
    private String[][] to_store_number = new String[9][9];
    


	public CountdownClock(int seconds, GameBoardPanel gameboard) {
    	
    	initial_time = seconds;
    	
    	
    	GameOveralMusic = new MusicPlayer("/Users/OswinChen/Desktop/Twilight_Zone.wav");
        GameLastSecondsMusic = new MusicPlayer("/Users/OswinChen/Desktop/nervous.wav");
        
        

        countdownTime = seconds;
        label = new JLabel(String.format("%02d:%02d", countdownTime / 60, countdownTime % 60));
        add(label);
        label.setFont(new Font("Consolas", Font.BOLD, 20));
 
        stop_start_Button = new JButton("Stop");
        
        stop_start_Button.setFont(new Font("Times New Roman", Font.BOLD, 19));
        stop_start_Button.setOpaque(true);
        stop_start_Button.setForeground(Color.BLACK);
        stop_start_Button.setPreferredSize(new Dimension(80, 30));
        stop_start_Button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        
        stop_start_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	run_or_pause ++;
            	
            	
            	if((run_or_pause % 2) == 1) {
            		timer.stop();
                    stop_start_Button.setText("Start");
                    
                    
            	}else {
            		timer.start();
                    stop_start_Button.setText("Stop");
                    
            	}
            	
            	 if (!timer.isRunning()) {
            		 
            		 for (int row = 0; row < 9; ++row) { //使格子有意義了，數字、顏色
            	         for (int col = 0; col < 9; ++col) {
            	        	 
            	        	 if(gameboard.cells[row][col].getBackground() != new Color(216, 0, 0)&& gameboard.cells[row][col].getBackground() != new Color(0, 216, 0)) {
            	        		    to_store_color[row][col] = gameboard.cells[row][col].getBackground();
            	        		} else {
            	        		    to_store_color[row][col] = Color.YELLOW;
            	        		}

            	        		to_store_number[row][col] = gameboard.cells[row][col].getText();

            	        		gameboard.cells[row][col].setBackground(Color.WHITE);
            	        		gameboard.cells[row][col].setText(""); 
            	        		gameboard.cells[row][col].setEditable(false);  

            	        		// Change the color of the cell to light gray
            	        		
            	        		gameboard.getGB_cells_Panel().setBackground(new Color(176, 224, 230));

            	         }
            	      }
            		
                 } else {
                	 for (int row = 0; row < 9; ++row) { //使格子有意義了，數字、顏色
            	         for (int col = 0; col < 9; ++col) {
            	        	 
            	        	 gameboard.cells[row][col].setBackground(to_store_color[row][col]);
            	        	 gameboard.cells[row][col].setText(to_store_number[row][col]); 
            	        	 gameboard.getGB_cells_Panel().setBackground(Color.ORANGE);
            	        	 gameboard.cells[row][col].setEditable(true);    

            	         }
            	      }
                 }
            }
        });
        add(stop_start_Button);
        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	PlayGameOveralMusic();
                countdownTime--;
                if (countdownTime == -1) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Time is up! You have failed.", "Failure", JOptionPane.ERROR_MESSAGE);   
                    System.exit(0);
                } else {
                    label.setText(String.format("%02d:%02d", countdownTime / 60, countdownTime % 60));
                }
                
                ChangeToNervous();
                StopNervous();
                
            }
        });
        
        
        timer.start();
        
    }
    
	
    public int getCountdownTime() {
		return countdownTime;
	}

	public void setCountdownTime(int countdownTime) {
		this.countdownTime = countdownTime;
	}

	public MusicPlayer getGameOveralMusic() {
 		return GameOveralMusic;
 	}
    
     public MusicPlayer getGameLastSecondsMusic() {
 		return GameLastSecondsMusic;
 	}
    
    public void PlayGameOveralMusic() {
 		
  		if(countdownTime == initial_time) {
  			
     		GameOveralMusic.GameOveralMusicplay();
     		 
          }
     }

    public void ChangeToNervous() {
		
 		if(countdownTime == 30) {
 		    GameOveralMusic.stop();
    		GameLastSecondsMusic.GameLastSecondsMusicplay();
         }
     }
    
    public void StopNervous() {
	
 		if(countdownTime == 0) {  
    		GameLastSecondsMusic.stop();
         }
     }

   
}
