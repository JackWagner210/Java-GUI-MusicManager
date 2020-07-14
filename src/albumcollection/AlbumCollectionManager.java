package albumcollection;

import albumcollection.album.Album;
import albumcollection.album.CassetteTape;
import albumcollection.album.CompactDisc;
import albumcollection.album.MusicGenre;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AlbumCollectionManager {

    private static final String DATABASE_FILE_NAME = "database.dat";

    private static ArrayList<Album> albumCollection = new ArrayList<Album>();

    public static Album createAlbumFrom(String type, String title, String artist, int year, String genre) {
        switch (type) {
            case "Compact Disc":
                return new CompactDisc(title, artist, year, MusicGenre.fromString(genre));
            case "Cassette Tape":
                return new CassetteTape(title, artist, year, MusicGenre.fromString(genre), 1800.0);
            default:
                return null;
        }
    }

    public static ArrayList<Album> getAlbumCollection() {
        return (ArrayList<Album>) albumCollection.clone();
    }

    public static boolean isAlbumCollectionEmpty() {
        return albumCollection.isEmpty();
    }

    public static void addAlbumToCollection(Album album) {
        albumCollection.add(album);
        albumCollection.sort(Album::compareTo);
    }

    public static void removeAlbumFromCollection(Album album) {
        albumCollection.remove(album);
        albumCollection.sort(Album::compareTo);
    }

    public static ArrayList<Album> albumsMatchingSearchTerm(String searchTerm) {

        ArrayList<Album> results = new ArrayList<Album>();

        for (Album album : albumCollection) {

            String searchTermLowerCase = searchTerm.toLowerCase();
            String artistLowerCase = album.getArtist().toLowerCase();
            String titleLowerCase = album.getTitle().toLowerCase();

            if (artistLowerCase.contains(searchTermLowerCase) || titleLowerCase.contains(searchTermLowerCase)) {
                results.add(album);
            }
        }

        return results;
    }

    public static void loadAlbumCollectionFromDatabase() {

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATABASE_FILE_NAME));
            albumCollection = (ArrayList<Album>) in.readObject();
            in.close();
            albumCollection.sort(Album::compareTo);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void saveAlbumCollectionToDatabase() {

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATABASE_FILE_NAME));
            out.writeObject(albumCollection);
            out.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


}