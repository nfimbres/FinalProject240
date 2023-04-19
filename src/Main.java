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
        setFrame(mainFrame,true);

        //buttons
        addButton("New Playlist",mainFrame,0);
        addButton("Edit Playlist",mainFrame,1);
        addButton("Shuffle Playlist",mainFrame,2);
        addButton("Exit",mainFrame,3);

        //display
        displayFrame(mainFrame);
    }
    public static void newPlaylist(){
        setFrame(newPlaylistFrame,"Enter a playlist name",false);

        //buttons
        JTextField textField = new JTextField();
        newPlaylistFrame.getContentPane().add(textField);
        addButton("Create",newPlaylistFrame,textField);

        //display
        displayFrame(newPlaylistFrame);
    }
    public static void choosePlaylist(){
        setFrame(choosePlaylistFrame,"Please choose which playlist you wish to edit",false);

        //buttons
        for(Playlist p : playlists) {
            addButton(p.getName(),choosePlaylistFrame,p);
        }

        displayFrame(choosePlaylistFrame);
    }
    public static void editPlaylist(Playlist playlist){
        setFrame(editplaylistFrame,"Would you like to add or remove songs",false);

        //buttons
        addButton("Add",editplaylistFrame,4);
        addButton("Remove",editplaylistFrame,4);

        //display
        displayFrame(editplaylistFrame);
    }
    public static void shufflePlaylist(){
        try {
            getPlaylists();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"No playlists found");
        }
    }
    public static void reorderPlaylist(){
        try {
            getPlaylists();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"No playlists found");
        }
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
    public static void addLabel(String text, JFrame j) {
        JLabel label = new JLabel(text);
        j.getContentPane().add(label);
    }
    public static void addButton(String text, JFrame frame, Playlist p) {
        JButton button = new JButton(text);
        button.addActionListener(new PlaylistButtonListener(p));
        frame.getContentPane().add(button);
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

    }
    public static void setFrame(JFrame frame, boolean exit) {
        if(exit) {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        frame.getContentPane().setLayout(new BoxLayout(newPlaylistFrame.getContentPane(), BoxLayout.Y_AXIS));
    }
    public static void setFrame(JFrame frame, String label, boolean exit) {
        if(exit) {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        frame.getContentPane().setLayout(new BoxLayout(newPlaylistFrame.getContentPane(), BoxLayout.Y_AXIS));
        addLabel(label, frame);
    }
    public static void displayFrame(JFrame frame) {
        frame.pack();
        frame.setSize(150,150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}