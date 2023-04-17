import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Playlist {
    private String fileName;
    private ArrayList<Song> songs;
    public Playlist() {
    }
    public Playlist(String fileName) {
        this.fileName = fileName;
    }
    public void save() throws IOException {
        File file = new File(this.fileName);
        PrintWriter out = new PrintWriter(file);
        try {
            for (Song s : this.songs) {
                s.save(out);
            }
            out.close();
        } catch (NullPointerException e) {}
    }
    public void addSong() {
    }
    public void removeSong() {
    }
    public void shuffle() {
    }
    public void reorder(int order) {
    }
    public String getFileName() {
        return "";
    }
}