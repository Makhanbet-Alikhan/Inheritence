import Database.ClothDAO;
import model.Shirt;

public class TestConnection {
    public static void main(String[] args) {
        ClothDAO dao = new ClothDAO();
        dao.getAllpants();
    }
}