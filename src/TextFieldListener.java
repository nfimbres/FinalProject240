import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TextFieldListener implements ActionListener {
    private JTextField textField; // variable for text field
    public TextFieldListener(JTextField t) { //constructor
        textField = t;
    }
    @Override
    public void actionPerformed(ActionEvent e) { // method for when the button is clicked
        String playlistName = textField.getText();
        JOptionPane.showMessageDialog(null,playlistName+ " has been created");
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
