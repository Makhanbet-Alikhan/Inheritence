import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println(" Clothing Store System");
        System.out.println("========================================");
        System.out.println("1. Add Item (General)");
        System.out.println("2. Add Shirt");
        System.out.println("3. Add Pants");
        System.out.println("4. View All Items (Polymorphic)\n");
        System.out.println("5. View all Items availability\n");
        System.out.println("6. View Shirts Only");
        System.out.println("7. View Pants Only");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
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

    private static void ViewAvailability(){
        for(ClothingItem s : allItems){
            s.available();
        }
    }

    private static void addShirt() {
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
        // Create Chef but store as Staff (polymorphism!)
        ClothingItem cloth = new Shirt(allItems.size(), price, material, available, amount, height);
        allItems.add(cloth);
        System.out.println("\n Shirt added successfully!");
    }

    private static void addPants() {
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
        ClothingItem cloth = new Pants(allItems.size(), price, material, available, amount, pocket);
        allItems.add(cloth);
        System.out.println("\n Pants added successfully!");
    }

    private static void addItem() {
        System.out.println("\n--- ADD Item General ---");
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
        // Create Chef but store as Staff (polymorphism!)
        ClothingItem cloth = new ClothingItem(allItems.size(), price, material, available, amount);
        allItems.add(cloth);
        System.out.println("\n Item added successfully!");
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

    private static void viewShirts() {
        System.out.println("\n========================================");
        System.out.println(" Shirts ONLY");
        System.out.println("========================================");
        int ShirtCount = 0;
        for (ClothingItem s : allItems) {
            if (s instanceof Shirt) {
                ShirtCount++;// Filter by type
                Shirt shirt = (Shirt) s;
                System.out.println("price" + shirt.getPrice());
                System.out.println(" Material: " + shirt.getMaterial());
                System.out.println(" Sleeves Height: " + shirt.getSleevesHeight());
                shirt.Sleeves();
                System.out.println();
            }
        }
        if (ShirtCount == 0) {
            System.out.println("No shirts found.");
        }
    }

    private static void viewPants() {
        System.out.println("\n========================================");
        System.out.println(" Pants ONLY");
        System.out.println("========================================");
        int PantsCount = 0;
        for (ClothingItem s : allItems) {
            if (s instanceof Pants) {
                PantsCount++;// Filter by type
                Pants pants = (Pants) s;
                System.out.println("price" + pants.getPrice());
                System.out.println(" Material: " + pants.getMaterial());
                System.out.println(" Does it has pockets?: " + pants.getWithPocket());
                pants.Pockets();
                System.out.println();
            }
        }
        if (PantsCount == 0) {
            System.out.println("No Pants found.");
        }
    }


    private static ArrayList<ClothingItem> allItems = new ArrayList<>();
    public static void main(String[] args){
        allItems.add( new ClothingItem(allItems.size(), 100, "Silk", false, 4));
        allItems.add( new Shirt(allItems.size(), 500, "Wool", true, 3, "long"));
        allItems.add( new Pants(allItems.size(), 300, "Silk", true, 2, true));

    }
}