import java.util.*;

public enum TypesOfProducts {
  FOOD("Food", 0, new ArrayList<String>()),
  CLOTHES("Clothes", 0, new ArrayList<String>()),
  ENTERTAINMENT("Entertainment", 0, new ArrayList<String>()),
  OTHER("Other", 0, new ArrayList<String>());

  String name;
  double sum;
  ArrayList<String> purchases;
  
  TypesOfProducts(String name, double sum, ArrayList<String> purchases) {
    this.name = name;
    this.sum = sum;
    this.purchases = purchases;
  }

  
}