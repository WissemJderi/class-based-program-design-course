class Vehicle {
  int mileage;
  int price;

  Vehicle(int mileage, int price) {
    this.mileage = mileage;
    this.price = price;
  }
}

class Sedan extends Vehicle {
  Sedan(int mileage, int price) {
    super(mileage, price);
  }
}
