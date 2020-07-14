package tests;

import albumcollection.AlbumCollectionManager;
import albumcollection.album.CompactDisc;
import albumcollection.album.Album;
import albumcollection.album.MusicGenre;
import org.junit.*;

import static org.junit.Assert.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Class to run album collection manager tests
 * @author Jack Wagner
 */
public class AlbumCollectionManagerTest {

    private static AlbumCollectionManager manager;
    private static CompactDisc cd = new CompactDisc( "a",  "a",  2007, MusicGenre.RAP);
    private static CompactDisc cd2 = new CompactDisc( "b",  "b", 2008, MusicGenre.COUNTRY);
    private static CompactDisc cd3 = new CompactDisc( "z",  "z", 2009, MusicGenre.POP);

    /**
     * Create environment before tests are run
     */
    @BeforeClass
    public static void createEnvironment(){
        manager = new AlbumCollectionManager();
    }

    /**
     * Clear environment after tests
     */
    @AfterClass
    public static void clearEnvironment(){
        manager = null;
    }

    /**
     * Test adding album to collection by comparing against an expected result
     */
    @Test
    public void testAddAlbumToCollection(){

        manager.addAlbumToCollection(cd);
        manager.addAlbumToCollection(cd2);
        ArrayList<Album> a = manager.getAlbumCollection();
        ArrayList<Album> expected = new ArrayList<>(2);

        expected.add(cd);
        expected.add(cd2);

        assertEquals(expected, a);
    }
    /**
     * Test remove album from collection by comparing against an expected result
     */
    @Test
    public void testRemoveAlbumFromCollection(){

        manager.addAlbumToCollection(cd);
        manager.addAlbumToCollection(cd2);
        manager.addAlbumToCollection(cd3);
        manager.removeAlbumFromCollection(cd2);

        ArrayList<Album> expected = new ArrayList<>();
        ArrayList<Album> a = manager.getAlbumCollection();

        expected.add(cd);
        expected.add(cd2);
        expected.add(cd3);
        expected.remove(cd2);

        assertEquals(expected, a);
    }
    /**
     * Test is album empty by comparing against an expected result
     */
    @Test
    public void testIsAlbumCollectionEmpty(){
        assertEquals(false, manager.isAlbumCollectionEmpty());

        ArrayList<Album> expected = new ArrayList<>();
        expected.add(cd2);
        manager.removeAlbumFromCollection(cd);

        ArrayList<Album> a = manager.getAlbumCollection();
        assertEquals(a, expected);

        manager.removeAlbumFromCollection(cd2);
        assertEquals(true, manager.isAlbumCollectionEmpty());

    }
    /**
     * Test get album collcetion by comparing against an expected result
     */
    @Test
    public void testGetAlbumCollection(){
        manager.addAlbumToCollection(cd);
        ArrayList<Album> a = manager.getAlbumCollection();
        ArrayList<Album> expected = new ArrayList<>();

        expected.add(cd);
        assertEquals(expected, a);

        manager.removeAlbumFromCollection(cd);
    }
    /**
     * tests search result function by comparing with expected results
     */
    @Test
    public void testAlbumsMatchingSearchTerm(){

        ArrayList<Album> expected = new ArrayList<>();
        ArrayList<Album> actual = manager.albumsMatchingSearchTerm("");

        assertEquals(expected, actual);

        manager.addAlbumToCollection(cd);
        manager.addAlbumToCollection(cd2);
        manager.addAlbumToCollection(cd3);
        expected.add(cd);
        actual = manager.albumsMatchingSearchTerm("a");
        assertEquals(expected, actual);
        manager.removeAlbumFromCollection(cd);
        manager.removeAlbumFromCollection(cd2);
        manager.removeAlbumFromCollection(cd3);



    }

    /**
     * Tests album creation by asserting equals with a default album object
     */
    @Ignore
    public void testCreateAlbumFrom(){
        Album a = manager.createAlbumFrom("CompactDisc", "a", "a", 2007, "Rap");
        assertEquals(cd, a);

    }
}