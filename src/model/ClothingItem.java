package model;

import java.util.Scanner;

public abstract class ClothingItem {
    protected int ClothId;
    protected double price;
    protected String material;
    protected boolean isAvailable;
    protected int count_of_item;

    private static Scanner scanner = new Scanner(System.in) ;

    public ClothingItem(int clothId, double price, String material, boolean isAvailable, int count_of_item) {
        setPrice(price);
        this.ClothId = clothId;
        setMaterial(material);
        this.isAvailable = isAvailable;
        setCount_of_item(count_of_item);
    }

    public  ClothingItem(){}

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0){
            this.price = price;
        }else{
            System.out.print("Price value is not correct! Enter again: ");
            setPrice(scanner.nextDouble());
        }

    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getCount_of_item() {
        return count_of_item;
    }

    public void setCount_of_item(int count_of_item) {
        if (count_of_item > 0){
            this.count_of_item = count_of_item;
        }else{
            System.out.print("Count of item less than 1! Enter again: ");
            setCount_of_item(scanner.nextInt());
        }
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

    public int getClothId() {
        return ClothId;
    }

    public void setClothId(int clothId) {
        ClothId = clothId;
    }

    public abstract void available();

    public abstract void getCategory();

    public void applyDiscount(double discount){
        this.price = price - (price * (discount / 100));
    }

    @Override
    public String toString() {
        return "ClothingItem{" +
                "ID=" + ClothId +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", remain:" + count_of_item +
                ", materal:" + material +
                '}';
    }
}
