package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import Database.*;
import exception.InvalidInputException;
import model.*;
import java.util.List;

public class ClothStoreMenu implements Menu{
    private Scanner scanner = new Scanner(System.in);
    List<ClothingItem> all = ClothDAO.getAllCloth();
    @Override
    public void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         MAIN MENU - Week 8             ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("┌─ STAFF MANAGEMENT ─────────────────────┐");
        System.out.println("│ 1. Add Shirts                          │");
        System.out.println("│ 2. Add Pants                           │");
        System.out.println("│ 3. View All Cloth                      │");
        System.out.println("│ 4. View Pants Only                     │");
        System.out.println("│ 5. View Shirts Only                    │");
        System.out.println("│ 6. Update Cloth                        │");
        System.out.println("│ 7. Delete Cloth                        │");
        System.out.println("├─ SEARCH & FILTER ──────────────────────┤");
        System.out.println("│ 8. Search by material                  │");
        System.out.println("│ 9. Search by Price Range               │");
        System.out.println("│10. High-Price Cloth (Price >= X)       │");
        System.out.println("├─ DEMO & OTHER ─────────────────────────┤");
        System.out.println("│11. Polymorphism Demo                   │");
        System.out.println("│ 0. Exit                                │");
        System.out.println("└────────────────────────────────────────┘");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("\n👉 Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addShirt();
                        break;
                    case 2:
                        addPants();
                        break;
                    case 3:
                        viewAllItems();
                        break;
                    case 4:
                        viewAllPants();
                        break;
                    case 5:
                        viewAllShirts();
                        break;
                    case 6:
                        updateCloth();
                        break;
                    case 7:
                        deleteStaff();
                        break;
                    case 8:
                        searchByMaterial();
                        break;
                    case 9:
                        searchBySalaryRange();
                        break;
                    case 10:
                        searchHighPaidStaff();
                        break;
                    case 11:
                        demonstratePolymorphism();
                        break;
                    case 0:
                        running = false;
                        System.out.println("\n╔════════════════════════════════════════╗");
                        System.out.println("║  Thank you for using our system!      ║");
                        System.out.println("║  Goodbye! 👋                          ║");
                        System.out.println("╚════════════════════════════════════════╝");
                        break;
                    default:
                        System.out.println("❌ Invalid choice! Please select 0-11.");
                }

