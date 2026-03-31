interface ITaxiVehicle {
}

abstract class ATaxiVehicle implements ITaxiVehicle {
  int idNum;
  int passengers;
  int pricePerMile;

  ATaxiVehicle(int idNum, int passengers, int pricePerMile) {
    this.idNum = idNum;
    this.passengers = passengers;
    this.pricePerMile = pricePerMile;
  }
}

class Cab extends ATaxiVehicle {
  Cab(int idNum, int passengers, int pricePerMile) {
    super(idNum, passengers, pricePerMile);
  }
}

class Limo extends ATaxiVehicle {
  int minRental;

  Limo(int idNum, int passengers, int pricePerMile, int minRental) {
    super(idNum, passengers, pricePerMile);
    this.minRental = minRental;
  }
}

class Van extends ATaxiVehicle {
  boolean access;

  Van(int idNum, int passengers, int pricePerMile, boolean access) {
    super(idNum, passengers, pricePerMile);
    this.access = access;
  }
}
