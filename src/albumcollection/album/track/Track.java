package albumcollection.album.track;

import albumcollection.album.Playable;
import java.io.Serializable;

public class Track implements Serializable, Playable {

    private double lengthInSeconds;
    private double currentPosition;

    public Track(double lengthInSeconds) {
        this.lengthInSeconds = lengthInSeconds;
        this.currentPosition = 0.0;
    }

    public void play() {
        System.out.println("Track Play\n");
    }

    public void fastForward() {
        System.out.println("Track Fast Forward\n");
    }

    public void rewind() {
        System.out.println("Track Rewind\n");
    }
}