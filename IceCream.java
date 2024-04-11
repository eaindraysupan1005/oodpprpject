import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IceCream {
    protected String flavor;
    protected String size;

    public IceCream(String flavor, String size){
        this.flavor = flavor;
        this.size = size;
    }

    public void showDetails(){
        System.out.print("Flavor: " + flavor + "Size: "+ size );
    }
    
}

