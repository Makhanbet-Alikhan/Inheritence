package inStorage;

public class Checking implements inStorage {
    private String name;
    private double price;
    @Override
    public void inStorage() {
        System.out.println("Checking " + name + "...");
    }
    @Override
    public String getamount() {
        return "Amount of " + name + "is ";
    }
}
