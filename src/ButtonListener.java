import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ButtonListener implements ActionListener {
    private int option;
    private Playlist playlist;
    public ButtonListener(int option) {
        this.option = option;
    }
    public ButtonListener(Playlist p, int option) {
        this.playlist = p;
        this.option = option;
    }
    @Override
    public void actionPerformed(ActionEvent e) { // method for when the button is clicked
        if(this.option==0) {
            Main.newPlaylist();
            Main.mainFrame.setVisible(false);
        } else if(this.option==1) {
            if(Main.playlists.size()>0) {
                Main.choosePlaylist();
                Main.mainFrame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null,"There are no playlists to edit.");
            }
        } else if(this.option==2) {
            if(Main.playlists.size()>0) {
                Main.shufflePlaylist();
                Main.mainFrame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null,"There are no playlists to shuffle.");
            }
        } else if(this.option==3) {
            try {
                Main.exit();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if(this.option==4) {
            Main.editplaylistFrame.getContentPane().removeAll();
            Main.addSongs(this.playlist);
        } else if(this.option==5) {
            Main.editplaylistFrame.getContentPane().removeAll();
            Main.removeSongs();
        } else if(this.option==6) {
            Main.clearFrame(Main.editplaylistFrame);

        }
    }
}
