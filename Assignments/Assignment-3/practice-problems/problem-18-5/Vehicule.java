interface IVehicle {
  double cost(double cp);
}

abstract class AVehicle implements IVehicle {
  double tank; // gallons

  AVehicle(double tank) {
    this.tank = tank;
  }

  public double cost(double cp) {
    return this.tank * cp;
  }
}

class Car extends AVehicle {
  Car(double tank) {
    super(tank);
  }
}

class Truck extends AVehicle {
  Truck(double tank) {
    super(tank);
  }
}

class Bus extends AVehicle {
  Bus(double tank) {
    super(tank);
  }
}

class ExamplesVehicle {
  IVehicle car = new Car(15.0);
  IVehicle truck = new Truck(50.0);
  IVehicle bus = new Bus(100.0);

  boolean testCost(Tester t) {
    return t.checkInexact(this.car.cost(3.5), 52.5, 0.01) &&
        t.checkInexact(this.truck.cost(3.5), 175.0, 0.01) &&
        t.checkInexact(this.bus.cost(3.5), 350.0, 0.01);
  }
}
