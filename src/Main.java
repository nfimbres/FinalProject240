import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void Main(String args[]) {
        JFrame frame = new JFrame("Playlister");
    }
    public static void addLabel(String text, JFrame j) {
        JLabel label = new JLabel(text);
        j.getContentPane().add(label);
    }
    public static void addButton(String text, JFrame j) {
        JButton button = new JButton(text);
        button.addActionListener(new ButtonListener());
        j.getContentPane().add(button);
    }
    public static void addButton(String text, JFrame j, JTextField t) {
        JButton button = new JButton(text);
        button.addActionListener(new TextFieldListener(t));
        j.getContentPane().add(button);
    }
    public static void newPlaylist(){
    }
    public static void editPlaylist(){
    }
    public static void shufflePlaylist(){
    }
    public static void reorderPlaylist(){
    }
    public static ArrayList<String> getPlaylists(){
    }
    public static Playlist choosePlaylist(){
    }
    public static void ask(){
    }
    public static void exit(){
    }
}