import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ButtonListener implements ActionListener {
    private int option;
    private Playlist playlist;
    private JFrame frame;
    public ButtonListener(int option) {
        this.option = option;
    }
    public ButtonListener(JFrame frame,int option) {
        this.frame = frame;
        this.option = option;
    }
    public ButtonListener(Playlist p, JFrame frame, int option) {
        this.playlist = p;
        this.option = option;
    }
    @Override
    public void actionPerformed(ActionEvent e) { // method for when the button is clicked
        if(this.option==0) { //new playlist
            Main.newPlaylist();
            Main.mainFrame.setVisible(false);
            } else if(this.option==1) { // delete playlist
                if(Main.playlists.size()>0) {
                    Main.choosePlaylist(1);
                    Main.mainFrame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null,"There are no playlists to delete.");
            }
            } else if(this.option==2) { // view playlist
                if(Main.playlists.size()>0) {
                    Main.choosePlaylist(2);
                    Main.mainFrame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null,"There are no playlists to view.");
            }
            } else if(this.option==3) { // edit playlist
                if(Main.playlists.size()>0) {
                    Main.choosePlaylist(0);
                    Main.mainFrame.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null,"There are no playlists to edit.");
                }
            } else if(this.option==4) { // reorder playlist
                if(Main.playlists.size()>0) {
                    Main.reorderPlaylist();
                    Main.mainFrame.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null,"There are no playlists to reorder.");
                }
            } else if(this.option==5) { // exit
                try {
                    Main.exit();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if(this.option==6) { // add songs
                Main.clearFrame(Main.editplaylistFrame);
                Main.addSongs(this.playlist);
            } else if(this.option==7) { // remove songs
                Main.clearFrame(Main.editplaylistFrame);
                Main.deleteSongs(this.playlist);
            } else if(this.option==8) { // back button
                Main.clearFrame(frame);
                frame.dispose();
                Main.mainFrame.setVisible(true);
            }
    }
}
