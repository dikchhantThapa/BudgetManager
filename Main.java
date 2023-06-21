import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {

  private static double totalPurchases = 0.0;
  private static double income = 0.0;
  private static double loadedBalance = 0.0;

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

        //   int purchaseInput = scanner.nextInt();

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
                System.out.println("Total sum: $" + total(foodPurchases));
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
                System.out.println("Total sum: $" + total(clothesPurchases));
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
                System.out.println("Total sum: $" + total(entertainmentPurchases));
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
                System.out.println("Total sum: $" + total(otherPurchases));
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

        }

      case "4":
        // come back
        totalPurchases = total(foodPurchases) + total(clothesPurchases) + total(entertainmentPurchases) + total(otherPurchases);
        System.out.println("\nBalance: $" + (income - totalPurchases) + "\n");
        menu();

      case "5":
        savePurchasesToFile();
        System.out.println("\nPurchases were saved!");
        menu();
        
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

      writer.println("All Purchases: ");

      // writer.println("Food purchases: ");
      for (String purchase : foodPurchases) {
        writer.println(purchase);
      }

      // writer.println("\nClothes purchases: ");
      for (String purchase : clothesPurchases) {
        writer.println(purchase);
      }

      // writer.println("\nEntertainment purchases: ");
      for (String purchase : entertainmentPurchases) {
        writer.println(purchase);
      }

      // writer.println("\nOther purchases: ");
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

}