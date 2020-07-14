package albumcollection.album;

public class CassetteTape extends Album {

    private double lengthInSeconds;
    private double currentPosition;

    public CassetteTape(String title, String artist, int releaseYear, MusicGenre genre, double lengthInSeconds) {
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.lengthInSeconds = lengthInSeconds;
        this.currentPosition = 0.0;
    }

    public void play() {
        System.out.println("Cassette Tape Play\n");
    }

    public void fastForward() {
        System.out.println("Cassette Tape Fast Forward\n");
    }

    public void rewind() {
        System.out.println("Cassette Tape Rewind\n");
    }
}