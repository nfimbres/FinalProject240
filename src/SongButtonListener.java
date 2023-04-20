import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * this class allows song buttons to be clicked
 */
public class SongButtonListener implements ActionListener {
    private Song song;
    private JTextField title;
    private JTextField artist;
    private JTextField rating;
    private Playlist playlist;
    private int option;

    /**
     * constructor
     * @param p the playlist the song is in
     * @param s the song
     * @param o which button is clicked
     */
    public SongButtonListener(Playlist p, Song s,int o) {
        this.playlist = p;
        this.song = s;
        this.option = o;
    }

    /**
     * constructor
     * @param p the playlist the song is in
     * @param t the title of the song
     * @param a the artist of the song
     * @param r the user's rating of the song
     * @param o the button clicked
     */
    public SongButtonListener(Playlist p, JTextField t, JTextField a, JTextField r,int o) {
        this.playlist = p;
        this.title = t;
        this.artist = a;
        this.rating = r;
        this.option = o;
    }

    /**
     * what happens whe button is clicked
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(option==0) {
            String titleText = title.getText();
            String artistText = artist.getText();
            int ratingInt = 0;
            try {
                ratingInt = Integer.parseInt(rating.getText());
                if(ratingInt<1 || ratingInt>5) {
                    throw new NumberFormatException();
                }
            } catch(NumberFormatException n) {
                JOptionPane.showMessageDialog(null,"Please enter an integer between 1 and 5.");
                return;
            }
            playlist.addSong(new Song(titleText, artistText, ratingInt));
            try {
                playlist.save();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(null, title.getText() + " has been added to " + playlist.getName() + ".");
            title.setText("");
            artist.setText("");
            rating.setText("");
        } else if(option==1) {
            try {
                Main.removeSong(playlist,song);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}