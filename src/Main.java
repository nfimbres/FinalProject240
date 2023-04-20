import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

public class Main {
    public static ArrayList<Playlist> playlists = new ArrayList<>();
    public static JFrame mainFrame = new JFrame();
    public static JPanel mainPanel = new JPanel();
    public static JFrame newPlaylistFrame = new JFrame();
    public static JFrame choosePlaylistFrame = new JFrame();
    public static JFrame editplaylistFrame = new JFrame();
    public static JFrame viewPlaylistFrame = new JFrame();
    public static JFrame sortPlaylistFrame = new JFrame();
    public static Font defaultFont = new Font(Font.MONOSPACED, Font.PLAIN,  16);
    public static Font  titleFont  = new Font(Font.MONOSPACED, Font.ITALIC,  24);

    public static void main(String[] args) throws IOException {
        //download playlists from directory
        getPlaylists();

        //create frame
        JFrame.setDefaultLookAndFeelDecorated(true);
        setFrame(mainPanel,mainFrame,"Playlist Editor");

        //buttons
        addButton("Create Playlist",mainPanel,mainFrame,0);
        addButton("Delete Playlist",mainPanel,mainFrame,1);
        addButton("View Playlist",mainPanel,mainFrame,2);
        addButton("Edit Playlist",mainPanel,mainFrame,3);
        addButton("Sort Playlist",mainPanel,mainFrame,4);
        addButton("Exit",mainPanel,mainFrame,5);

        //display
        displayFrame(mainPanel, mainFrame);
    }
    public static void createPlaylist(){
        JPanel panel = new JPanel();
        panel.setFont(defaultFont);
        setFrame(panel, newPlaylistFrame,"Playlist Name");

        //buttons
        JTextField textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setMaximumSize(new Dimension(300,35));
        textField.setFont(defaultFont);
        panel.add(textField);
        addTextButton("Create", panel,textField);
        addLabel(" ", panel);
        addButton("Back", panel, newPlaylistFrame,8);

        //display
        displayFrame(panel, newPlaylistFrame);
    }
    public static void choosePlaylist(int option){
        JPanel panel = new JPanel();
        if(option==0) { // edit playlist
            setFrame(panel, choosePlaylistFrame, "Edit");
            //buttons
            for (Playlist p : playlists) {
                addPlaylistButton(p.getName(), p, panel,option);
            }
        } else if(option==1) { // delete playlist
            setFrame(panel, choosePlaylistFrame, "Delete");
            //buttons
            for (Playlist p : playlists) {
                addPlaylistButton(p.getName(), p, panel,option);
            }
        } else if(option==2) { // view playlist
            setFrame(panel, choosePlaylistFrame, "View");
            //buttons
            for (Playlist p : playlists) {
                addPlaylistButton(p.getName(),p, panel,option);
            }
        } else if(option==3) { // reorder playlist
            setFrame(panel, choosePlaylistFrame, "Sort");
            //buttons
            for (Playlist p : playlists) {
                addPlaylistButton(p.getName(),p, panel,option);
            }
        }
        addLabel(" ", panel);
        addButton("Back", panel, choosePlaylistFrame,8);
        displayFrame(panel, choosePlaylistFrame);
    }
    public static void editPlaylist(Playlist playlist){
        JPanel panel = new JPanel();
        setFrame(panel, editplaylistFrame,"Add or delete a songs?");

        //buttons
        addButton("Add",playlist, panel, editplaylistFrame,6);
        addButton("Delete",playlist, panel, editplaylistFrame,7);

        //display
        displayFrame(panel, editplaylistFrame);
    }
    public static void sortPlaylist(Playlist playlist){
        JPanel panel = new JPanel();
        setFrame(panel, sortPlaylistFrame,"Select a sorting method");

        //buttons
        addButton("Shuffle",playlist, panel, sortPlaylistFrame,10);
        addButton("By Name",playlist, panel, sortPlaylistFrame,11);
        addButton("By Artist",playlist, panel, sortPlaylistFrame,12);
        addButton("By Rating",playlist, panel, sortPlaylistFrame,13);

        //display
        displayFrame(panel, sortPlaylistFrame);
    }
    public static void getPlaylists() throws FileNotFoundException {
        File folder = new File("./playlists/");
        File[] listOfFiles = folder.listFiles();
        for(File f : listOfFiles) {
            playlists.add(new Playlist(String.valueOf(f),1));
        }
    }
    public static void addLabel(String text, JPanel p) {
        JLabel label = new JLabel(text);
        label.setFont(defaultFont);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(label);
    }
    public static void addTitle(String text, JPanel p) {
        JLabel label = new JLabel(text);
        label.setFont(titleFont);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(label);
    }
    public static void addPlaylistButton(String text, Playlist playlist, JPanel panel, int option) {
        JButton button = new JButton(text);
        button.setFont(defaultFont);
        button.addActionListener(new PlaylistButtonListener(playlist,option));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
    }
    public static void addSongButton(String text, Playlist playlist, Song song, JPanel panel, int option) {
        JButton button = new JButton(text);
        button.setFont(defaultFont);
        button.addActionListener(new SongButtonListener(playlist,song,option));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
    }
    public static void addButton(String text, Playlist playlist, JPanel panel, JFrame frame, int option) {
        JButton button = new JButton(text);
        button.setFont(defaultFont);
        button.addActionListener(new ButtonListener(playlist,frame,option));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
    }
    public static void addButton(String text, JPanel panel, JFrame frame, int option) {
        JButton button = new JButton(text);
        button.setFont(defaultFont);
        if(option==8 || option==9) {
            button.addActionListener(new ButtonListener(frame,option));
        } else {
            button.addActionListener(new ButtonListener(option));
        }
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);

    }
    public static void addSongInfoButton(String text, JPanel panel, Playlist p,JTextField t, JTextField a, JTextField r) {
        JButton button = new JButton(text);
        button.setFont(defaultFont);
        button.addActionListener(new SongButtonListener(p,t,a,r,0));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
    }
    public static void addTextButton(String text, JPanel panel, JTextField t) {
        JButton button = new JButton(text);
        button.setFont(defaultFont);
        button.addActionListener(new TextFieldListener(t));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
    }
    public static void save() throws IOException {
        for(Playlist p : playlists) {
            p.save();
        }
    }
    public static void exit() throws IOException {
        save();
        System.exit(0);
    }
    public static void clearFrame(JFrame frame) {
        frame.getContentPane().removeAll();
    }
    public static void setFrame(JPanel panel, JFrame frame, String label) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(100, 10, 10, 25)));
        addTitle(label, panel);
        addTitle(" ", panel);
    }
    public static void displayFrame(JPanel panel, JFrame frame) {
        frame.add(panel);
        frame.setSize(750,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public static void addSongs(Playlist playlist) {
        JPanel panel = new JPanel();
        setFrame(panel, editplaylistFrame,"Add");
        addLabel("Title", panel);
        JTextField title = new JTextField();
        title.setMaximumSize(new Dimension(300,25));
        title.setHorizontalAlignment(JTextField.CENTER);
        panel.add(title, BorderLayout.CENTER);
        addLabel("Artist", panel);
        JTextField artist = new JTextField();
        artist.setMaximumSize(new Dimension(300,25));
        artist.setHorizontalAlignment(JTextField.CENTER);
        panel.add(artist, BorderLayout.CENTER);
        addLabel("Rating (1-5)", panel);
        JTextField rating = new JTextField();
        rating.setMaximumSize(new Dimension(300,25));
        rating.setHorizontalAlignment(JTextField.CENTER);
        panel.add(rating, BorderLayout.CENTER);

        addSongInfoButton("Add", panel,playlist,title,artist,rating);
        addLabel(" ", panel);
        addButton("Done", panel, editplaylistFrame,9);

        displayFrame(panel,editplaylistFrame);
    }
    public static void deleteSongs(Playlist playlist) {
        JPanel panel = new JPanel();
        setFrame(panel, editplaylistFrame, "Select a song to delete");

        //buttons
        for (Song s : playlist.getSongs()) {
            addSongButton(s.getName(),playlist,s,panel,1);
        }
        addLabel(" ", panel);
        addButton("Done", panel, editplaylistFrame,9);
        displayFrame(panel, editplaylistFrame);
    }
    public static void removeSong(Playlist playlist, Song song) throws IOException {

        playlist.getSongs().remove(song);
        playlist.save();
        JOptionPane.showMessageDialog(null,song.getName()+" has been deleted.");
        clearFrame(editplaylistFrame);
        deleteSongs(playlist);
    }
    public static void removePlaylist(Playlist playlist) {
        playlists.remove(playlist);
        File fileToDelte = new File(playlist.getFileName());
        fileToDelte.delete();
        JOptionPane.showMessageDialog(null,playlist.getName()+" has been deleted.");
        clearFrame(choosePlaylistFrame);
        choosePlaylistFrame.dispose();
        mainFrame.setVisible(true);
    }
    public static void viewPlaylist(Playlist playlist) {
        JPanel panel = new JPanel();
        setFrame(panel, viewPlaylistFrame,playlist.getName());
        if(playlist.getSongs().size()>0) {
            int i = 1;
            for (Song s : playlist.getSongs()) {
                addLabel(i + ". " + s.songDetails(), panel);
                i++;
            }
        } else {
            addLabel("This playlist is empty.", panel);
        }
        addLabel(" ", panel);
        addButton("Done", panel, viewPlaylistFrame,9);
        displayFrame(panel, viewPlaylistFrame);
    }
}