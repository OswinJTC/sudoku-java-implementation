package sudoku;
import javax.swing.*;

public class MistakesCounter extends JPanel {
    private static final long serialVersionUID = 1L; // to prevent serial warning
    
    private JLabel label;
    private int count;
    
    public MistakesCounter() {
    	
        count = 0;
        
        label = new JLabel(String.format("Mistake Count: %d/3", count));
        add(label);
    }
    
    public void increment() {
        
    	if(count != 3) { //不要讓人家發現可以顯示錯誤次數高於上限
    		count++;
    	}
        label.setText(String.format("Mistake Count: %d/3", count));
        
        if (count >= 3) { // check if count has reached the maximum
            JOptionPane.showMessageDialog(this, "You have reached the maximum number of mistakes. You have failed."); // show notification
            System.exit(0);
        }
    }
    
    public int getCount() {
        return count;
    }
}