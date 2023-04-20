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
    public static JFrame viewPlaylistFrame = new JFrame();

    public static void main(String[] args) throws FileNotFoundException {
        //download playlists from directory
        getPlaylists();

        //create frame
        setFrame(mainFrame);

        //buttons
        addButton("New Playlist",mainFrame,0);
        addButton("Delete Playlist",mainFrame,1);
        addButton("View Playlist",mainFrame,2);
        addButton("Edit Playlist",mainFrame,3);
        addButton("Reorder Playlist",mainFrame,4);
        addButton("Exit",mainFrame,5);

        //display
        displayFrame(mainFrame);
    }
    public static void newPlaylist(){
        setFrame(newPlaylistFrame,"Enter a playlist name");

        //buttons
        JTextField textField = new JTextField();
        newPlaylistFrame.getContentPane().add(textField);
        addTextButton("Create",newPlaylistFrame,textField);
        addButton("Back",newPlaylistFrame,8);

        //display
        displayFrame(newPlaylistFrame);
    }
    public static void choosePlaylist(int option){
        if(option==0) { // edit playlist
            setFrame(choosePlaylistFrame, "Select a playlist to edit");
            //buttons
            for (Playlist p : playlists) {
                addPlaylistButton(p.getName(), p,choosePlaylistFrame,option);
            }
        } else if(option==1) { // delete playlist
            setFrame(choosePlaylistFrame, "Select a playlist to delete");
            //buttons
            for (Playlist p : playlists) {
                addPlaylistButton(p.getName(), p,choosePlaylistFrame,option);
            }
        } else if(option==2) { // view playlist
            setFrame(choosePlaylistFrame, "Select a playlist to view");
            //buttons
            for (Playlist p : playlists) {
                addPlaylistButton(p.getName(), p,choosePlaylistFrame,option);
            }
        }
        addButton("Back",choosePlaylistFrame,8);
        displayFrame(choosePlaylistFrame);
    }
    public static void editPlaylist(Playlist playlist){
        setFrame(editplaylistFrame,"Would you like to add or delete songs");

        //buttons
        addButton("Add",playlist,editplaylistFrame,6);
        addButton("Delete",playlist,editplaylistFrame,7);

        //display
        displayFrame(editplaylistFrame);
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
    }
    public static void addLabel(String text, JFrame j) {
        JLabel label = new JLabel("  "+text+"  ");
        j.getContentPane().add(label);
    }
    public static void addPlaylistButton(String text, Playlist playlist, JFrame frame, int option) {
        JButton button = new JButton(text);
        button.addActionListener(new PlaylistButtonListener(playlist,option));
        frame.getContentPane().add(button);
    }
    public static void addSongButton(String text, Playlist playlist, Song song, JFrame frame, int option) {
        JButton button = new JButton(text);
        button.addActionListener(new SongButtonListener(playlist,song,option));
        frame.getContentPane().add(button);
    }
    public static void addButton(String text, Playlist playlist, JFrame frame, int option) {
        JButton button = new JButton(text);
        button.addActionListener(new ButtonListener(playlist,frame,option));
        frame.getContentPane().add(button);
    }
    public static void addButton(String text, JFrame frame, int option) {
        JButton button = new JButton(text);
        if(option==8) {
            button.addActionListener(new ButtonListener(frame,option));
        } else {
            button.addActionListener(new ButtonListener(option));
        }
        frame.getContentPane().add(button);
    }
    public static void addSongInfoButton(String text, JFrame frame, Playlist p,JTextField t, JTextField a, JTextField r) {
        JButton button = new JButton(text);
        button.addActionListener(new SongButtonListener(p,t,a,r,0));
        frame.getContentPane().add(button);
    }
    public static void addTextButton(String text, JFrame frame, JTextField t) {
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
        frame.getContentPane().removeAll();
    }
    public static void setFrame(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    }
    public static void setFrame(JFrame frame, String label) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        addLabel(label, frame);
    }
    public static void displayFrame(JFrame frame) {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public static void addSongs(Playlist playlist) {
        setFrame(editplaylistFrame);
        addLabel("Title",editplaylistFrame);
        JTextField title = new JTextField();
        editplaylistFrame.getContentPane().add(title);
        addLabel("Artist",editplaylistFrame);
        JTextField artist = new JTextField();
        editplaylistFrame.getContentPane().add(artist);
        addLabel("Rating (1-5)",editplaylistFrame);
        JTextField rating = new JTextField();
        editplaylistFrame.getContentPane().add(rating);

        addSongInfoButton("Add",editplaylistFrame,playlist,title,artist,rating);
        addButton("Done",editplaylistFrame,8);

        displayFrame(editplaylistFrame);
    }
    public static void deleteSongs(Playlist playlist) {
        setFrame(editplaylistFrame, "Select a song to delete");

        //buttons
        for (Song s : playlist.getSongs()) {
            addSongButton(s.getName(),playlist,s,editplaylistFrame,1);
        }
        displayFrame(editplaylistFrame);
    }
    public static void removeSong(Playlist playlist, Song song) throws IOException {

        playlist.getSongs().remove(song);
        playlist.save();
        JOptionPane.showMessageDialog(null,song.getName()+" has been deleted.");
        clearFrame(editplaylistFrame);
        editplaylistFrame.dispose();
        mainFrame.setVisible(true);
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
        setFrame(viewPlaylistFrame);
        if(playlist.getSongs().size()>0) {
            int i = 1;
            for (Song s : playlist.getSongs()) {
                addLabel(i + ". " + s.songDetails(), viewPlaylistFrame);
                i++;
            }
        } else {
            addLabel("This playlist is empty.",viewPlaylistFrame);
        }
        addButton("Done",viewPlaylistFrame,8);
        displayFrame(viewPlaylistFrame);
    }
}