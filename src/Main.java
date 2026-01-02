import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Shirt cloth = new Shirt(0, 10, "Silk", true, 3, "short");
        cloth.Sleeves();
        System.out.println(cloth);
    }
}