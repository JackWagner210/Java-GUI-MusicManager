package tests;

import albumcollection.UserInterface;
import albumcollection.album.Album;
import albumcollection.album.CompactDisc;
import albumcollection.album.MusicGenre;
import org.junit.*;

import static org.junit.Assert.*;
import java.io.*;
import java.util.ArrayList;


/**
 * Class to test user interface method display albums
 * @author Jack Wagner
 */
public class UserInterfaceTest {

    private static UserInterface userInt;

    private static ArrayList<Album> albums = new ArrayList<>();

    private static CompactDisc cd = new CompactDisc( "a",  "a",  2007, MusicGenre.RAP);
    private static CompactDisc cd2 = new CompactDisc( "b",  "b", 2008, MusicGenre.COUNTRY);
    private static CompactDisc cd3 = new CompactDisc( "z",  "z", 2009, MusicGenre.POP);

    private static String separator = System.getProperty("line.separator");
    private static String expected = "   1. Title: a, Artist: a, Year: 2007, Genre: Rap" + separator +
                                     "   2. Title: b, Artist: b, Year: 2008, Genre: Country" + separator +
                                     "   3. Title: z, Artist: z, Year: 2009, Genre: Pop" + separator + separator;

    private static PrintStream originalOut = System.out;
    private static OutputStream os = new ByteArrayOutputStream();
    private static PrintStream ps = new PrintStream(os);

    /**
     * Before tests, inits new userint
     */
    @BeforeClass
    public static void createEnvironment() {
        userInt = new UserInterface();
    }

    /**
     * After tests are finished, closes userint object.
     */
    @AfterClass
    public static void clearEnvironment(){
        userInt = null;
    }

    /**
     * Init. before tests
     */
    @Before
    public void setUp(){
        System.setOut(ps);
    }

    /**
     * Resets after tests
     */
    @After
    public void reset(){
        System.setOut(originalOut);
    }

    /**
     * Test to capture display output and match it with expdcted output
     * @throws Exception exception
     */
    @Test
    public void testDisplayAlbums() throws Exception{

        userInt.displayAlbums(albums, true);
        assertEquals("", os.toString());

        albums.add(cd);
        albums.add(cd2);
        albums.add(cd3);

        userInt.displayAlbums(albums, true);
        assertEquals(expected, os.toString());

        albums.remove(cd);
        albums.remove(cd2);
        albums.remove(cd3);

        userInt.displayAlbums(albums, false);
        assertEquals(expected, os.toString());
    }
}