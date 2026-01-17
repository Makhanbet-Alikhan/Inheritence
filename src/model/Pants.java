package model;

public class Pants extends ClothingItem{

    private boolean withPocket;

    public Pants(int clothId, double price, String material, boolean isAvailable, int count_of_item, boolean withPocket){
        super(clothId, price, material, isAvailable, count_of_item);
        this.withPocket = withPocket;
    }

    public boolean getWithPocket(){
        return withPocket;
    }

    public void setWithPocket(boolean withPocket){
        this.withPocket = withPocket;
    }

    @Override
    public void available() {
        if(isAvailable){
            System.out.println("Pants" + " is available.");
        }else{
            System.out.println("Pants" + " is unavailable.");
        }
    }

    @Override
    public void getCategory() {
        System.out.println("Pants Item");
    }

    public void Pockets() {
        if(withPocket){
            System.out.println("Pants have pockets");
        }else{
            System.out.println("Pants do not have pockets");
        }
    }

    public void PantsMethod(){
        System.out.println("Pants are cool, it has been created for legs");
    }

    @Override
    public String toString(){
        return super.toString() + " pocket:" + withPocket;
    }
}
