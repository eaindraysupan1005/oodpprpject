import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadIceCreamMenu {
    public static void main(String[] args) {
        String filename = "menu.txt";
        ArrayList<IceCream> menuItems = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 3) { // Assuming each line has flavor, type, and price
                    String flavor = parts[0].trim();
                    String type = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    if(type == "cone"){
                    IceCream menuItem = new Cup(flavor, price);
                    menuItems.add(menuItem);
                    }
                    
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // Print the menu items
        for (IceCream menuItem : menuItems) {
            System.out.println(menuItem);
        }
    }
}
