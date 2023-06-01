import java.util.*;

public class Main {
  
  private static String outputListOfPurchases = "";
  private static double totalPurchases = 0.0;
  private static double income = 0.0;

  public static void main(String[] args) {
      menu();
  }

  public static void menu() {
    
    Scanner scanner = new Scanner(System.in);

    System.out.println("Choose your action:");
    System.out.println("1) Add income");
    System.out.println("2) Add purchase");
    System.out.println("3) Show list of purchases");
    System.out.println("4) Balance");
    System.out.println("0) Exit");

    // read user Pick
    int userMenuInput = scanner.nextInt();
    switch (userMenuInput) {
      case 1:
        System.out.println("\nEnter income: ");
        income = scanner.nextDouble();
        System.out.println("Income was added!\n");
        menu();
      case 2:
        outputListOfPurchases += purchase() + "\n";
        menu();
      case 3:
        if (outputListOfPurchases == "") {
          System.out.println("\nThe purchase list is empty\n");
        }  else {
          System.out.println();
          System.out.print(outputListOfPurchases);
          totalPurchases = total(outputListOfPurchases);
          System.out.println("Total sum: $" + totalPurchases);
          System.out.println();
        }
        menu();
      case 4:
        System.out.println("\nBalance: $" + (income - totalPurchases) + "\n");
        menu();
      case 0:
        System.out.println("\nBye!");
        System.exit(0);
        break;
        
    }
  }

  public static String purchase() {
    Scanner scanner = new Scanner(System.in);

    String listOfPurchase = "";

    // item name
    System.out.println("\nEnter purchase name: ");
    String itemName = scanner.nextLine();

    // item price
    System.out.println("Enter its price: ");
    double itemPrice = scanner.nextDouble();
    
    System.out.println("Purchase was added!\n");

    listOfPurchase += itemName + " $" + itemPrice;
    return listOfPurchase;      
    
  }

  public static double total(String input) {
    double total = 0.0;
    if (input.contains("$")) {
      // delete $ and convert Strings into multiple strings
      String[] parts = input.split("\\$");
  
      // We do int i = 1, since we don't need the first part before $
      for (int i = 1; i < parts.length; i++) {
        // Extract the numeric digits and decimal point from the substring 
        String numberString = parts[i].replaceAll("[^\\d.]+", "");
        if (!numberString.isEmpty()) {
          // convert the extracted String into a double
          double number = Double.parseDouble(numberString);
          total += number;    // add the number to our total 
        }
      } 
    }
  
    // System.out.print("Total: $");
    // System.out.printf("%.2f", total);
    return total;
  }
  
}
