/*

Pseudocode

psvm main {
	menu()
}

static void menu() {
	
	totalOfPurchases += // calculate and add the total from the retrieved string from purchase()
	totalBalance = income - totalOfPurchases 
	listOfPurchases += // add all the Strings of purchase() here as a string 
	

	// 1) income 
		// add income 
	// 2) purchase
		// purchase() 
	// 3) show list of purchases
		// output listOfPurchases 
	// 4) balance remaining 
		// output totalBalance 
	// 0) exit 


}

static String purchase() {
	// option 2
	input item
	input price
	if (price < income) {
		// no income
	} else {
		String listOfPurchase += item + " " + price
	}
	// output "purchase was added!" 
	return listOfPurchase 
	// return the string here and calculate the price later 

}

static String listOfPurchases(String outputListOfPurchase) {
	
}


*/

import java.util.*;

public class Main {
  
  private static String outputListOfPurchases = "";
  public static void main(String[] args) {

      //Scanner scanner = new Scanner(System.in);
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
      case 2:
        outputListOfPurchases += purchase() + "\n";
        
        menu();
      case 3:
        if (outputListOfPurchases == "") {
          System.out.println("\nThe purchase list is empty\n");
        }  else {
          System.out.println();
          System.out.println(outputListOfPurchases);
          System.out.println("Total sum: $" + total(outputListOfPurchases));
          System.out.println();
        }
        menu();
      case 0:
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
    
    System.out.println("\nPurchase was added!\n");

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
  
    
    // public static double purchase () {

    //     Scanner scanner = new Scanner(System.in);

    //     String input = "";
    //     String outputUserChoices = "";
    //     double total = 0.0;

    //     while (scanner.hasNextLine()) {
    //         input = scanner.nextLine();
    //         outputUserChoices += input + "\n";

    //         if (input.contains("$")) {
    //             // delete $ and convert Strings into multiple strings
    //             String[] parts = input.split("\\$");

    //             // We do int i = 1, since we don't need the first part before $
    //             for (int i = 1; i < parts.length; i++) {
    //                 // Extract the numeric digits and decimal point from the substring
    //                 String numberString = parts[i].replaceAll("[^\\d.]+", "");
    //                 if (!numberString.isEmpty()) {
    //                     // convert the extracted String into a double
    //                     double number = Double.parseDouble(numberString);
    //                     total += number;    // add the number to our total
    //                 }
    //             }
    //         }
    //     }

    //     System.out.println(outputUserChoices);
    //     System.out.print("Total: $");
    //     System.out.printf("%.2f", total);

    //     return total;

    // }
  
}
