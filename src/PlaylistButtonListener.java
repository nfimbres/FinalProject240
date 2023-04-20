import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            Main.deletePlaylist(playlist);
        } else if (option==1) {
            Main.viewPlaylist(playlist);
        } else if (option==2) {
            Main.editPlaylistmenu(playlist);
        } else if (option==3) {
            Main.sortPlaylist(playlist);
        }
    }
}