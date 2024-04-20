import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IceCream {
    protected String flavor;
    protected double price;

    public IceCream(String flavor, double price){
        this.flavor = flavor;
        this.price = price;
    }

    @Override
    public String toString(){
        return "flavor: "+flavor;
    } 
    
}

