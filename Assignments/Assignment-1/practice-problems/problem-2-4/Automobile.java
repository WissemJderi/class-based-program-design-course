class Automobile {
  String model;
  int price; // in dollars
  double mileage; // in miles per gallon
  boolean used;

  Automobile(String model, int price, double mileage, boolean used) {
    this.model = model;
    this.price = price;
    this.mileage = mileage;
    this.used = used;
  }
}

class ExamplesAutomobiles {
  ExamplesAutomobiles() {

  }

  Automobile car1 = new Automobile("BMW", 36000, 12, true);
  Automobile car2 = new Automobile("Golf", 30000, 10, false);
}
