import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String input = "";
    String outputUserChoices = "";
    int total = 0;
    
    while (scanner.hasNextLine()) {
      input = scanner.nextLine();
      outputUserChoices += input + "\n";

      // slice up all the numbers after $ and add it to the input
    }

    
    System.out.println(outputUserChoices);
  }
}
