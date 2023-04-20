import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * this class allows for information from text fields to be stored
 */
public class TextFieldListener implements ActionListener {
    private JTextField textField; // variable for text field

    /**
     * constructor
     * @param t JTextField
     */
    public TextFieldListener(JTextField t) {
        textField = t;
    }

    /**
     * determines what happens when the button is clicked
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String playlistName = textField.getText();
        JOptionPane.showMessageDialog(null,playlistName+ " has been created.");
        Playlist playlist;
        try {
            playlist = new Playlist(playlistName,0);
            playlist.save();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Main.playlists.add(playlist);
        Main.clearFrame();
        this.textField.removeAll();
        Main.mainMenu();
        }
}
