import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

/**
 * this class displays the all the GUI elements and runs the program
 */
public class Main {
    public static ArrayList<Playlist> playlists = new ArrayList<>();
    public static JFrame frame = new JFrame();
    public static JPanel panel = new JPanel();
    public static Font defaultFont = new Font(Font.MONOSPACED, Font.PLAIN,  16);
    public static Font  titleFont  = new Font(Font.MONOSPACED, Font.ITALIC,  24);
    public static JTextField playlistName = new JTextField();

    public static void main(String[] args) throws IOException {
        //download playlists from directory
        getPlaylists();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(100, 10, 100, 10)));
        frame.add(panel);
        frame.setSize(750,500);
        frame.setLocationRelativeTo(null);
        mainMenu();
    }

    /**
     * displays the main menu using GUI
     */
    public static void mainMenu(){
        setFrame("Playlist Editor");
        addButton("Create Playlist",0);
        addButton("Delete Playlist",1);
        addButton("View Playlist",2);
        addButton("Edit Playlist",3);
        addButton("Sort Playlist",4);
        addButton("Exit",-1);
        displayFrame();
    }

    /**
     * creates the window for when the user clicks the "create playlist" button
     */
    public static void createPlaylist(){
        panel.setFont(defaultFont);
        setFrame("Create a Playlist");
        playlistName.setHorizontalAlignment(JTextField.CENTER);
        playlistName.setMaximumSize(new Dimension(300,35));
        playlistName.setFont(defaultFont);
        panel.add(playlistName);
        addTextButton("Create");
        backButton(0);
    }

    /**
     * allows the user to choose if they want to delete, view, edit, or sort a playlist
     * @param option represents which button the user clicks
     */
    public static void playlistMenu(int option){
        if(option==0) {
            setFrame("Select a playlist to delete.");
        } else if(option==1) {
            setFrame("Select a playlist to view.");
        } else if(option==2) {
            setFrame("Select a playlist to edit.");
        } else if(option==3) {
            setFrame("Select a playlist to sort.");
        }
        for (Playlist p : playlists) {
            addPlaylistButton(p.getName(),p,option);
        }
        backButton(0);
        displayFrame();
    }

    /**
     * allows the user to delete a playlist
     * @param playlist the playlist the user chooses to delete
     */
    public static void deletePlaylist(Playlist playlist) {
        playlists.remove(playlist);
        File fileToDelete = new File(playlist.getFileName());
        fileToDelete.delete();
        JOptionPane.showMessageDialog(null,playlist.getName()+" has been deleted.");
        clearFrame();
        playlistMenu(0);
    }

    /**
     * displays all the songs in a playlist
     * @param playlist the playlist to be displayed
     */
    public static void viewPlaylist(Playlist playlist) {
        String message = "\n\nSongs:\n";
        if(playlist.getSongs().size()>0) {
            int i = 1;
            for (Song s : playlist.getSongs()) {
                message = message + i + ". " + s.songDetails()+"\n";
                i++;
            }
            JOptionPane.showMessageDialog(null,"Playlist: "+playlist.getName()+message);
        } else {
            JOptionPane.showMessageDialog(null,"This playlist is empty.");
        }
        clearFrame();
        playlistMenu(1);
    }

    /**
     * displays the options for when a user wants to edit a playlist and allows them to edit it
     * @param playlist the playlist to be edited
     */
    public static void editPlaylistmenu(Playlist playlist){
        Object[] options = {"Add Songs", "Remove Songs", "Back"};
        int result = JOptionPane.showOptionDialog(null,"Select an option below.",playlist.getName(),JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, options, null);
        if(result==JOptionPane.YES_OPTION) {
            Main.clearFrame();
            addSongs(playlist);
        } else if (result==JOptionPane.NO_OPTION) {
            if (playlist.getSongs().size() > 0) {
                clearFrame();
                removeSongs(playlist);
            } else {
                JOptionPane.showMessageDialog(null, "This playlist is empty.");
            }
        }
    }

    /**
     * creates the GUI for when a user wants to add songs to a playlist and allows them to add songs
     * @param playlist the playlist the user is adding songs to
     */
    public static void addSongs(Playlist playlist) {
        setFrame("Enter new song information below.");
        addLabel("Title");
        JTextField title = new JTextField();
        title.setMaximumSize(new Dimension(300,25));
        title.setHorizontalAlignment(JTextField.CENTER);
        panel.add(title, BorderLayout.CENTER);
        addLabel("Artist");
        JTextField artist = new JTextField();
        artist.setMaximumSize(new Dimension(300,25));
        artist.setHorizontalAlignment(JTextField.CENTER);
        panel.add(artist, BorderLayout.CENTER);
        addLabel("Integer Rating (1-5)");
        JTextField rating = new JTextField();
        rating.setMaximumSize(new Dimension(300,25));
        rating.setHorizontalAlignment(JTextField.CENTER);
        panel.add(rating, BorderLayout.CENTER);

        addSongInfoButton("Add",playlist,title,artist,rating);
        backButton(1);

        displayFrame();
    }

    /**
     * creates GUI for when a user wants to delete songs from a playlist and allows them to delete songs
     * @param playlist the playlist the user wants to remove songs from
     */
    public static void removeSongs(Playlist playlist) {
        setFrame("Select a song to delete.");
        for (Song s : playlist.getSongs()) {
            addSongButton(s.getName(),playlist,s,1);
        }
        backButton(1);
        displayFrame();
    }

    /**
     * displays the ways a user can sort their playlist when they choose to sort a playlist
     * @param playlist the playlist to be sorted
     */
    public static void sortPlaylist(Playlist playlist){
        Main.clearFrame();
        setFrame("Select a sorting method.");
        addButton("Shuffle",playlist,8);
        addButton("By Name",playlist,9);
        addButton("By Artist",playlist,10);
        addButton("By Rating",playlist,11);
        backButton(2);
        displayFrame();
    }

    /**
     * lists all the playlists created
     * @throws FileNotFoundException when there are no playlist files
     */
    public static void getPlaylists() throws FileNotFoundException {
        File folder = new File("./playlists/");
        File[] listOfFiles = folder.listFiles();
        for(File f : listOfFiles) {
            playlists.add(new Playlist(String.valueOf(f),1));
        }
    }

    /**
     * adds new JLabels
     * @param text the text for the JLabel
     */
    public static void addLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(defaultFont);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
    }

    /**
     * adds a title to a JLabel
     * @param text the title
     */
    public static void addTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(titleFont);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
    }

    /**
     * adds a playlist button based on what the user clicked
     * @param text the text for the button
     * @param playlist the playlist
     * @param option the button the user clicked
     */
    public static void addPlaylistButton(String text, Playlist playlist, int option) {
        JButton button = new JButton(text);
        button.setFont(defaultFont);
        button.addActionListener(new PlaylistButtonListener(playlist,option));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
    }

    /**
     * the button created when a user is adding a new song
     * @param text what the button will say
     * @param playlist the playlist the user is editing
     * @param song the song the user is adding
     * @param option the button the user clicked
     */
    public static void addSongButton(String text, Playlist playlist, Song song, int option) {
        JButton button = new JButton(text);
        button.setFont(defaultFont);
        button.addActionListener(new SongButtonListener(playlist,song,option));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
    }

    /**
     * creates a back button
     * @param option the button the user clicked
     */
    public static void backButton(int option) {
        addLabel(" ");
        JButton button = new JButton("Back");
        button.setFont(defaultFont);
        button.addActionListener(new ButtonListener((option==0) ? 5 : (option==1) ? 6 : 7));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        panel.add(button);
    }

    /**
     * adds a new button based on the playlist being edited
     * @param text what the button will say
     * @param playlist the playlist being edited
     * @param option the button the user clicked
     */
    public static void addButton(String text, Playlist playlist, int option) {
        JButton button = new JButton(text);
        button.setFont(defaultFont);
        button.addActionListener(new ButtonListener(playlist,option));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
    }

    /**
     * adds a new button
     * @param text what the button will say
     * @param option the button the user clicked
     */
    public static void addButton(String text,int option) {
        JButton button = new JButton(text);
        button.setFont(defaultFont);
        button.addActionListener(new ButtonListener(option));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);

    }

    /**
     * creates a button for when the user wants to display song information
     * @param text what the button will say
     * @param p the playlist
     * @param t the JTextField for the song title
     * @param a the JTextField for the song's artist
     * @param r the JTextField for the song's rating
     */
    public static void addSongInfoButton(String text, Playlist p, JTextField t, JTextField a, JTextField r) {
        JButton button = new JButton(text);
        button.setFont(defaultFont);
        button.addActionListener(new SongButtonListener(p,t,a,r,0));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
    }

    /**
     * adds a new button
     * @param text what the button will say
     */
    public static void addTextButton(String text) {
        JButton button = new JButton(text);
        button.setFont(defaultFont);
        button.addActionListener(new TextFieldListener(playlistName));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
    }

    /**
     * saves the playlist
     * @throws IOException
     */
    public static void save() throws IOException {
        for(Playlist p : playlists) {
            p.save();
        }
    }

    /**
     * ends the program
     * @throws IOException
     */
    public static void exit() throws IOException {
        save();
        System.exit(0);
    }

    /**
     * clears everything being displayed
     */
    public static void clearFrame() {
        panel.removeAll();
        panel.remove(playlistName);
        panel.revalidate();
        panel.repaint();
    }

    /**
     * creates a label
     * @param label the name for the label
     */
    public static void setFrame(String label) {
        addTitle(label);
        addTitle(" ");
    }

    /**
     * displays the buttons, text fields, labels, etc.
     */
    public static void displayFrame() {
        frame.setVisible(true);
    }

    /**
     * removes a song from a playlist
     * @param playlist the playlist the song is in
     * @param song the song the user wants to remove
     * @throws IOException
     */
    public static void removeSong(Playlist playlist, Song song) throws IOException {
        playlist.removeSong(song);
        playlist.save();
        JOptionPane.showMessageDialog(null,song.getName()+" has been deleted.");
        clearFrame();
        removeSongs(playlist);
    }
}