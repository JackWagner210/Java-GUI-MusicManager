package albumcollection.album;

import albumcollection.Comparable;
import java.io.Serializable;

public abstract class Album implements Serializable, Playable, Comparable<Album> {

    protected String title;
    protected String artist;
    protected int releaseYear;
    protected MusicGenre genre;

    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public MusicGenre getGenre() {
        return this.genre;
    }

    @Override
    public String toString() {
        return "Title: " + this.title + ", Artist: " + this.artist +
                ", Year: " + this.releaseYear + ", Genre: " + this.genre.toString();
    }

    public abstract void play();

    public abstract void fastForward();

    public abstract void rewind();

    @Override
    public int compareTo(Album o) {
        return this.artist.compareToIgnoreCase(o.artist);
    }
}