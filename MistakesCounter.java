package sudoku;
import java.awt.Font;
import java.awt.Color;

import javax.swing.*;

public class MistakesCounter extends JPanel {
    private static final long serialVersionUID = 1L; // to prevent serial warning
    
    private JLabel label;
    private int count;
    private int chances_max;
    
    public MistakesCounter(int chances) {
    	
    	chances_max = chances;
        count = 0;
        
        label = new JLabel(String.format("Mistake Count: %d/%d", count, chances_max));
        add(label);
        label.setFont(new Font("Verdana", Font.BOLD, 22));
        label.setForeground(Color.WHITE);
    }
    
    public void increment() {
        
    	if(count != chances_max) { //不要讓人家發現可以顯示錯誤次數高於上限
    		count++;
    	}
        label.setText(String.format("Mistake Count: %d/%d", count, chances_max));
        
        if (count >= chances_max) { // check if count has reached the maximum
            JOptionPane.showMessageDialog(this, "You have reached the maximum number of mistakes. You have failed."); // show notification
            System.exit(0);
        }
    }
    
    public int getCount() {
        return count;
    }

}