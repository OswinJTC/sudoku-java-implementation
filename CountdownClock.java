package sudoku;
import javax.swing.*;
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
    
   
    

	public CountdownClock(int seconds) {
    	
    	initial_time = seconds;
    	
    	GameOveralMusic = new MusicPlayer("/Users/OswinChen/Desktop/Twilight_Zone.wav");
        GameLastSecondsMusic = new MusicPlayer("/Users/OswinChen/Desktop/nervous.wav");
        
        

        countdownTime = seconds;
        label = new JLabel(String.format("%02d:%02d", countdownTime / 60, countdownTime % 60));
        add(label);
        
        
        
        stop_start_Button = new JButton("Stop");
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
                    
                    /*
                     *  dispose(); // dispose the GamePageMain instance (this)
                SudokuMain.this.pack();
                SudokuMain.this.revalidate();
                SudokuMain.this.repaint();
                
                //WelcomePage welcomePage = sudokuMain.getWelcomePage();
                // do something with the welcomePage instance
                welcomePage.setVisible(true);
                HomeMusic.HomeMusicplay();
                board.getCountdownClock().getGameOveralMusic().stop();
                board.getCountdownClock().getGameLastSecondsMusic().stop();
                
                board.getCountdownClock().setCountdownTime(-2);// try to delete everything after returning to the welcome page
                     */
                    
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
