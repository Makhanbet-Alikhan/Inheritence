package Database;
import model.ClothingItem;
import model.Pants;
import model.Shirt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ClothDAO {
    public void getAllpants() {
        String sql = "SELECT * FROM pants";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\n--- ALL pants FROM DATABASE ---");
            while (resultSet.next()) {
                int id = resultSet.getInt("clothid");
                int price = resultSet.getInt("price");
                String material = resultSet.getString("material");
                boolean isAvailable = resultSet.getBoolean("isavailable");
                int count = resultSet.getInt("count_of_item");
                boolean withpockets = resultSet.getBoolean("withpockets");
                System.out.println("ID: " + id);
                System.out.println("price: " + price);
                System.out.println("material: " + material);
                System.out.println("Available: " + isAvailable);
                System.out.println("count: " + count);
                System.out.println("withPockets: " + withpockets);
                System.out.println("---");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(" Select failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public void getAllshirt() {
        String sql = "SELECT * FROM shirt";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\n--- ALL shirt FROM DATABASE ---");
            while (resultSet.next()) {
                int id = resultSet.getInt("clothid");
                int price = resultSet.getInt("price");
                String material = resultSet.getString("material");
                boolean isAvailable = resultSet.getBoolean("isavailable");
                int count = resultSet.getInt("count_of_item");
                String Sleeves = resultSet.getString("sleevesheight");
                System.out.println("ID: " + id);
                System.out.println("price: " + price);
                System.out.println("material: " + material);
                System.out.println("Available: " + isAvailable);
                System.out.println("count: " + count);
                System.out.println("Sleeves Height: " + Sleeves);
                System.out.println("---");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(" Select failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public void insertPants(Pants clothingItem) {
        String sql = "INSERT INTO Pants (clothid, price, material, isavailable, count_of_item, withPockets) VALUES (?, ?, ?, ?, ?, ?)";
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

    public void insertShirt(Shirt clothingItem) {
        String sql = "INSERT INTO Shirt (clothid, price, material, isavailable, count_of_item, sleevesHeight) VALUES (?, ?, ?, ?, ?, ?)";
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
}