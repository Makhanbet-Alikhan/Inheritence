package Database;
import model.ClothingItem;
import model.Pants;
import model.Shirt;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ClothDAO {
    public static List<ClothingItem> getAllpants() {
        List<ClothingItem> ClothList = new ArrayList<>();
        String sql = "SELECT * FROM clothingitem AS c WHERE c.type_cloth = 'Pants'";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return ClothList;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\n--- ALL Pants FROM DATABASE ---");
            while (resultSet.next()) {
                ClothingItem cloth = extractClothFromResultSet(resultSet);
                if (cloth != null) {
                    ClothList.add(cloth);
                }
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(" Select failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return ClothList;
    }

    public static List<ClothingItem> getAllshirt() {
        List<ClothingItem> ClothList = new ArrayList<>();
        String sql = "SELECT * FROM clothingitem AS c WHERE c.type_cloth = 'Shirt'";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return ClothList;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\n--- ALL shirt FROM DATABASE ---");
            while (resultSet.next()) {
                ClothingItem cloth = extractClothFromResultSet(resultSet);
                if (cloth != null) {
                    ClothList.add(cloth);
                }
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(" Select failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return ClothList;
    }

    public static List<ClothingItem> getAllCloth() {
        List<ClothingItem> ClothList = new ArrayList<>();
        String sql = "SELECT * FROM clothingitem ORDER BY clothid";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return ClothList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ClothingItem cloth = extractClothFromResultSet(resultSet);
                if (cloth != null) {
                    ClothList.add(cloth);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("Retrieved " + ClothList.size() + " staff from database");

        } catch (SQLException e) {
            System.out.println("Select all staff failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return ClothList;
    }

    public static ClothingItem getClothById(int clothid) {
        String sql = "SELECT * FROM clothingitem WHERE clothid = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, clothid);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ClothingItem clothingItem = extractClothFromResultSet(resultSet);

                resultSet.close();
                statement.close();

                if (clothingItem != null) {
                    System.out.println("Found staff with ID: " + clothid);
                }

                return clothingItem;
            }

            System.out.println("No staff found with ID: " + clothid);

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("Select by ID failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return null;
    }

    public static void insertPants(Pants clothingItem) {
        String sql = "INSERT INTO clothingitem (clothid, price, material, isavailable, count_of_item, type_cloth, withPockets, sleevesheight) VALUES (?, ?, ?, ?, ?, 'Pants', ?, NULL)";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            // Set parameters (? → actual values)
            statement.setInt(1, clothingItem.getClothId());
            statement.setDouble(2, clothingItem.getPrice());
            statement.setString(3, clothingItem.getMaterial());
            statement.setBoolean(4, clothingItem.isAvailable());
            statement.setInt(5, clothingItem.getCount_of_item());
            statement.setBoolean(6, clothingItem.getWithPocket());
            // Execute INSERT
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(" Staff inserted successfully!");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println(" Insert failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public static void insertShirt(Shirt clothingItem) {
        String sql = "INSERT INTO clothingitem (clothid, price, material, isavailable, count_of_item, type_cloth, withPockets, sleevesHeight) VALUES (?, ?, ?, ?, ?, 'Shirt', NULL, ?)";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            // Set parameters (? → actual values)
            statement.setInt(1, clothingItem.getClothId());
            statement.setDouble(2, clothingItem.getPrice());
            statement.setString(3, clothingItem.getMaterial());
            statement.setBoolean(4, clothingItem.isAvailable());
            statement.setInt(5, clothingItem.getCount_of_item());
            statement.setString(6, clothingItem.getSleevesHeight());
            // Execute INSERT
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(" Shirt inserted successfully!");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println(" Insert failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

    }

    public static boolean updatePants(Pants pants) {
        String sql = "UPDATE clothingitem SET price = ?, material = ?, " +
                "isavailable = ?,count_of_item = ?, withpockets = ? " +
                "WHERE clothid = ? AND type_cloth = 'Pants'";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, pants.getPrice());
            statement.setString(2, pants.getMaterial());
            statement.setBoolean(3, pants.isAvailable());
            statement.setInt(4, pants.getCount_of_item());
            statement.setBoolean(5, pants.getWithPocket());
            statement.setInt(6, pants.getClothId());
            // WHERE condition
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated > 0) {
                System.out.println(" Pants updated: Pants " + pants.getClothId());
                return true;
            }
        } catch (SQLException e) {
            System.out.println(" Update failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public static boolean updateShirt(Shirt pants) {
        String sql = "UPDATE clothingitem SET price = ?, material = ?, " +
                "isavailable = ?,count_of_item = ?, sleevesheight = ? " +
                "WHERE clothid = ? AND type_cloth = 'Shirt'";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, pants.getPrice());
            statement.setString(2, pants.getMaterial());
            statement.setBoolean(3, pants.isAvailable());
            statement.setInt(4, pants.getCount_of_item());
            statement.setString(5, pants.getSleevesHeight());
            statement.setInt(6, pants.getClothId());
            // WHERE condition
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated > 0) {
                System.out.println(" Shirt updated: shirts " + pants.getClothId());
                return true;
            }
        } catch (SQLException e) {
            System.out.println(" Update failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public static boolean DeleteStaff(int clothid){
        String sql = "DELETE FROM clothingitem WHERE clothid = ?";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, clothid);

            int rowDeleted = statement.executeUpdate();
            statement.close();

            if(rowDeleted > 0){
                System.out.println(" Cloth deleted (ID: " + clothid + ")");
                return true;
            }else{
                System.out.println(" Delete failed");
            }
        }catch (SQLException e){
            System.out.println("Delete failed");
            e.printStackTrace();
        }finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    private static ClothingItem extractClothFromResultSet(ResultSet resultSet) throws SQLException {
        int clothid = resultSet.getInt("clothid");
        double price = resultSet.getDouble("price");
        String material = resultSet.getString("material");
        boolean isavailable = resultSet.getBoolean("isavailable");
        int count = resultSet.getInt("count_of_item");
        String cloth_type = resultSet.getString("type_cloth");

        ClothingItem cloth = null;

        if ("Pants".equals(cloth_type)) {
            boolean withPockets = resultSet.getBoolean("withpockets");
            cloth = new Pants(clothid, price, material, isavailable, count, withPockets);

        } else if ("Shirt".equals(cloth_type)) {
            String sleevesheight = resultSet.getString("sleevesheight");
            cloth = new Shirt(clothid, price, material, isavailable, count, sleevesheight);
        }

        return cloth;
    }

    public static List<ClothingItem> searchByMaterial(String material) {
        List<ClothingItem> clothlist = new ArrayList<>();

        // ILIKE for case-insensitive search, % for partial match
        String sql = "SELECT * FROM clothingitem WHERE material ILIKE ? ORDER BY material";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return clothlist;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + material + "%");  // % = wildcard

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ClothingItem clothingItem = extractClothFromResultSet(resultSet);
                if (clothingItem != null) {
                    clothlist.add(clothingItem);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("Found " + clothlist.size() + " staff matching '" + material + "'");

        } catch (SQLException e) {
            System.out.println("Search by material failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return clothlist;
    }

    public static List<ClothingItem> searchByPriceRange(double minSalary, double maxSalary) {
        List<ClothingItem> clothlist = new ArrayList<>();

        String sql = "SELECT * FROM clothingitem WHERE price BETWEEN ? AND ? ORDER BY price DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return clothlist;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minSalary);
            statement.setDouble(2, maxSalary);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ClothingItem clothingItem = extractClothFromResultSet(resultSet);
                if (clothingItem != null) {
                    clothlist.add(clothingItem);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("Found " + clothlist.size() + " staff in salary range " +
                    minSalary + " - " + maxSalary);

        } catch (SQLException e) {
            System.out.println("Search by salary failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return clothlist;
    }

    public static List<ClothingItem> searchbyMinPrice(double minprice){
        List<ClothingItem> clothList = new ArrayList<>();
        String sql = "SELECT * FROM clothingitem WHERE ? <= price ORDER BY price DESC";

        Connection connection = DatabaseConnection.getConnection();

        if(connection == null) return clothList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minprice);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                ClothingItem clothingItem = extractClothFromResultSet(resultSet);
                if (clothingItem != null){
                    clothList.add(clothingItem);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("Found " + clothList.size() + " staff earning >= " + minprice);
        }catch (SQLException e){
            System.out.println("Search by min salary failed!");
            e.printStackTrace();
        }finally {
            DatabaseConnection.closeConnection(connection);
        }

        return clothList;
    }

    public static void DisplayAlCLoth(){
        List<ClothingItem> clothList = getAllCloth();

        System.out.println("\n========================================");
        System.out.println("   ALL STAFF FROM DATABASE");
        System.out.println("========================================");

        if (clothList.isEmpty()){
            System.out.println("No staff members in database.");
        }else{
            for (int i = 0; i < clothList.size(); i++){
                ClothingItem c = clothList.get(i);
                System.out.print((i + 1) + ". ");
                System.out.print("[" + c.getClothId() + "] ");
                System.out.println(c.toString());
            }
        }

        System.out.println("========================================\n");
    }

    public static void demonstratePolymorphysim() {
        List<ClothingItem> clothlist = getAllCloth();

        System.out.println("\n========================================");
        System.out.println("  POLYMORPHISM: Staff from Database");
        System.out.println("========================================");

        if(clothlist.isEmpty()){
            System.out.println("No staff to demonstrate.");
        }else{
            for (ClothingItem c: clothlist){
                c.available();
            }
        }

        System.out.println("========================================\n");
    }
}