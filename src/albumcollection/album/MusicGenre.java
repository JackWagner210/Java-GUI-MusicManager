package albumcollection.album;

/**
 * This enum defines the music genre options of an album.
 *
 * @author Shawn Seals
 */
public enum MusicGenre {

    ROCK ("Rock", 0),
    RAP ("Rap", 1),
    COUNTRY ("Country", 2),
    POP ("Pop", 3);

    private String name;
    private int intValue;

    private MusicGenre(String name, int intValue) {
        this.name = name;
        this.intValue = intValue;
    }

    /**
     * This method returns a <b><CODE>String</CODE></b> representation of a <b><CODE>MusicGenre</CODE></b> enum.
     *
     * @return a <b><CODE>String</CODE></b> representation of the music genre.
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * This method returns an <b><CODE>int</CODE></b> representation of a <b><CODE>MusicGenre</CODE></b>.
     *
     * @return an <b><CODE>int</CODE></b> representation of the music genre.
     */
    public int toInt() {
        return this.intValue;
    }

    /**
     * This method returns the <b><CODE>MusicGenre</CODE></b> corresponding to the passed in
     * <b><CODE>int</CODE></b> value.
     *
     * @param intValue an <b><CODE>int</CODE></b> representing the desired <b><CODE>MusicGenre</CODE></b>.
     *
     * @return a <b><CODE>MusicGenre</CODE></b> corresponding to the passed in value.
     */
    public static MusicGenre fromInt(int intValue) {
        switch (intValue) {
            case 0:
                return ROCK;
            case 1:
                return RAP;
            case 2:
                return COUNTRY;
            case 3:
                return POP;
            default:
                return null;
        }
    }

    /**
     * This method returns the <b><CODE>MusicGenre</CODE></b> corresponding to the passed in
     * <b><CODE>String</CODE></b> representation of a genre.
     *
     * @param stringGenre an <b><CODE>String</CODE></b> representing the desired <b><CODE>MusicGenre</CODE></b>.
     *
     * @return a <b><CODE>MusicGenre</CODE></b> corresponding to the passed in value.
     */
    public static MusicGenre fromString(String stringGenre) {
        switch (stringGenre) {
            case "Rock":
                return ROCK;
            case "Rap":
                return RAP;
            case "Country":
                return COUNTRY;
            case "Pop":
                return POP;
            default:
                return null;
        }
    }
}