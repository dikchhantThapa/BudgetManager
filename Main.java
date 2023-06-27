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
        
        do {
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
        
        } while (purchaseInput != "5");

      case "3":
        System.out.println("\nChoose the type of purchases");
        System.out.println("1) Food \n2) Clothes \n3) Entertainment \n4) Other \n5) All \n6) Back");

        String outputPurchases = scanner.nextLine();
        System.out.println();

        do {
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
              System.out.printf("Total sum: $%.2f", totalPurchases);              
              System.out.println("\n");
        
              break;
        
            case "6":
              menu();
        }
          System.out.println("\nChoose the type of purchases");
          System.out.println("1) Food \n2) Clothes \n3) Entertainment \n4) Other \n5) All \n6) Back");
          outputPurchases = scanner.nextLine();
        } while (outputPurchases != "6");

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
        sort();
        
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

    // converting every item price into a String with 2 decimal values
    String formattedPrice = String.format("%.2f", itemPrice);

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

      writer.printf("Income: $%.2f", income);
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
                sortByType();
                break;
            case 3:
                sortCertainType();
                break;
            case 4:
                menu();
                break;
        }
    } while (sortPick != 4);
}

  public static void sortAllPurchases() {
    if (purchasesLoaded == false) {
      System.out.println("\nThe purchase list is empty!");
    }  else {
      // Use LinkedHashMap to preserve insertion order
      Map<String, Double> allPurchasesMap = new LinkedHashMap<>();
      ArrayList<Double> pricesListAllPurchases = new ArrayList<>();
      double sum = 0.0;
  
      try {
        // open file for reading
        File file = new File("purchases.txt");
        Scanner scanner = new Scanner(file);
  
        // iterate through each line of the file
        while (scanner.hasNextLine()) {
          String lineInPurchase = scanner.nextLine();
  
          // ignore the line that shows Income
          if (lineInPurchase.startsWith("Income")) {
            continue;
          }
  
          // ignore the line that shows sum and calculate and print it later in print part
          if (lineInPurchase.startsWith("Total sum")) {
            continue;
          }
          
          if (lineInPurchase.contains("$")) {
            int lastIndex = lineInPurchase.lastIndexOf("$");
  
            // extract the String part (1st part) --> key for HashMap
            String stringPart = lineInPurchase.substring(0, lastIndex).trim();
  
            // extract the Double number (2nd part) --> value for HashMap
            String amountString = lineInPurchase.substring(lastIndex + 1).trim();
            double amount = Double.parseDouble(amountString);
            
            // store String(items) and Double(item cost) in the map as key and value 
            allPurchasesMap.put(stringPart, amount);
          } 
        }
        // close the file
        scanner.close();
      }  catch (FileNotFoundException e) {
        System.out.println("File not found!");
      }
  
      // add all prices onto our ArrayList for retrieval in the next step 
      for (var price : allPurchasesMap.entrySet()) {
        pricesListAllPurchases.add(price.getValue());
      }
      
      // sorting the list in Decreasing order on the basis of price
      Collections.sort(pricesListAllPurchases, Comparator.reverseOrder());
      
      boolean printedMilk = false;
      boolean printedDebt = false;
  
      // print the unsorted HashMap in a sorted format
      System.out.println();
      System.out.println("All:");
      for (var num : pricesListAllPurchases) {
        for (var entry : allPurchasesMap.entrySet()) {
          if (entry.getValue().equals(num)) {
            String formattedNum = String.format("%.2f", num);

            if (entry.getKey().equals("Milk") && !printedMilk) {
              System.out.println("Milk $" + formattedNum);
              printedMilk = true;
            } else if (entry.getKey().equals("Debt") && !printedDebt) {
              System.out.println("Debt $" + formattedNum);
              printedDebt = true;
            } else {
              if (entry.getKey().equals("Milk") && printedMilk == true) {
                continue;
              }
              if (entry.getKey().equals("Debt") && printedDebt == true) {
                continue;
              }
              System.out.println(entry.getKey() + " $" + formattedNum);
            }
            sum += num;

          }
        }
      }
      System.out.printf("Total sum: $%.2f", sum);
      System.out.println();
    }
    
  }

  public static void sortByType() {
    if (purchasesLoaded == false) {
      System.out.println("\nTypes:");
      System.out.println("Food - $0");
      System.out.println("Entertainment - $0");
      System.out.println("Clothes - $0");
      System.out.println("Other - $0");
      System.out.println("Total sum: $0");
    }  else {
      HashMap<String, Double> sortByTypeMap = new HashMap<>();
      ArrayList<Double> sortedTotalsByType = new ArrayList<>();
  
      // add all the total values into the ArrayList
      sortedTotalsByType.add(total(foodPurchases));
      sortedTotalsByType.add(total(clothesPurchases));
      sortedTotalsByType.add(total(entertainmentPurchases));
      sortedTotalsByType.add(total(otherPurchases));
      
      // now sort the ArrayList in decreasing order
      Collections.sort(sortedTotalsByType, Collections.reverseOrder());
  
      sortByTypeMap.put("Food - $", total(foodPurchases));
      sortByTypeMap.put("Clothes - $", total(clothesPurchases));
      sortByTypeMap.put("Entertainment - $", total(entertainmentPurchases));
      sortByTypeMap.put("Other - $", total(otherPurchases));
  
      System.out.println();
      System.out.println("Types:");
      for (var total : sortedTotalsByType) {
        for (var item : sortByTypeMap.entrySet()) {
          if (item.getValue().equals(total)) {
            String key = item.getKey();
  
            String formattedValue = String.format("%.2f", total);
            System.out.println(key + formattedValue);  // output key and value by type in decreasing order 
          }
        }
      }
      System.out.println("Sum: $" + totalPurchases);
    }
  }

  public static void sortCertainType() {
    
    System.out.println("\nChoose the type of purchase");
    System.out.println("1) Food \n2) Clothes \n3) Entertainment \n4) Other");
    int whichType = scanner.nextInt();  // user pick 

    if (purchasesLoaded == false) {
      System.out.println("\nThe purchase list is empty!");
    }  else {
      switch (whichType) {
        case 1:
          System.out.println("\nFood:");
          sortCertainTypeCalc(foodPurchases);
          System.out.printf("Total sum: $%.2f", total(foodPurchases));
          System.out.println("");
          break;
        case 2:
          System.out.println("\nClothes:");
          sortCertainTypeCalc(clothesPurchases);
          System.out.printf("Total sum: $%.2f", total(clothesPurchases));
          System.out.println("");
          break;
        case 3:
          System.out.println("\nEntertainment:");
          sortCertainTypeCalc(entertainmentPurchases);
          System.out.printf("Total sum: $%.2f", total(entertainmentPurchases));
          System.out.println("");
          break;
        case 4:
          System.out.println("\nOther:");
          sortCertainTypeCalc(otherPurchases);
          System.out.printf("Total sum: $%.2f", total(otherPurchases));
          System.out.println("");
          break;
      }
      sort();  // go back to the Sort Menu after particular purchase 
    }

  }

  public static void sortCertainTypeCalc(List<String> purchaseType) {
    // now we got individual ArrayList imported here 

    HashMap<String, Double> sortCertainMap = new HashMap<>();
    ArrayList<Double> sortedCertainType = new ArrayList<>();

    // storing ArrayList into a HashMap so retrieval is easy with Key and Value 
    for (var individualPurchase : purchaseType) {
      if (individualPurchase.contains("$")) {
        int lastIndex = individualPurchase.lastIndexOf("$");

        // extract the String part (1st part) --> key for HashMap
        String stringPart = individualPurchase.substring(0, lastIndex).trim();

        // extract the Double number (2nd part) --> value for HashMap
        String amountString = individualPurchase.substring(lastIndex + 1).trim();
        double amount = Double.parseDouble(amountString);

        // store String(items) and Double(item cost) in the map as key and value 
        sortCertainMap.put(stringPart, amount);
      }
    }

    // add all prices onto our ArrayList for retrieval in the next step 
    for (var price : sortCertainMap.entrySet()) {
      sortedCertainType.add(price.getValue());
    }

    // sorting the list in Decreasing order on the basis of price
    Collections.sort(sortedCertainType, Comparator.reverseOrder());

    // retrieving the sorted values and outputting it 
    for (var num : sortedCertainType) {
      for (var entry : sortCertainMap.entrySet()) {
        if (entry.getValue().equals(num)) {
          String formattedNum = String.format("%.2f", num);
          System.out.println(entry.getKey() + " $" + formattedNum);  // this outputs the sorted map
        }
      }
    }
    
  }


}