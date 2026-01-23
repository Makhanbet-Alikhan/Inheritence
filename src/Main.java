import Menu.Menu;
import Menu.ClothStoreMenu;
import java.sql.Connection;
import java.sql.DriverManager;
public class Main {
    public static void main(String[] args) {
        Menu menu = new ClothStoreMenu();
        menu.run();
    }
}