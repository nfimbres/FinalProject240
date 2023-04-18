import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Playlist> playlists = new ArrayList<>();
    public static JFrame mainFrame = new JFrame();
    public static JFrame newPlaylistFrame = new JFrame();
    public static JFrame playlistsFrame = new JFrame();

    public static void main(String[] args) {
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
    public static void addButton(String text,JFrame j, int option) {
        JButton button = new JButton(text);
        button.addActionListener(new ButtonListener(option));
        j.getContentPane().add(button);
    }
    public static void addButton(String text, JFrame j, JTextField t, int option) {
        JButton button = new JButton(text);
        button.addActionListener(new TextFieldListener(t,option,j));
        j.getContentPane().add(button);
    }
    public static void newPlaylist(){
        newPlaylistFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newPlaylistFrame.getContentPane().setLayout(new BoxLayout(newPlaylistFrame.getContentPane(), BoxLayout.Y_AXIS));
        addLabel("Enter a playlist name", newPlaylistFrame); //add a label

        //buttons
        JTextField textField = new JTextField();
        newPlaylistFrame.getContentPane().add(textField);
        addButton("Create",newPlaylistFrame,textField,0);

        //display
        newPlaylistFrame.pack();
        newPlaylistFrame.setSize(150,150);
        newPlaylistFrame.setLocationRelativeTo(null);
        newPlaylistFrame.setVisible(true);
    }
    public static void editPlaylist() {
        if (playlists.size() > 0) {
            playlistsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            playlistsFrame.getContentPane().setLayout(new BoxLayout(newPlaylistFrame.getContentPane(), BoxLayout.Y_AXIS));
            addLabel("Choose a playlist to edit", playlistsFrame); //add a label
            for (Playlist p : playlists) {
                addButton(p.getName(), playlistsFrame, 4);
            }
            //display
            playlistsFrame.pack();
            playlistsFrame.setSize(150, 150);
            playlistsFrame.setLocationRelativeTo(null);
            playlistsFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null,"There are no playlists to edit");
        }
    }
    public static void shufflePlaylist(){
    }
    public static void reorderPlaylist(){
    }
    public static ArrayList<String> getPlaylists(){
        return new ArrayList<>();
    }
    public static Playlist choosePlaylist(){
        return new Playlist();
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