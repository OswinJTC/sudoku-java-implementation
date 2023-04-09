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
    
    
    public CountdownClock(int seconds) {
    	

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
                countdownTime--;
                if (countdownTime < 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Time is up! You have failed.", "Failure", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                } else {
                    label.setText(String.format("%02d:%02d", countdownTime / 60, countdownTime % 60));
                }
            }
        });
        timer.start();
    }
    
   
}
