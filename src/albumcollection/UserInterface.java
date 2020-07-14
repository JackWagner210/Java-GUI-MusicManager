package albumcollection;

import albumcollection.album.*;
import albumcollection.album.track.Track;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private static Scanner consoleIn = new Scanner(System.in);

    public void displayMainMenu() {

        int mainUserSelection = 0;
        System.out.println("");

        while (mainUserSelection != 6) {

            System.out.println(
                    "Select an operation...\n" +
                            "   1. Display list of all albums.\n" +
                            "   2. Add a new album to collection.\n" +
                            "   3. Search for an album.\n" +
                            "   4. Delete an album from collection.\n" +
                            "   5. Play album.\n" +
                            "   6. Exit.\n"
            );

            System.out.print("Enter a selection: ");
            mainUserSelection = Integer.parseInt(consoleIn.nextLine());
            System.out.println("");

            switch (mainUserSelection) {

                case 1:
                    displayAlbums(AlbumCollectionManager.getAlbumCollection(), false);
                    break;
                case 2:
                    addNewAlbumToCollection();
                    break;
                case 3:
                    searchForAlbum();
                    break;
                case 4:
                    removeAlbumFromCollection();
                    break;
                case 5:
                    if (!AlbumCollectionManager.isAlbumCollectionEmpty()) {
                        playAlbum();
                    } else {
                        System.out.println("No albums in collection to play.\n");
                    }
                    break;
                case 6:
                    AlbumCollectionManager.saveAlbumCollectionToDatabase();
                    break;
                default:
                    break;
            }
        }
    }

    public void displayAlbums(ArrayList<Album> albums, boolean numbered) {

        for (int i = 0; i < albums.size(); i++) {

            String numberingString;

            if (numbered) {
                numberingString = "   " + Integer.toString(i + 1) + ". ";
            } else {
                numberingString = "";
            }

            Album album = albums.get(i);
            System.out.println(numberingString + album.toString());
        }

        if (albums.size() > 0) {
            System.out.println("");
        }
    }

    private void addNewAlbumToCollection() {

        boolean isCompactDisc = false;
        boolean isValidType = false;

        while (!isValidType) {

            System.out.println(
                    "Select the type of album...\n" +
                            "   1. Compact Disc (CD)\n" +
                            "   2. Cassette Tape\n");

            String typeString = consoleIn.nextLine();
            System.out.println("");

            if (typeString.matches("[1-2]")) {

                switch (Integer.parseInt(typeString)) {
                    case 1:
                        isCompactDisc = true;
                        break;
                    case 2:
                        break;
                }

                isValidType = true;
            }
        }

        System.out.println("Enter title of album: ");
        String title = consoleIn.nextLine();
        System.out.println("");

        System.out.println("Enter name of artist: ");
        String artist = consoleIn.nextLine();
        System.out.println("");

        boolean isValidReleaseYear = false;
        int releaseYear = 0;

        while (!isValidReleaseYear) {

            System.out.println("Enter release year of album: ");
            String releaseYearString = consoleIn.nextLine();
            System.out.println("");

            if (releaseYearString.matches("\\d{4}")) {
                releaseYear = Integer.parseInt(releaseYearString);
                isValidReleaseYear = true;
            }
        }

        boolean isValidGenre = false;
        MusicGenre genre = null;

        while (!isValidGenre) {

            System.out.println(
                    "Select the genre of album...\n" +
                            "   1. Rock\n" +
                            "   2. Rap\n" +
                            "   3. Country\n" +
                            "   4. Pop\n");

            String genreString = consoleIn.nextLine();
            System.out.println("");

            if (genreString.matches("[1-4]")) {

                switch (Integer.parseInt(genreString)) {
                    case 1:
                        genre = MusicGenre.ROCK;
                        break;
                    case 2:
                        genre = MusicGenre.RAP;
                        break;
                    case 3:
                        genre = MusicGenre.COUNTRY;
                        break;
                    case 4:
                        genre = MusicGenre.POP;
                        break;
                }

                isValidGenre = true;
            }
        }

        if (isCompactDisc) {

            ArrayList<Track> tracks = new ArrayList<Track>();
            for (int i = 0; i < 10; i++) {
                Track track = new Track(180.0);
                tracks.add(track);
            }

            Album album = new CompactDisc(title, artist, releaseYear, genre, tracks);
            AlbumCollectionManager.addAlbumToCollection(album);

        } else {

            Album album = new CassetteTape(title, artist, releaseYear, genre, 1800.0);
            AlbumCollectionManager.addAlbumToCollection(album);
        }
    }

    private void removeAlbumFromCollection() {

        System.out.println("Enter title or artist album to remove: ");
        String searchTerm = consoleIn.nextLine();
        System.out.println("");

        ArrayList<Album> searchResults = AlbumCollectionManager.albumsMatchingSearchTerm(searchTerm);

        if (searchResults.size() > 0) {

            System.out.println("Select album to remove...");
            displayAlbums(searchResults, true);
            int indexToRemove = Integer.parseInt(consoleIn.nextLine()) - 1;

            Album albumToRemove = searchResults.get(indexToRemove);
            AlbumCollectionManager.removeAlbumFromCollection(albumToRemove);

            System.out.println("");

        } else {

            System.out.println("No albums matching search.\n");
        }
    }

    private void searchForAlbum() {

        System.out.println("Search for: ");
        String searchTerm = consoleIn.nextLine();
        System.out.println("");

        ArrayList<Album> searchResults = AlbumCollectionManager.albumsMatchingSearchTerm(searchTerm);

        if (searchResults.size() > 0) {
            displayAlbums(searchResults, false);
        } else {
            System.out.println("No albums matching search.\n");
        }
    }

    private void playAlbum() {

        Album album = null;

        while (album == null) {

            System.out.println("Enter title or artist album to play: ");
            String searchTerm = consoleIn.nextLine();
            System.out.println("");

            ArrayList<Album> searchResults = AlbumCollectionManager.albumsMatchingSearchTerm(searchTerm);

            if (searchResults.size() > 0) {

                System.out.println("Select album to play...");
                displayAlbums(searchResults, true);
                int indexToPlay = Integer.parseInt(consoleIn.nextLine()) - 1;

                album = searchResults.get(indexToPlay);

                System.out.println("");

            } else {

                System.out.println("No albums matching search.\n");
            }
        }

        boolean isCompactDisc;

        if (album instanceof CompactDisc) {
            isCompactDisc = true;
        } else {
            isCompactDisc = false;
        }

        int mainUserSelection = 0;

        while (mainUserSelection != 6) {

            System.out.println(
                    "Select an operation...\n" +
                    "   1. Play.\n" +
                    "   2. Fast Forward.\n" +
                    "   3. Rewind."
            );

            if (isCompactDisc) {

                System.out.println(
                        "   4. Skip To Next Track.\n" +
                        "   5. Skip To Previous Track.\n" +
                        "   6. Exit Player.\n"
                );

            } else {

                System.out.println("   4. Exit Player.\n");
            }

            System.out.print("Enter a selection: ");
            mainUserSelection = Integer.parseInt(consoleIn.nextLine());
            System.out.println("");

            switch (mainUserSelection) {

                case 1:
                    album.play();
                    break;
                case 2:
                    album.fastForward();
                    break;
                case 3:
                    album.rewind();
                    break;
                case 4:
                    if (isCompactDisc) {
                        ((CompactDisc) album).skipToNext();
                    } else {
                        mainUserSelection = 6;
                    }
                    break;
                case 5:
                    if (isCompactDisc) {
                        ((CompactDisc) album).skipToPrevious();
                    }
                    break;
                case 6:
                    break;
                default:
                    break;
            }
        }
    }

}
