interface IItems {
  // computes the unit price (cents per gram) of a grocery item
  double unitPrice();

  /*
   * determines whether the unit price of a grocery
   * item is lower than some given amount
   */

  boolean lowerUnitPrice(double other);

  /*
   * determines whether a grocery item’s unit price is
   * less than some other (presumably) comparable item’s unit price.
   */

  boolean cheaperThan(IItems other);
}

class Juice implements IItems {
  String brandName;
  double weight; // grams
  int price; // cents
  String flavor;
  String packagedAs;

  Juice(String brandName, double weight, int price, String flavor, String packagedAs) {
    this.brandName = brandName;
    this.weight = weight;
    this.price = price;
    this.flavor = flavor;
    this.packagedAs = packagedAs;
  }

  public double unitPrice() {
    return this.price / this.weight;
  }

  public boolean lowerUnitPrice(double other) {
    return this.unitPrice() < other;
  }

  public boolean cheaperThan(IItems other) {
    return this.unitPrice() < other.unitPrice();
  }
}

class Coffee implements IItems {
  String brandName;
  double weight; // grams
  int price; // cents
  boolean regular;

  Coffee(String brandName, double weight, int price, boolean regular) {
    this.brandName = brandName;
    this.weight = weight;
    this.price = price;
    this.regular = regular;
  }

  public double unitPrice() {
    return this.price / this.weight;
  }

  public boolean lowerUnitPrice(double other) {
    return this.unitPrice() < other;
  }

  public boolean cheaperThan(IItems other) {
    return this.unitPrice() < other.unitPrice();
  }
}

class IceCream implements IItems {
  String brandName;
  double weight; // grams
  int price; // cents
  String flavor;

  IceCream(String brandName, double weight, int price, String flavor) {
    this.brandName = brandName;
    this.weight = weight;
    this.price = price;
    this.flavor = flavor;
  }

  public double unitPrice() {
    return this.price / this.weight;
  }

  public boolean lowerUnitPrice(double other) {
    return this.unitPrice() < other;
  }

  public boolean cheaperThan(IItems other) {
    return this.unitPrice() < other.unitPrice();
  }
}
