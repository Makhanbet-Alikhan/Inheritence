package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import exception.InvalidInputException;
import model.*;

public class ClothStoreMenu implements Menu{
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<ClothingItem> allItems = new ArrayList<>();
    @Override
    public void displayMenu() {
        System.out.println("\n========================================");
        System.out.println(" Clothing Store System");
        System.out.println("========================================");
        System.out.println("1. Add Shirt");
        System.out.println("2. Add Pants");
        System.out.println("3. View All Items (Polymorphic)");
        System.out.println("4. View all Items availability");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1: addShirt(); break;
                    case 2: addPants(); break;
                    case 3: viewAllItems(); break;
                    case 4: demonstratePolymorphism(); break;
                    case 0: running = false; break;
                    default: System.out.println("Invalid!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void viewAllItems() {
        System.out.println("\n========================================");
        System.out.println(" ALL Items (POLYMORPHIC LIST)");
        System.out.println("========================================");
        if (allItems.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        System.out.println("Total item: " + allItems.size());
        System.out.println();
        for (int i = 0; i < allItems.size(); i++) {
            ClothingItem s = allItems.get(i);
            System.out.println((i + 1) + ". " + s);
            if (s instanceof Shirt) {
                Shirt shirt = (Shirt) s; // Downcast
                shirt.Sleeves();
            } else if (s instanceof Pants) {
                Pants pants = (Pants) s; // Downcast
                pants.Pockets();
            }
            System.out.println();
        }
    }

    private static void addShirt() {
        try {
        System.out.println("\n--- ADD Shirt ---");
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Material: ");
        String material = scanner.next();
        System.out.print("Is Available: ");
        boolean available = scanner.nextBoolean();
        System.out.print("Enter amount: ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Sleeves' height: ");
        String height = scanner.next();
        Shirt shirt = new Shirt(allItems.size(), price, material, available, amount, height);
        allItems.add(shirt);
        System.out.println("\n Shirt added successfully!");}
        catch (InvalidInputException e){
            System.out.println("X" + e.getMessage());
        }
    }

    private static void addPants() {
        try {
        System.out.println("\n--- ADD Pants ---");
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Material: ");
        String material = scanner.next();
        System.out.print("Is Available: ");
        boolean available = scanner.nextBoolean();
        System.out.print("Enter amount: ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Have Pocket: ");
        boolean pocket = scanner.nextBoolean();
        // Create Chef but store as Staff (polymorphism!)
        Pants pants = new Pants(allItems.size(), price, material, available, amount, pocket);
        allItems.add(pants);
        System.out.println("\n Pants added successfully!");}
        catch (IllegalArgumentException e){
            System.out.println("X" + e.getMessage());
        }
    }

    private static void demonstratePolymorphism() {
        System.out.println("\n========================================");
        System.out.println(" POLYMORPHISM DEMONSTRATION");
        System.out.println("========================================");
        System.out.println("Calling available() on all staff members:");
        System.out.println();
        for (ClothingItem s : allItems) {
            s.available(); // Polymorphism: Same method, different behavior!
        }
        System.out.println();
        System.out.println(" Notice: Same method name (available), different output!");
        System.out.println(" This is POLYMORPHISM in action!");
    }
}
