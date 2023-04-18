import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Playlist {
    private String name;
    private String fileName;
    private ArrayList<Song> songs;
    public Playlist() {

    }
    public Playlist(String name) {
        this.name = name;
        this.fileName = "./playlists/"+name+".playlist";
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
    public String getName() {
        return name;
    }
    public String getFileName() {
        return fileName;
    }
}