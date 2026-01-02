import java.util.Scanner;

public class Shirt extends ClothingItem{
    private String material;

    private static Scanner scanner = new Scanner(System.in);

    public Shirt(int clothId, double price, boolean isAvailable, int count_of_item, String category, String material){
        super(clothId, price, isAvailable, count_of_item);
        setMaterial(material);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if (material != null && !material.isEmpty()){
            this.material = material;}
        else{
            System.out.print("Material can't be empty! Enter again: ");
            setMaterial(scanner.next());
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

    public void ShirtMethod() {
        System.out.println("Shirt is cool, and its types could be various");
    }

    @Override
    public String toString() {
        return super.toString() + " | material: " + material;
    }




}
