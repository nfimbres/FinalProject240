import java.io.PrintWriter;

public class Song {
    private String name;
    private String artist;
    private int rating;
    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
        rating = 0;
    }
    public void save(PrintWriter out) {
        out.print(this.name+"\n");
        out.print(this.artist+"\n");
        out.print(this.rating+"\n\n");
    }
    public String getName() {
        return "";
    }
    public String getArtist() {
        return "";
    }
    public int getRating() {
        return 0;
    }
    public void setName() {
    }
    public void setArtist() {
    }
    public void setRating() {
    }
}