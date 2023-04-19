import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Playlist> playlists = new ArrayList<>();
    public static JFrame mainFrame = new JFrame();
    public static JFrame newPlaylistFrame = new JFrame();
    public static JFrame choosePlaylistFrame = new JFrame();
    public static JFrame editplaylistFrame = new JFrame();

    public static void main(String[] args) throws FileNotFoundException {
        //download playlists from directory
        getPlaylists();

        //create frame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));

        //buttons
        addButton("New Playlist",mainFrame,0);
        addButton("Edit Playlist",mainFrame,1);
        addButton("Shuffle Playlist",mainFrame,2);
        addButton("Exit",mainFrame,3);

        //display
        mainFrame.pack();
        mainFrame.setSize(150,150);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
    public static void addLabel(String text, JFrame j) {
        JLabel label = new JLabel(text);
        j.getContentPane().add(label);
    }
    public static void addButton(String text, JFrame frame, int option) {
        JButton button = new JButton(text);
        button.addActionListener(new MenuButtonListener(option));
        frame.getContentPane().add(button);
    }
    public static void addButton(String text, JFrame frame, JTextField t) {
        JButton button = new JButton(text);
        button.addActionListener(new TextFieldListener(t));
        frame.getContentPane().add(button);
    }
    public static void addButton(String text, JFrame frame, Playlist p) {
        JButton button = new JButton(text);
        button.addActionListener(new PlaylistButtonListener(p));
        frame.getContentPane().add(button);
    }
    public static void newPlaylist(){
        newPlaylistFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newPlaylistFrame.getContentPane().setLayout(new BoxLayout(newPlaylistFrame.getContentPane(), BoxLayout.Y_AXIS));
        addLabel("Enter a playlist name", newPlaylistFrame); //add a label

        //buttons
        JTextField textField = new JTextField();
        newPlaylistFrame.getContentPane().add(textField);
        addButton("Create",newPlaylistFrame,textField);

        //display
        newPlaylistFrame.pack();
        newPlaylistFrame.setSize(150,150);
        newPlaylistFrame.setLocationRelativeTo(null);
        newPlaylistFrame.setVisible(true);
    }
    public static void choosePlaylist(){
        choosePlaylistFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        choosePlaylistFrame.getContentPane().setLayout(new BoxLayout(choosePlaylistFrame.getContentPane(),BoxLayout.Y_AXIS));
        addLabel("Please choose which playlist you wish to edit", choosePlaylistFrame);

        for(Playlist p : playlists) {
            addButton(p.getName(),choosePlaylistFrame,p);
        }

        choosePlaylistFrame.pack();
        choosePlaylistFrame.setSize(300,150);
        choosePlaylistFrame.setLocationRelativeTo(null);
        choosePlaylistFrame.setVisible(true);
    }
    public static void editPlaylist(Playlist playlist){

    }
    public static void shufflePlaylist(){
    }
    public static void reorderPlaylist(){
    }
    public static void getPlaylists() throws FileNotFoundException {
        File folder = new File("./playlists/");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> playlistNames = new ArrayList<String>();
        for(File f : listOfFiles) {
            playlists.add(new Playlist(String.valueOf(f),1));
        }
        for(Playlist p : playlists) {
            for(Song s : p.getSongs()) {
                JOptionPane.showMessageDialog(null,s.songDetails());
            }
        }
    }
    public static void ask(){
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
}