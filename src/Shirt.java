import java.util.Scanner;

public class Shirt extends ClothingItem{

    private String sleevesHeight;
    private static Scanner scanner = new Scanner(System.in);

    public Shirt(int clothId, double price, String material, boolean isAvailable, int count_of_item, String sleevesHeight){
        super(clothId, price, material, isAvailable, count_of_item);
        setSleevesHeight(sleevesHeight);
    }

    public String getSleevesHeight() {
        return sleevesHeight;
    }

    public void setSleevesHeight(String sleevesHeight) {
        if (sleevesHeight != null && !sleevesHeight.isEmpty()){
            this.sleevesHeight = sleevesHeight;}
        else{
            System.out.print("Sleeves are can't be empty! Enter again: ");
            setSleevesHeight(scanner.next());
        }
    }

    @Override
    public void available() {
        if(isAvailable){
            System.out.println("Shirt" + " is available.");
        }else{
            System.out.println("Shirt" + " is unavailable.");
        }
    }

    @Override
    public String getCategory() {
        return "Shirt";
    }

    public void Sleeves() {
        System.out.println("Sleeves are " + sleevesHeight);
    }

    public void ShirtMethod(){
        System.out.println("Shirt is cool, it has been created for a chest");
    }

    @Override
    public String toString(){
        return super.toString() + " sleeves=" + sleevesHeight;
    }
}
