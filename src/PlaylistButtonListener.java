import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PlaylistButtonListener implements ActionListener {
    private Playlist playlist;
    private int option;
    public PlaylistButtonListener(Playlist p, int o) {
        this.playlist = p;
        this.option = o;
    }
    @Override
    public void actionPerformed(ActionEvent e) { // method for when the button is clicked
        if(option==0) {
            Main.editPlaylist(playlist);
            Main.clearFrame(Main.choosePlaylistFrame);
            Main.choosePlaylistFrame.dispose();
        } else if (option==1) {
            Main.removePlaylist(playlist);
        } else if (option==2) {
            Main.viewPlaylist(playlist);
            Main.clearFrame(Main.choosePlaylistFrame);
            Main.choosePlaylistFrame.dispose();
        }
    }
}