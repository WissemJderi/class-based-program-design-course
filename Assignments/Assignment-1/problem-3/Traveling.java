interface IHousing {
}

class Hut implements IHousing {
  int capacity;
  int population;

  Hut(int capacity, int population) {
    this.capacity = capacity;
    this.population = population; // must be less than the capacity
  }
}

class Inn implements IHousing {
  String name;
  int capacity;
  int population; // must be less than the capacity
  int stalls;

  Inn(String name, int capacity, int population, int stalls) {
    this.name = name;
    this.capacity = capacity;
    this.population = population;
    this.stalls = stalls;
  }
}

class Castle implements IHousing {
  String name;
  String familyName;
  int population;
  int carriageHouse;

  Castle(String name, String familyName, int population, int carriageHouse) {
    this.name = name;
    this.familyName = familyName;
    this.population = population;
    this.carriageHouse = carriageHouse;
  }
}

interface ITransportation {
}

class Horse implements ITransportation {
  IHousing from;
  IHousing to;
  String name;
  String color;

  Horse(IHousing from, IHousing to, String name, String color) {
    this.from = from;
    this.to = to;
    this.name = name;
    this.color = color;
  }
}

class Carriage implements ITransportation {
  IHousing from;
  IHousing to;
  int tonnage;

  Carriage(IHousing from, IHousing to, int tonnage) {
    this.from = from;
    this.to = to;
    this.tonnage = tonnage;
  }
}

class ExamplesHousing {
  ExamplesHousing() {
  }

  IHousing hovel = new Hut(5, 1);
  IHousing winterfell = new Castle("Winterfell", "Stark", 500, 6);
  IHousing crossroads = new Inn("Inn At The Crossroads", 40, 20, 12);

  IHousing hovel2 = new Hut(9, 2);
  IHousing hovel3 = new Hut(12, 2);
  IHousing crossroads1 = new Inn("Inn At The Crossroads", 40, 20, 1);
}

class ExamplesTravel {
  ExamplesTravel() {
  }

  IHousing hovel = new Hut(5, 1);
  IHousing winterfell = new Castle("Winterfell", "Stark", 500, 6);
  IHousing crossroads = new Inn("Inn At The Crossroads", 40, 20, 12);

  ITransportation horse1 = new Horse(hovel, winterfell, "Hors", "Red");
  ITransportation horse2 = new Horse(crossroads, hovel, "Shadowfax", "White");

  ITransportation carriage1 = new Carriage(crossroads, winterfell, 500);
  ITransportation carriage2 = new Carriage(winterfell, crossroads, 300);
}
