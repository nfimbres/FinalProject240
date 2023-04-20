import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SongButtonListener implements ActionListener {
    private Song song;
    private JTextField title;
    private JTextField artist;
    private JTextField rating;
    private Playlist playlist;
    private int option;
    public SongButtonListener(Playlist p, Song s,int o) {
        this.playlist = p;
        this.song = s;
        this.option = o;
    }
    public SongButtonListener(Playlist p, JTextField t, JTextField a, JTextField r,int o) {
        this.playlist = p;
        this.title = t;
        this.artist = a;
        this.rating = r;
        this.option = o;
    }
    @Override
    public void actionPerformed(ActionEvent e) { // method for when the button is clicked
        if(option==0) {
            String titleText = title.getText();
            String artistText = artist.getText();
            int ratingInt = Integer.parseInt(rating.getText());
            playlist.addSong(new Song(titleText, artistText, ratingInt));
            try {
                playlist.save();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(null, title.getText() + " has been added to " + playlist.getName());
            title.setText("");
            artist.setText("");
            rating.setText("");
        } else if(option==1) {
            Main.clearFrame(Main.editplaylistFrame);
            Main.editplaylistFrame.dispose();
            Main.mainFrame.setVisible(true);
        }
    }
}