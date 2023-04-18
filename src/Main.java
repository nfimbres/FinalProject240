import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Playlist> playlists = new ArrayList<>();
    public static JFrame mainFrame = new JFrame();
    public static JFrame newPlaylistFrame = new JFrame();
    public static JFrame editPlaylistFrame = new JFrame();
    public static JFrame playlistsFrame = new JFrame();

    public static void main(String[] args) {
        //create frame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));

        //buttons
        addButton("New Playlist",0);
        addButton("Edit Playlist",1);
        addButton("Shuffle Playlist",2);
        addButton("Exit",3);
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
    public static void addButton(String text, int option) {
        JButton button = new JButton(text);
        button.addActionListener(new ButtonListener(option));
        mainFrame.getContentPane().add(button);
    }
    public static void addButton(String text, JFrame j, JTextField t, int option,JFrame f) {
        JButton button = new JButton(text);
        button.addActionListener(new TextFieldListener(t,option,f));
        j.getContentPane().add(button);
    }
    public static void newPlaylist(){
        newPlaylistFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newPlaylistFrame.getContentPane().setLayout(new BoxLayout(newPlaylistFrame.getContentPane(), BoxLayout.Y_AXIS));
        addLabel("Enter a playlist name", newPlaylistFrame); //add a label

        //buttons
        JTextField textField = new JTextField();
        newPlaylistFrame.getContentPane().add(textField);
        addButton("Create",newPlaylistFrame,textField,0,newPlaylistFrame);

        //display
        newPlaylistFrame.pack();
        newPlaylistFrame.setSize(150,150);
        newPlaylistFrame.setLocationRelativeTo(null);
        newPlaylistFrame.setVisible(true);
    }
    public static void editPlaylist(){
        editPlaylistFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editPlaylistFrame.getContentPane().setLayout(new BoxLayout(editPlaylistFrame.getContentPane(), BoxLayout.Y_AXIS));
        addLabel("Please choose which playlist you wish to edit", editPlaylistFrame);
        choosePlaylist();


        editPlaylistFrame.pack();
        editPlaylistFrame.setSize(150,150);
        editPlaylistFrame.setLocationRelativeTo(null);
        editPlaylistFrame.setVisible(true);
    }
    public static void shufflePlaylist(){
    }
    public static void reorderPlaylist(){
    }
    public static ArrayList<String> getPlaylists(){

        ArrayList<String> PLN = new ArrayList<String>();
        for(int i = 0; i>playlists.size(); i++)
        {
            PLN.add(playlists.get(i).getFileName());
        }
        return PLN;
    }
    public static Playlist choosePlaylist(){

        getPlaylists();
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