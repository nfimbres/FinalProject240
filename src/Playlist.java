import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Playlist {
    private String name;
    private String filename;
    private ArrayList<Song> songs = new ArrayList<>();
    public Playlist(String name,int option) throws FileNotFoundException {
        if(option==0) {
            this.name = name;
            this.filename = "./playlists/" + name + ".playlist";
        } else if(option==1) {
            this.name = name.replace("./playlists/","").replace(".playlist","");
            this.filename = name;
            File deck = new File(this.filename);
            Scanner reader = new Scanner(deck);
            while(reader.hasNextLine()) {
                String n = reader.nextLine();
                String a = reader.nextLine();
                int r = Integer.parseInt(reader.nextLine());
                reader.nextLine();
                this.songs.add(new Song(n,a,r));
            }
        }
    }
    public void save() throws IOException {
        File file = new File(this.filename);
        PrintWriter out = new PrintWriter(file);
        try {
            for (Song s : this.songs) {
                s.save(out);
            }
            out.close();
        } catch (NullPointerException e) {}
    }
    public void addSong(Song s) {
        songs.add(s);
    }
    public void removeSong(Song s) {
        songs.remove(s);
    }
    public void shuffle() {
        Collections.shuffle(songs);
    }
    public void reorder(int order) {
        Collections.sort(songs);
    }
    public String getName() {
        return name;
    }
    public String getFileName() {
        return filename;
    }
    public ArrayList<Song> getSongs() {
        return songs;
    }
}