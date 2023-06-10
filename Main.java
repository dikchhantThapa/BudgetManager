import java.util.*;

public class Main {

  private static double totalPurchases = 0.0;
  private static double income = 0.0;

  private static List<String> foodPurchases = new ArrayList<>();
  private static List<String> clothesPurchases = new ArrayList<>();
  private static List<String> entertainmentPurchases = new ArrayList<>();
  private static List<String> otherPurchases = new ArrayList<>();

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

    try {
      // read user Pick
      int userMenuInput = scanner.nextInt();
      switch (userMenuInput) {
        case 1:
          System.out.println("\nEnter income: ");
          income += scanner.nextDouble();
          System.out.println("Income was added!\n");
          menu();
        case 2:
          // inside user purchase list
          System.out.println("\nChoose the type of purchase");
          System.out.println("1) Food \n2) Clothes \n3) Entertainment \n4) Other \n5) Back");

          int purchaseInput = scanner.nextInt();

          switch(purchaseInput) {
            case 1:
              foodPurchases.add(purchase());
              break;
            case 2:
              clothesPurchases.add(purchase());
              break;
            case 3:
              entertainmentPurchases.add(purchase());
              break;
            case 4:
              otherPurchases.add(purchase());
              break;
            case 5:
              break;
          }

          menu();

        case 3:
          System.out.println("\nChoose the type of purchases");
          System.out.println("1) Food \n2) Clothes \n3) Entertainment \n4) Other \n5) All \n6) Back");


          int outputPurchases = scanner.nextInt();
          switch(outputPurchases) {
            case 1:
              if (foodPurchases.isEmpty()) {
                System.out.println("The food purchase list is empty\n");
              }  else {
                for (String purchase : foodPurchases) {
                  System.out.println(purchase);
                }
                System.out.println("Total sum: $" + total(foodPurchases));
                System.out.println();
              }
              break;
            case 2:
              if (clothesPurchases.isEmpty()) {
                System.out.println("The clothes purchase list is empty\n");
              }  else {
                for (String purchase : clothesPurchases) {
                  System.out.println(purchase);
                }
                System.out.println("Total sum: $" + total(clothesPurchases));
                System.out.println();
              }
              break;
            case 3:
              if (entertainmentPurchases.isEmpty()) {
                System.out.println("The entertainment purchase list is empty\n");
              }  else {
                for (String purchase : entertainmentPurchases) {
                  System.out.println(purchase);
                }
                System.out.println("Total sum: $" + total(entertainmentPurchases));
                System.out.println();
              }
              break;
            case 4:
              if (otherPurchases.isEmpty()) {
                System.out.println("The other purchase list is empty\n");
              }  else {
                for (String purchase : otherPurchases) {
                  System.out.println(purchase);
                }
                System.out.println("Total sum: $" + total(otherPurchases));
                System.out.println();
              }
              break;
            case 5:
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
          }

          menu();

        case 4:
          totalPurchases = total(foodPurchases) + total(clothesPurchases) + total(entertainmentPurchases) + total(otherPurchases);
          System.out.println("\nBalance: $" + (income - totalPurchases) + "\n");
          menu();
        case 0:
          System.out.println("\nBye!");
          System.exit(0);
      }
    } catch(InputMismatchException e) {
      System.out.println("\nInvalid input. Please enter a valid option.\n");
      scanner.nextLine(); // Clear the input buffer
      menu();
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

  public static double total(List<String> purchases) {
    double total = 0.0;

    for (String input : purchases) {
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

    return total;
  }

}