import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private JTextField textField; // variable for text field
    public ButtonListener(JTextField t) { //constructor
        textField = t;
    }
    @Override
    public void actionPerformed(ActionEvent e) { // method for when the button is clicked

    }
}
