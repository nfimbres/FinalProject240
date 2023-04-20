import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaylistButtonListener implements ActionListener {
    private Playlist playlist;
    private int option;
    public PlaylistButtonListener(Playlist p) {
        this.playlist = p;
    }
    @Override
    public void actionPerformed(ActionEvent e) { // method for when the button is clicked
        Main.editPlaylist(playlist);
        Main.clearFrame(Main.choosePlaylistFrame);
        Main.choosePlaylistFrame.dispose();
    }
}