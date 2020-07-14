package albumcollection;

public class MainApp {

    public static void main(String[] args) {

        AlbumCollectionManager.loadAlbumCollectionFromDatabase();

        UserInterface userInterface = new UserInterface();
        userInterface.displayMainMenu();
    }
}