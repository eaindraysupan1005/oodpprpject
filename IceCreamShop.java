import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class IceCreamShop {

    public static ArrayList<IceCream> menuItems = new ArrayList<>();
    public static ArrayList<IceCream> orders = new ArrayList<>();
    public static double totalprice = 0.0;

    public static void main(String[] args) {
        String filename = "menu.txt";
        
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
        System.out.println();
        System.out.println("Welcome to GrandBlue IceCream Shop\nHere are the Ice Cream Flavors");
        System.out.println("1. strawberry 30.5\n2. blueberry 33.5\n3. macha 32.5\n4. cookie 32.5\n5. vanilla 30.5\n6. chocolate 30.5\n7. durian 35.5");
               
       while(true){
        if(orders.size()>0){
            System.out.print("Do you want to order again? y or n: ");
            String s1 = input.next();
            if(s1.equals("y")|| s1.equals("Y")){
                chooseIcecream();
            }
            else if(s1.equals("n") || s1.equals("N")){
                 break;
            }           
        }
        else{
            chooseIcecream();
        }           
        }
        
        File orderfile = new File("order.txt");

        try(BufferedWriter bWrite = new BufferedWriter(new FileWriter(orderfile))){

            for(IceCream order:orders){
                bWrite.write(order.toString());
                bWrite.newLine();
            }

        }
        catch(IOException e){
            System.out.println("Error: "+ e.getMessage());
        }
        confirmOrder();



    }

    //method for choosing icecream
    public static void chooseIcecream(){

        Scanner input = new Scanner(System.in);
        System.out.print("Choose an ice cream flavor: ");
        int flavor = input.nextInt();
        while(flavor <=0 ||flavor>9){
            System.out.print("Please choose only 1 to 9! Choose again: ");
            flavor = input.nextInt();
        }
        System.out.print("Choose cup or cone(1. Cup, 2. Cone): ");
        int type = input.nextInt();
        while(type !=1 && type !=2){
            System.out.print("Choose only 1 or 2: ");
            type= input.nextInt();
        } 
        
        System.out.print("How many servings do you want? ");
        int count = input.nextInt();
        while(count == 0){
            System.out.print("Please order at least one item!");
            count= input.nextInt();
        } 

        //cup class or cone class create
        if(type == 1){
            IceCream temp = menuItems.get(flavor-1);
           String flavorString = temp.getFlavor();
           double price1 = temp.getPrice();

            totalprice += price1*count;
           //add to orders arraylist 
            IceCream orderItem = new Cup(flavorString,price1,"cup",count);
            orders.add(orderItem);
        }
        else if (type == 2){
            IceCream temp = menuItems.get(flavor-1);
            String flavorString = temp.getFlavor();
            double price1 = temp.getPrice();

            totalprice += price1*count;
            //add to orders arraylist
             IceCream orderItem = new cone(flavorString,price1,"cone",count);
             orders.add(orderItem);
        }
    }

    public static void confirmOrder(){
        Scanner input = new Scanner(System.in);
        System.out.print("Do you want to confirm order? y or n: ");
        String s2 = input.next();
        if(s2.equals("y")|| s2.equals("Y")){
            viewReceipt();            
        }else if(s2.equals("n")|| s2.equals("N")){
            System.out.println("Good Bye!");
            System.exit(0);
        }    
    }

    public static void viewReceipt(){
    
        String filename = "order.txt";
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Total Price: "+ totalprice);
    }







    // public static double totalprice(){

    // }
}
