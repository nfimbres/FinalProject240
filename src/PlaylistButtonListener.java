import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this class allows playlist buttons to be clicked
 */
public class PlaylistButtonListener implements ActionListener {
    private Playlist playlist;
    private int option;

    /**
     * constructor
     * @param p the playlist
     * @param o what button the user clicked
     */
    public PlaylistButtonListener(Playlist p, int o) {
        this.playlist = p;
        this.option = o;
    }

    /**
     * what happens when a button is being clicked
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
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