                if (choice != 0) {
                    pressEnterToContinue();
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Error: Please enter a valid number!");
                scanner.nextLine();
                pressEnterToContinue();
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine();
                pressEnterToContinue();
            }
        }

        scanner.close();
    }

    private void viewAllItems() {
        ClothDAO.DisplayAlCLoth();
    }

    private void viewAllShirts() {
        System.out.println("\n========================================");
        System.out.println(" ALL Shirts (POLYMORPHIC LIST)");
        System.out.println("========================================");
        List<ClothingItem> all = ClothDAO.getAllshirt();
        if (all.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        System.out.println("Total item: " + all.size());
        System.out.println();
        for (int i = 0; i < all.size(); i++) {
            ClothingItem s = all.get(i);
            System.out.println((i + 1) + ". " + s);
            Shirt shirt = (Shirt) s; // Downcast
            shirt.getSleevesHeight();
            System.out.println();
        }
    }

    private void viewAllPants() {
        System.out.println("\n========================================");
        System.out.println(" ALL Pants (POLYMORPHIC LIST)");
        System.out.println("========================================");
        List<ClothingItem> all = ClothDAO.getAllpants();
        if (all.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        System.out.println("Total item: " + all.size());
        System.out.println();
        for (int i = 0; i < all.size(); i++) {
            ClothingItem s = all.get(i);
            System.out.println((i + 1) + ". " + s);
            Pants shirt = (Pants) s; // Downcast
            shirt.getWithPocket();
            System.out.println();
        }
    }

    private void addShirt() {
        try {
        System.out.println("\n--- ADD Shirt ---");
        System.out.print("│ Enter Shirt ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
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
        Shirt shirt = new Shirt(id, price, material, available, amount, height);
        ClothDAO.insertShirt(shirt);
        System.out.println("\n Shirt added successfully!");}
        catch (InvalidInputException e){
            System.out.println("X" + e.getMessage());
        }
    }

    private void addPants() {
        try {
        System.out.println("\n--- ADD Pants ---");
        System.out.print("│ Enter Shirt ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
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
        Pants pants = new Pants(id, price, material, available, amount, pocket);
        ClothDAO.insertPants(pants);
        System.out.println("\n Pants added successfully!");}
        catch (IllegalArgumentException e){
            System.out.println("X" + e.getMessage());
        }
    }

    private void updateCloth() {
        System.out.print("Enter Cloth ID to update: ");
        int staffId = scanner.nextInt();
        scanner.nextLine();
        // 1. Load current data from database
        ClothingItem existingCLoth = ClothDAO.getClothById(staffId);
        if (existingCLoth == null) {
            System.out.println(" No CLoth found with ID: " + staffId);
            return;
        }
        // 2. Display current info
        System.out.println("Current Info:");
        System.out.println(existingCLoth.toString());
        // 3. Get new values (press Enter to keep current)
        System.out.print("New Price [" + existingCLoth.getPrice() + "]: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();
        if (newPrice == 0) {
            newPrice = existingCLoth.getPrice(); // Keep current
        }
        System.out.print("New Material [" + existingCLoth.getMaterial() + "]: ");
        String materialInput = scanner.nextLine();
        String newMaterial = materialInput.trim().isEmpty() ?
                existingCLoth.getMaterial() : materialInput;
        System.out.print("New Availability [" + existingCLoth.isAvailable() + "]: ");
        Boolean isAvailableInput = scanner.nextBoolean();
        System.out.print("New Count [" + existingCLoth.getCount_of_item() + "]: ");
        int newCount = scanner.nextInt();
        scanner.nextLine();
        if (newCount == 0) {
            newCount = existingCLoth.getCount_of_item(); // Keep current
        }
        // 4. Update based on type
        if (existingCLoth instanceof Pants) {
            System.out.print("New withPocket [" + ((Pants) existingCLoth).getWithPocket() + "]: ");
            Boolean newPocket = scanner.nextBoolean();
            Pants updatedPants = new Pants(staffId, newPrice, newMaterial, isAvailableInput, newCount, newPocket);
            ClothDAO.updatePants(updatedPants);
        }

        if (existingCLoth instanceof Shirt) {
            System.out.print("New Sleeves Height [" + ((Shirt) existingCLoth).getSleevesHeight() + "]: ");
            String newPocket = scanner.nextLine();
            Shirt updatedShirts = new Shirt(staffId, newPrice, newMaterial, isAvailableInput, newCount, newPocket);
            ClothDAO.updateShirt(updatedShirts);
        }
    }

    private void deleteStaff() {
        System.out.print("Enter Cloth ID to delete: ");
        int clothId = scanner.nextInt();
        scanner.nextLine();
        // 1. First, load and show who will be deleted
        ClothingItem cloth = ClothDAO.getClothById(clothId);
        if (cloth == null) {
            System.out.println(" No staff found with ID: " + clothId);
            return;
        }
        // 2. Display staff details
        System.out.println("Staff to delete:");
        System.out.println(cloth.toString());
        // 3. Ask for confirmation
        System.out.print(" Are you sure? (y/n): ");
        String confirmation = scanner.nextLine();
        // 4. Delete only if confirmed
        if (confirmation.equalsIgnoreCase("y")) {
            ClothDAO.DeleteStaff(clothId);
        } else {
            System.out.println(" Deletion cancelled.");
        }
    }

    private void searchByMaterial() {
        System.out.println("\n┌─ SEARCH BY Material ───────────────────────┐");
        System.out.print("│ Enter material to search: ");
        String material = scanner.nextLine();
        System.out.println("└────────────────────────────────────────┘");

        List<ClothingItem> results = ClothDAO.searchByMaterial(material);

        displaySearchResults(results, "Search: '" + material + "'");
    }

    private void searchBySalaryRange() {
        try {
            System.out.println("\n┌─ SEARCH BY PRICE RANGE ───────────────┐");
            System.out.print("│ Enter minimum price: ");
            double minSalary = scanner.nextDouble();

            System.out.print("│ Enter maximum price: ");
            double maxSalary = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("└────────────────────────────────────────┘");

            List<ClothingItem> results = ClothDAO.searchByPriceRange(minSalary, maxSalary);

            displaySearchResults(results, "Price: " + minSalary + " - " + maxSalary + " KZT");

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid number!");
            scanner.nextLine();
        }
    }

    private void searchHighPaidStaff() {
        try {
            System.out.println("\n┌─ HIGH-PRICE STAFF ──────────────────────┐");
            System.out.print("│ Enter minimum price: ");
            double minSalary = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("└────────────────────────────────────────┘");

            List<ClothingItem> results = ClothDAO.searchbyMinPrice(minSalary);

            displaySearchResults(results, "Price >= " + minSalary + " KZT");

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid number!");
            scanner.nextLine();
        }
    }

    private void displaySearchResults(List<ClothingItem> results, String criteria) {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         SEARCH RESULTS                ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Criteria: " + criteria);
        System.out.println("─────────────────────────────────────────");

        if (results.isEmpty()) {
            System.out.println("📭 No cloth found matching criteria.");
        } else {
            for (int i = 0; i < results.size(); i++) {
                ClothingItem s = results.get(i);
                System.out.print((i + 1) + ". ");
                System.out.println(s.toString());
            }
            System.out.println("─────────────────────────────────────────");
            System.out.println("Total Results: " + results.size());
        }
    }


    private void demonstratePolymorphism() {
        ClothDAO.demonstratePolymorphysim();
    }

    // ========================================
    // HELPER METHOD
    // ========================================

    private void pressEnterToContinue() {
        System.out.println("\n[Press Enter to continue...]");
        scanner.nextLine();
    }
}
