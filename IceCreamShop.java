import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class IceCreamShop {

    public static ArrayList<IceCream> menuItems = new ArrayList<>();
    public static ArrayList<IceCream> orders = new ArrayList<>();

    public static void main(String[] args) {

        // reading the file start
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
        // reading the file end

        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println(
                "Welcome to GrandBlue IceCream Shop\n*********************\nHere are the Ice Cream Flavors and Prices");
        System.out.print(
                "1. strawberry 30.5 baht\n2. blueberry 33.5 baht\n3. macha 32.5 baht\n4. cookie 32.5 baht\n5. vanilla 30.5 baht\n6. chocolate 30.5 baht\n7. durian 35.5 baht");
        System.out.print("\n*********************\n");

        outerloop: while (true) {
            if (orders.size() > 0) {
                checkSameType();
                confirmOrder();
            } else {
                chooseIcecream();
            }
        } // outerloop end
    }
    // main method end

    // method for choosing icecream
    public static void chooseIcecream() {

        Scanner input = new Scanner(System.in);

        // ask the icecream flavor
        int flavor = 0;
        System.out.print("Choose a IceCream flavor: ");
        String f = input.next();
        while (true) {
            if (!f.equals("1") && !f.equals("2") && !f.equals("3") && !f.equals("4") && !f.equals("5") && !f.equals("6")
                    && !f.equals("7")) {
                System.out.print("Invalid Input!! Choose only 1 to 7:  ");
                f = input.next();
            } else {
                flavor = Integer.parseInt(f);
                break;
            }
        }

        // ask the type of the icecream
        int type = 0;
        System.out.print("Choose cup or cone(1. Cup, 2. Cone): ");
        String t = input.next();
        while (true) {
            if (!t.equals("1") && !t.equals("2")) {
                System.out.print("Invalid Input!! Choose only 1 or 2:  ");
                t = input.next();
            } else {
                type = Integer.parseInt(t);
                break;
            }
        }

        // ask the number of servings
        int count = quantityValid("How many servings do you want? ");

        // cup class or cone class create
        if (type == 1) {
            IceCream temp = menuItems.get(flavor - 1);
            String flavorString = temp.getFlavor();
            double price1 = temp.getPrice();

            // add to orders arraylist
            IceCream orderItem = new Cup(flavorString, price1, count);
            orders.add(orderItem);
        } else if (type == 2) {
            IceCream temp = menuItems.get(flavor - 1);
            String flavorString = temp.getFlavor();
            double price1 = temp.getPrice();

            // add to orders arraylist
            IceCream orderItem = new Cone(flavorString, price1, count);
            orders.add(orderItem);
        }
    }

    // method for checking same type
    public static void checkSameType() {
        for (int i = 0; i < orders.size() - 1; i++) {
            if (orders.get(i).getFlavor() == orders.get(i + 1).getFlavor()) {
                if (orders.get(i) instanceof Cup && orders.get(i + 1) instanceof Cup) {
                    int newCount = ((Cup) orders.get(i)).getCount() + ((Cup) orders.get(i + 1)).getCount();
                    ((Cup) orders.get(i)).setCount(newCount);
                    orders.remove(i + 1);
                } else if (orders.get(i) instanceof Cone && orders.get(i + 1) instanceof Cone) {
                    int newCount = ((Cone) orders.get(i)).getCount() + ((Cone) orders.get(i + 1)).getCount();
                    ((Cone) orders.get(i)).setCount(newCount);
                    orders.remove(i + 1);
                }
            }
        }
    }

    // method for confirming order
    public static void confirmOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println(
                "*********************\n1: Do you want to view your order?\n2: Do you want to Confirm your order?\n3: Do you want to modify your order?\n4: Do you want to Order more items?\n5. Do you want to exit? ");
        String mainOption = input.next();
        while (!mainOption.equals("1") && !mainOption.equals("2") && !mainOption.equals("3") && !mainOption.equals("4") && !mainOption.equals("5")) {
            System.out.println("Choose only 1 to 5: ");
            mainOption = input.next();
        }

        if (mainOption.equals("1")) {

            printCurrentOrder();
            System.out.print("*********************\n1. Confirm the order, 2.Modify the order, 3.Add more Items, 4.Exit : ");
            String viewOption = input.next();

            while (true) {
                if (!viewOption.equals("1") && !viewOption.equals("2") && !viewOption.equals("3")) {
                    System.out.println("Enter only 1 to 3: ");
                    viewOption = input.next();
                } else {
                    break;
                }
            }

            if (viewOption.equals("1")) {
                // file writing start
                File orderfile = new File("order.txt");
                try (BufferedWriter bWrite = new BufferedWriter(new FileWriter(orderfile))) {

                    for (IceCream order : orders) {
                        bWrite.write(order.toString());
                        bWrite.newLine();
                    }
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                // file writing end
                viewReceipt();
                System.exit(0);

            } else if (viewOption.equals("2")) {
                modifyOrder();
                System.out.println("Your Orders have been UPDATED!");
                printCurrentOrder();
            } else if (viewOption.equals("3")) {
                chooseIcecream();
            }else if(viewOption.equals("4")){
                System.out.println("*********************\nThank you for your visit. Good Bye!\n");
                System.exit(0);
            }

        } else if (mainOption.equals("2")) {
            // file writing start
            File orderfile = new File("order.txt");
            try (BufferedWriter bWrite = new BufferedWriter(new FileWriter(orderfile))) {

                for (IceCream order : orders) {
                    bWrite.write(order.toString());
                    bWrite.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
            // file writing end
            viewReceipt();
            System.exit(0);
        } else if (mainOption.equals("3")) {
            modifyOrder();
            System.out.println("Your Orders Updated!");
            printCurrentOrder();
        } else if (mainOption.equals("4")) {
            chooseIcecream();
        } else {
            System.out.println("*********************\nThank you for your visit. Good Bye!\n");
            System.exit(0);
        }
    }

    // method for modifying orders
    public static void modifyOrder() {

        Scanner input = new Scanner(System.in);

        printCurrentOrder();
        if (orders.size() > 1) {
            int edit = 0;
            while (true) {
                try {
                    // Get input
                    System.out.print("*********************\nEnter the order number that you want to modify: ");
                    edit = input.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    // Clear the invalid input
                    input.next();
                    System.out.print("Invalid input!! ");
                }
            }

            while (edit <= 0 || edit > orders.size()) {
                try {
                    // Get input
                    System.out.print("You must choose from your orders: ");
                    edit = input.nextInt();
                } catch (InputMismatchException e) {
                    // Clear the invalid input
                    input.next();
                    System.out.print("Invalid input!! ");
                }
            }

            System.out.print("What do you want to change? (1.flavor 2.type 3.quantity 4. Delete the item) ");
            String s3 = input.next();
            while (!s3.equals("1") && !s3.equals("2") && !s3.equals("3") && !s3.equals("4")) {
                System.out.print("Choose only 1 to 4: ");
                s3 = input.next();
            }
            int etype = Integer.parseInt(s3);
            editOrder(edit - 1, etype);
            // checkSameType();

        } else {
            System.out.print("*********************\nWhat do you want to change? (1.flavor 2.type 3.quantity): ");
            String s3 = input.next();
            while (!s3.equals("1") && !s3.equals("2") && !s3.equals("3")) {
                System.out.println("Choose only 1 to 3: ");
                s3 = input.next();
            }
            int etype = Integer.parseInt(s3);
            editOrder(0, etype);
        }

    }

    // method for printing current orders list
    public static void printCurrentOrder() {
        System.out.print("*********************\nThis is your order list.\n");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". " + orders.get(i).toString());
        }
    }

    // method for editing orders
    public static void editOrder(int orderIndex, int editType) {

        Scanner input = new Scanner(System.in);
        if (editType == 1) {
            System.out.println(
                    "*********************\n1. strawberry 30.5 baht\n2. blueberry 33.5 baht\n3. macha 32.5 baht\n4. cookie 32.5 baht\n5. vanilla 30.5 baht\n6. chocolate 30.5 baht\n7. durian 35.5 baht");
            System.out.print("*********************\nChoose a flavor to change: ");
            String f = input.next();
            while (true) {
                if (!f.equals("1") && !f.equals("2") && !f.equals("3") && !f.equals("4") && !f.equals("5")
                        && !f.equals("6")
                        && !f.equals("7")) {
                    System.out.print("Invalid Input!! Choose only 1 to 7:  ");
                    f = input.next();
                } else {
                    int flavor = Integer.parseInt(f);
                    IceCream temp = orders.get(orderIndex);
                    String f1 = temp.getFlavor();
                    String f2 = menuItems.get(flavor - 1).getFlavor();
                    // System.out.println("f1: "+f1);
                    // System.out.println("f2: "+f2);

                    if (f1.equals(f2)) {
                        System.out.print("You choose the same flavor again! ");
                        f = input.next();
                    } else {
                        temp.setFlavor(f2);
                        temp.setPrice(menuItems.get(flavor - 1).getPrice());
                        orders.set(orderIndex, temp);
                        break;
                    }
                }
            }
        } else if (editType == 2) {
            IceCream temp = orders.get(orderIndex);
            if (temp instanceof Cup) {
                int count = ((Cup) temp).getCount();
                double price = temp.getPrice();
                String flavor = temp.getFlavor();

                Cone newC = new Cone(flavor, price, count);
                orders.set(orderIndex, newC);
            } else if (temp instanceof Cone) {
                int count = ((Cone) temp).getCount();
                double price = temp.getPrice();
                String flavor = temp.getFlavor();

                Cup newC = new Cup(flavor, price, count);
                orders.set(orderIndex, newC);
            }
        } else if (editType == 3) {
            int newCount = quantityValid("Enter the quantity to change: ");

            IceCream temp = orders.get(orderIndex);
            if (temp instanceof Cup) {
                int count = ((Cup) temp).getCount();
                if (count == newCount) {
                    newCount = quantityValid("You enter the same Quantity! ");
                } else {
                    ((Cup) temp).setCount(newCount);
                }
            } else if (temp instanceof Cone) {
                int count = ((Cone) temp).getCount();
                if (count == newCount) {
                    newCount = quantityValid("You enter the same Quantity! ");
                } else {
                    ((Cone) temp).setCount(newCount);
                }
            }
        } else if (editType == 4) {
            orders.remove(orderIndex);
        }
    }

    // method for quantity valid
    public static int quantityValid(String s) {
        Scanner input = new Scanner(System.in);
        int c = 0;
        while (true) {
            try {
                // Get input
                System.out.print(s);
                c = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                // Clear the invalid input
                input.next();
                System.out.print("Invalid input!! ");
            }
        }

        while (c <= 0) {
            try {
                // Get input
                System.out.print("Please order at least one item! ");
                c = input.nextInt();
            } catch (InputMismatchException e) {
                // Clear the invalid input
                input.next();
                System.out.print("Invalid Input!! ");
            }
        }
        return c;
    }

    // method for calcute the totalPrice
    public static double totalPrice(ArrayList<IceCream> orders) {

        double total = 0.0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i) instanceof Cup) {
                Cup temp = (Cup) orders.get(i);
                total += temp.getPrice() * temp.getCount();
            } else if (orders.get(i) instanceof Cone) {
                Cone temp = (Cone) orders.get(i);
                total += temp.getPrice() * temp.getCount();
            }
        }
        return total;

    }

    // method for viewing receipt
    public static void viewReceipt() {

        String filename = "order.txt";
        System.out.print("*********************\n");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Total Price: " + totalPrice(orders)
                + " baht\nThank you for your purchase! We hope you will enjoy your ice cream. ");
        System.out.println();
    }

}
