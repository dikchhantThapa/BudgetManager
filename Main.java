import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {

  public static Scanner scanner = new Scanner(System.in);

  private static double totalPurchases = 0.0;
  private static double income = 0.0;

  private static boolean purchasesLoaded = false;  // Flag to track if purchases are loaded

  private static List<String> foodPurchases = new ArrayList<>();
  private static List<String> clothesPurchases = new ArrayList<>();
  private static List<String> entertainmentPurchases = new ArrayList<>();
  private static List<String> otherPurchases = new ArrayList<>();

  public static void main(String[] args) {
    menu();
  }

  public static void menu() {

    Scanner scanner = new Scanner(System.in);

    System.out.println("\nChoose your action:");
    System.out.println("1) Add income");
    System.out.println("2) Add purchase");
    System.out.println("3) Show list of purchases");
    System.out.println("4) Balance");
    System.out.println("5) Save");
    System.out.println("6) Load");
    System.out.println("7) Analyze (Sort)");
    System.out.println("0) Exit");


    switch (scanner.nextLine()) {
      case "1":
        System.out.println("\nEnter income: ");
        income += scanner.nextDouble();
        System.out.println("Income was added!\n");
        menu();
      case "2":
        // inside user purchase list
        System.out.println("\nChoose the type of purchase");
        System.out.println("1) Food \n2) Clothes \n3) Entertainment \n4) Other \n5) Back");

        String purchaseInput = scanner.nextLine();

        while (purchaseInput != "5") {

          switch(purchaseInput) {
            case "1":
              foodPurchases.add(purchase());
              break;
            case "2":
              clothesPurchases.add(purchase());
              break;
            case "3":
              entertainmentPurchases.add(purchase());
              break;
            case "4":
              otherPurchases.add(purchase());
              break;
            case "5":
              menu();
          }
          System.out.println("\nChoose the type of purchase");
          System.out.println("1) Food \n2) Clothes \n3) Entertainment \n4) Other \n5) Back");

          purchaseInput = scanner.nextLine();

        }

      case "3":
        System.out.println("\nChoose the type of purchases");
        System.out.println("1) Food \n2) Clothes \n3) Entertainment \n4) Other \n5) All \n6) Back");

        String outputPurchases = scanner.nextLine();
        System.out.println();

        while (outputPurchases != "6") {

          switch(outputPurchases) {
            case "1":
              if (foodPurchases.isEmpty()) {
                System.out.println("\nThe food purchase list is empty\n");
              }  else {
                System.out.println();
                for (String purchase : foodPurchases) {
                  System.out.println(purchase);
                }
                System.out.printf("Total sum: $%.2f", total(foodPurchases));
                System.out.println();
              }
              break;
            case "2":
              if (clothesPurchases.isEmpty()) {
                System.out.println("\nThe clothes purchase list is empty\n");
              }  else {
                System.out.println();
                for (String purchase : clothesPurchases) {
                  System.out.println(purchase);
                }
                System.out.printf("Total sum: $%.2f", total(clothesPurchases));
                System.out.println();
              }
              break;
            case "3":
              if (entertainmentPurchases.isEmpty()) {
                System.out.println("\nThe entertainment purchase list is empty\n");
              }  else {
                System.out.println();
                for (String purchase : entertainmentPurchases) {
                  System.out.println(purchase);
                }
                System.out.printf("Total sum: $%.2f", total(entertainmentPurchases));
                System.out.println();
              }
              break;
            case "4":
              if (otherPurchases.isEmpty()) {
                System.out.println("\nThe other purchase list is empty\n");
              }  else {
                System.out.println();
                for (String purchase : otherPurchases) {
                  System.out.println(purchase);
                }
                System.out.printf("Total sum: $%.2f", total(otherPurchases));
                System.out.println();
              }
              break;
            case "5":
              System.out.println("\nAll: ");
              for (String purchase : foodPurchases) {
                System.out.println(purchase);
              }
              for (String purchase : clothesPurchases) {
                System.out.println(purchase);
              }
              for (String purchase : entertainmentPurchases) {
                System.out.println(purchase);
              }
              for (String purchase : otherPurchases) {
                System.out.println(purchase);
              }

              totalPurchases = total(foodPurchases) + total(clothesPurchases) + total(entertainmentPurchases) + total(otherPurchases);
              System.out.printf("Total sum this is from case 3: $%.2f", totalPurchases);              
              System.out.println("\n");

              break;

            case "6":
              menu();

          }
          System.out.println("\nChoose the type of purchases");
          System.out.println("1) Food \n2) Clothes \n3) Entertainment \n4) Other \n5) All \n6) Back");
          outputPurchases = scanner.nextLine();

        }

      case "4":
        totalPurchases = total(foodPurchases) + total(clothesPurchases) + total(entertainmentPurchases) + total(otherPurchases);
        System.out.println("\nBalance: $" + (income - totalPurchases) + "\n");
        // // just test income
        // System.out.println("Income = " + income);  // IT'S WORKING!! 
        menu();

      case "5":
        savePurchasesToFile();
        System.out.println("\nPurchases were saved!");
        menu();

      case "6":
        loadPurchasesFromFile();
        System.out.println("\nPurchases were loaded!");
        purchasesLoaded = true; // purchase  flag marked as True when loaded
        menu();

      case "7":
        if (!purchasesLoaded) {
          System.out.println("\nThe purchase list is empty!");
          menu();
        } else {
          sort();
        }
        
      case "0":
        System.out.println("\nBye!");
        System.exit(0);

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
    String formattedPrice = String.format("%.2f", itemPrice);
    // converting every item price into a String with 2 decimal values

    System.out.println("Purchase was added!\n");

    listOfPurchase += itemName + " $" + formattedPrice;
    return listOfPurchase;

  }

  public static double total(List<String> purchases) {
    double total = 0.0;

    for (String input : purchases) {
      // get index of the last $ character
      int indexLast =  input.lastIndexOf("$");
      
      // extract the numeric portion after the last '$' character
      String numberString = input.substring(indexLast + 1);
      
      if (!numberString.isEmpty()) {
        // convert the extracted String into a double
        double number = Double.parseDouble(numberString);
        total += number;  // add the number to our total
      }
    }

    return total;
  }

  public static void savePurchasesToFile() {
    try {
      File file = new File("purchases.txt");
      PrintWriter writer = new PrintWriter(file);

      // added income in the first line
      writer.printf("Income: $%.2f", income);

      // line 
      writer.println("\nAll Purchases: ");

      writer.println("Food purchases: ");
      for (String purchase : foodPurchases) {
        writer.println(purchase);
      }

      writer.println("Clothes purchases: ");
      for (String purchase : clothesPurchases) {
        writer.println(purchase);
      }

      writer.println("Entertainment purchases: ");
      for (String purchase : entertainmentPurchases) {
        writer.println(purchase);
      }

      writer.println("Other purchases: ");
      for (String purchase : otherPurchases) {
        writer.println(purchase);
      }
      
      totalPurchases = total(foodPurchases) + total(clothesPurchases) + total(entertainmentPurchases) + total(otherPurchases);
      writer.printf("Total sum: $%.2f", totalPurchases);
      // writer.println("Total sum: $" + totalPurchases);
      
      writer.close();
    }  catch (IOException e) {
      System.out.println("Error saving purchases to file"); 
    }
  }

  public static void loadPurchasesFromFile() {
    try {
      File file = new File("purchases.txt");
      Scanner scanner = new Scanner(file);

      // clear existing purchase lists
      foodPurchases.clear();
      clothesPurchases.clear();
      entertainmentPurchases.clear();
      otherPurchases.clear();

      String currentCategory = null;  // track current category being processed

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();

        // check if the line indicates a category
        if (line.startsWith("Income")) {
          String incomeString = line.substring(line.indexOf("$") + 1);
          income = Double.parseDouble(incomeString);
        } else if (line.startsWith("Food")) {
          currentCategory = "Food";
        } else if (line.startsWith("Clothes")) {
          currentCategory = "Clothes";
        } else if (line.startsWith("Entertainment")) {
          currentCategory = "Entertainment";
        } else if (line.startsWith("Other")) {
          currentCategory = "Other";
        } else if (line.startsWith("Total sum")) {
          String totalSumString = line.substring(line.indexOf("$") + 1);
          totalPurchases = Double.parseDouble(totalSumString);
        } else if (currentCategory != null) {
          // add the line to the respective list based on the current category
          switch (currentCategory) {
            case "Food":
              foodPurchases.add(line);
              break;
            case "Clothes":
              clothesPurchases.add(line);
              break;
            case "Entertainment":
              entertainmentPurchases.add(line);
              break;
            case "Other":
              otherPurchases.add(line);
              break;
          }
        }
      }
      scanner.close();
    }  catch (IOException e) {
      System.out.println("Error loading purchases from file!");
    }
  }

  public static void sort() {
    int sortPick;
    do {
        System.out.println("\nHow do you want to sort?");
        System.out.println("1) Sort all purchases");
        System.out.println("2) Sort by type");
        System.out.println("3) Sort certain type");
        System.out.println("4) Back");

        sortPick = scanner.nextInt();

        switch (sortPick) {
            case 1:
                sortAllPurchases();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                menu();
                break;
        }
    } while (sortPick != 4);
}

  public static void sortAllPurchases() {
    Map<String, Double> allPurchasesMap = new HashMap<>();

    try {
      // open file for reading
      File file = new File("purchases.txt");
      Scanner scanner = new Scanner(file);

      // iterate through each line of the file
      while (scanner.hasNextLine()) {
        String lineInPurchase = scanner.nextLine();

        if (lineInPurchase.contains("Income")) {
          continue;
        }
        
        if (lineInPurchase.contains("$")) {
          int lastIndex = lineInPurchase.lastIndexOf("$");

          // extract the String part (1st part) --> key for HashMap
          String stringPart = lineInPurchase.substring(0, lastIndex).trim();

          // extract the Double number (2nd part) --> value for HashMap
          String amountString = lineInPurchase.substring(lastIndex + 1).trim();
          double amount = Double.parseDouble(amountString);

          // convert the cost(double) to 2 decimal places before storing
          amount = Math.round(amount * 100) / 100;
          
          // store String(items) and Double(item cost) in the map as key and value 
          allPurchasesMap.put(stringPart, amount);
        }
      }
      // close the file
      scanner.close();
    }  catch (FileNotFoundException e) {
      System.out.println("File not found!");
    }

    System.out.println();
    // Print the HashMap (unsorted)
    for (var entry : allPurchasesMap.entrySet()) {
      System.out.println(entry.getKey() + " $" + entry.getValue());
    }
    
    
  }



}