import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class IceCreamShop {
    public static void main(String[] args) {
        String filename = "menu.txt";
        ArrayList<IceCream> menuItems = new ArrayList<>();

      
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) { // Assuming each line has flavor, type, and price
                    String flavor = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());                     
                        IceCream menuItem = new IceCream(flavor, price);
                        menuItems.add(menuItem);                     

                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        Scanner input= new Scanner(System.in);
        System.out.println("");
        System.out.println("1. strawberry 23.4\n2. blueberry 24.5\n3. macha 27.8\n4. cookie 28.1\n5. vanilla 23.9\n6. chocolate 26.5\n7. durian 29.1\n8. lime 27.2\n9. rainbow 25.5");
        System.out.print("Choose an ice cream flavor:");
        int flavor = input.nextInt();
        while(flavor <=0 ||flavor>9){
            System.out.print("Please choose only 1 to 9! Choose again: ");
            flavor = input.nextInt();
        }

        System.out.println("Enter cup or cone: \n1. Cup (+5$)\n2. Cone (+3$) ");
        int type = input.nextInt();
        while(type !=1 && type !=2){
            System.out.print("Choose only 1 or 2: ");
            type= input.nextInt();
        }
        System.out.println("Enter size: small or medium");
        String size = input.nextLine();

        ArrayList<IceCream> orders = new ArrayList<>();

        
      






        // Print the menu items
        for (IceCream menuItem : menuItems) {
          
            System.out.println(menuItem);
        }
    }
}
