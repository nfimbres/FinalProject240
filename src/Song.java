import java.io.PrintWriter;
import java.util.Comparator;

public class Song{
    private String name;
    private String artist;
    private int rating;

    /**
     * constructor
     * @param name the name of the song
     * @param artist the artist
     * @param rating what the user rates the song (1-5)
     */
    public Song(String name, String artist, int rating) {
        this.name = name;
        this.artist = artist;
        this.rating = rating;
    }

    /**
     * writes the song into the playlist file
     * @param out
     */
    public void save(PrintWriter out) {
        out.print(this.name+"\n");
        out.print(this.artist+"\n");
        out.print(this.rating+"\n\n");
    }

    /**
     * gets the name of the song
     * @return the name of the song
     */
    public String getName() {
        return name;
    }

    /**
     * gets the artist of the song
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * gets the user's rating of the song
     * @return rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * gives the song a name
     * @param n the name of the song
     */
    public void setName(String n) {

        this.name = n;
    }

    /**
     * gives the song an artist
     * @param a the artist
     */
    public void setArtist(String a) {

        this.artist = a;
    }

    /**
     * gives the song a rating
     * @param r the user's rating for the song
     */
    public void setRating(int r) {

        this.rating = r;
    }

    /**
     * formats a string containing all the song's details
     * @return a string of the song's details ("songTitle by songArtist - rating/5")
     */
    public String songDetails() {

        return this.name + " by " + this.artist + " - " + this.rating + "/5";
    }
}

/**
 * this class allows the songs to be sorted by their titles
 */
class OrderByName implements Comparator<Song>{
    private static boolean asc = false;
    public OrderByName() {
        asc = !(asc);
    }

    /**
     * compares all the songs
     * @param s1 the first song title to be compared.
     * @param s2 the second song title to be compared.
     * @return
     */
    @Override
    public int compare(Song s1, Song s2) {
        if(asc) {
            return s1.getName().compareTo(s2.getName());
        } else {
            return s2.getName().compareTo(s1.getName());
        }
    }
}

/**
 * this class allows the songs to be sorted by their artists
 */
class OrderByArtist implements Comparator<Song>{
    private static boolean asc = false;
    public OrderByArtist() {
        asc = !(asc);
    }

    /**
     * compares all the songs
     * @param s1 the first artist to be compared.
     * @param s2 the second artist to be compared.
     * @return
     */
    @Override
    public int compare(Song s1, Song s2) {
        if(asc) {
            return s1.getArtist().compareTo(s2.getArtist());
        } else {
            return s2.getArtist().compareTo(s1.getArtist());
        }
    }
}

/**
 * this class allows the songs to be sorted by the user rating
 */
class OrderByRating implements Comparator<Song>{
    private static boolean asc = true;
    public OrderByRating() {
        asc = !(asc);
    }

    /**
     * compares the ratings
     * @param s1 the first rating to be compared.
     * @param s2 the second rating to be compared.
     * @return
     */
    @Override
    public int compare(Song s1, Song s2) {
        if(asc) {
            return s1.getRating()-(s2.getRating());
        } else {
            return s2.getRating()-(s1.getRating());
        }
    }
}