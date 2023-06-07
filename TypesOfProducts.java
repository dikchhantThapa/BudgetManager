import java.util.*;

public enum TypesOfProducts {
  FOOD("Food", 0, new ArrayList<String>());

  String name;
  double sum;
  ArrayList<String> purchases;
  
  TypesOfProducts(String name, double sum, ArrayList<String> purchases) {
    this.name = name;
    this.sum = sum;
    this.purchases = purchases;
  }
    
}