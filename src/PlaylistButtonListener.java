import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PlaylistButtonListener implements ActionListener {
    private Playlist playlist;
    private int option;
    public PlaylistButtonListener(Playlist p) {
        this.playlist = p;
    }
    @Override
    public void actionPerformed(ActionEvent e) { // method for when the button is clicked
        Main.editPlaylist(playlist);
        Main.choosePlaylistFrame.dispose();
        Main.mainFrame.setVisible(true);
    }
}