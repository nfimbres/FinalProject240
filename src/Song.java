import java.io.PrintWriter;
import java.util.Comparator;

public class Song{
    private String name;
    private String artist;
    private int rating;
    public Song(String name, String artist, int rating) {
        this.name = name;
        this.artist = artist;
        this.rating = rating;
    }
    public void save(PrintWriter out) {
        out.print(this.name+"\n");
        out.print(this.artist+"\n");
        out.print(this.rating+"\n\n");
    }
    public String getName() {
        return name;
    }
    public String getArtist() {
        return artist;
    }
    public int getRating() {
        return rating;
    }
    public void setName(String n) {

        this.name = n;
    }
    public void setArtist(String a) {

        this.artist = a;
    }
    public void setRating(int r) {

        this.rating = r;
    }
    public String songDetails() {

        return this.name + " by " + this.artist + " - " + this.rating + "/5";
    }
}
class OrderByName implements Comparator<Song>{
    private static boolean asc = false;
    public OrderByName() {
        asc = !(asc);
    }
    @Override
    public int compare(Song s1, Song s2) {
        if(asc) {
            return s1.getName().compareTo(s2.getName());
        } else {
            return s2.getName().compareTo(s1.getName());
        }
    }
}
class OrderByArtist implements Comparator<Song>{
    private static boolean asc = false;
    public OrderByArtist() {
        asc = !(asc);
    }
    @Override
    public int compare(Song s1, Song s2) {
        if(asc) {
            return s1.getArtist().compareTo(s2.getArtist());
        } else {
            return s2.getArtist().compareTo(s1.getArtist());
        }
    }
}
class OrderByRating implements Comparator<Song>{
    private static boolean asc = true;
    public OrderByRating() {
        asc = !(asc);
    }
    @Override
    public int compare(Song s1, Song s2) {
        if(asc) {
            return s1.getRating()-(s2.getRating());
        } else {
            return s2.getRating()-(s1.getRating());
        }
    }
}