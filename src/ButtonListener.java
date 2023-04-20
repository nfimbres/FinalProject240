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
    public ButtonListener(Playlist playlist,int option) {
        this.playlist = playlist;
        this.option = option;
    }
    public void selectPlaylist(String action) {
        if(Main.playlists.size()>0) {
            Main.clearFrame();
            Main.playlistMenu(option-1);
        } else {
            JOptionPane.showMessageDialog(null,"There are no playlists to "+action+".");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) { // method for when the button is clicked
        if (option == 0) { // create playlist
            Main.clearFrame();
            Main.createPlaylist();
        } else if (option == 1) { // delete playlist
            selectPlaylist("delete");
        } else if (option == 2) { // view playlist
            selectPlaylist("view");
        } else if (option == 3) { // edit playlist
            selectPlaylist("edit");
        } else if (this.option == 4) { // sort playlist
            selectPlaylist("sort");
        } else if (this.option == 5) { // back button
            Main.clearFrame();
            Main.panel.remove(Main.playlistName);
            Main.panel.revalidate();
            Main.panel.repaint();
            Main.mainMenu();
        } else if (this.option == 6) { // back button
            Main.clearFrame();
            Main.panel.remove(Main.playlistName);
            Main.panel.revalidate();
            Main.panel.repaint();
            Main.playlistMenu(2);
        } else if (this.option == 7) { // back button
            Main.clearFrame();
            Main.panel.remove(Main.playlistName);
            Main.panel.revalidate();
            Main.panel.repaint();
            Main.playlistMenu(3);
        } else if (this.option > 7 && this.option < 12) { // sort button
            playlist.sort(option);
            try {
                playlist.save();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            String message = "";
            if(playlist.getSongs().size()>0) {
                int i = 1;
                for (Song s : playlist.getSongs()) {
                    message = message + i + ". " + s.songDetails()+"\n";
                    i++;
                }
                JOptionPane.showMessageDialog(null,playlist.getName()+"\n"+message);
            } else {
                JOptionPane.showMessageDialog(null,"This playlist is empty.");
            }
        } else if (this.option == -1) { // exit
            try {
                Main.exit();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
