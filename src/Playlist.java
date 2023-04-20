import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * this class creates a playlist objects
 */

public class Playlist {
    private String name;
    private String filename;
    private ArrayList<Song> songs = new ArrayList<>();

    /**
     * constructor
     * @param name the name of the playlist
     * @param option which button was selected
     * @throws FileNotFoundException when there is no file associated with the name given
     */
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

    /**
     * saves the playlist to a file
     * @throws IOException
     */
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

    /**
     * adds a song to the playlist
     * @param s the song to be added
     */
    public void addSong(Song s) {
        songs.add(s);
    }

    /**
     * removes a song from the playlist
     * @param s the song to be removed
     */
    public void removeSong(Song s) {
        songs.remove(s);
    }

    /**
     * shuffles the playlist
     */
    public void shuffle() {
        Collections.shuffle(songs);
    }

    /**
     * orders the playlist by song name, artist name, or rating
     * @param order which way the playlist will be ordered based on the button clicked
     */
    public void sort(int order) {
        if(order==8) {
            Collections.shuffle(songs);
        } else if (order==9) {
            Collections.sort(songs, new OrderByName());
        } else if (order==10) {
            Collections.sort(songs, new OrderByArtist());
        } else if (order==11) {
            Collections.sort(songs, new OrderByRating());
        }
    }

    /**
     * gets the name of the playlist
     * @return the name of the playlist
     */
    public String getName() {
        return name;
    }

    /**
     * gets the name of the file
     * @return the file name
     */
    public String getFileName() {
        return filename;
    }

    /**
     * gets the songs in the playlist
     * @return an arraylist of the songs
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }
}