package albumcollection.album;

import albumcollection.album.track.Track;
import java.util.ArrayList;

public class CompactDisc extends Album {

    private ArrayList<Track> tracks;
    private int currentTrackIndex;

    public CompactDisc(String title, String artist, int releaseYear, MusicGenre genre, ArrayList<Track> tracks) {
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.tracks = tracks;
        this.currentTrackIndex = 0;
    }

    public CompactDisc(String title, String artist, int releaseYear, MusicGenre genre) {
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.currentTrackIndex = 0;
        tracks = new ArrayList<Track>();
        for (int i = 0; i < 10; i++) {
            Track track = new Track(180);
            this.tracks.add(track);
        }
    }

    public void play() {
        if (this.currentTrackIndex < this.tracks.size()) {
            Track currentTrack = this.tracks.get(currentTrackIndex);
            currentTrack.play();
        }
    }

    public void skipToNext() {
        if (this.currentTrackIndex < this.tracks.size() - 1) {
            this.currentTrackIndex++;
        } else {
            this.currentTrackIndex = 0;
        }
        System.out.println("Skip To Next");
        play();
    }

    public void skipToPrevious() {
        if (this.currentTrackIndex > 0) {
            this.currentTrackIndex--;
        } else {
            this.currentTrackIndex = tracks.size() - 1;
        }
        System.out.println("Skip To Previous");
        play();
    }

    public void fastForward() {
        if (this.currentTrackIndex < this.tracks.size()) {
            Track currentTrack = this.tracks.get(currentTrackIndex);
            currentTrack.fastForward();
        }
    }

    public void rewind() {
        if (this.currentTrackIndex < this.tracks.size()) {
            Track currentTrack = this.tracks.get(currentTrackIndex);
            currentTrack.rewind();
        }
    }
}