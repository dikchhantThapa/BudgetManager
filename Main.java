import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String input = "";
    String outputUserChoices = "";
    double total = 0.0;
    
    while (scanner.hasNextLine()) {
      input = scanner.nextLine();
      outputUserChoices += input + "\n";

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
    }
    
    System.out.println(outputUserChoices);
    System.out.print("Total: $");
    System.out.printf("%.2f", total);
  }
}
