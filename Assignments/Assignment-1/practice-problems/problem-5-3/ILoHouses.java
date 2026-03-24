class Address {
  int streetNumber;
  String streetName;
  String city;

  Address(int streetNumber, String streetName, String city) {
    this.streetNumber = streetNumber;
    this.streetName = streetName;
    this.city = city;
  }
}

class House {
  String kind;
  int noRooms;
  Address address;
  int price;

  House(String kind, int noRooms, Address address, int price) {
    this.kind = kind;
    this.noRooms = noRooms;
    this.address = address;
    this.price = price;
  }
}

interface ILoHouses {
}

class MtLoHouse implements ILoHouses {
  MtLoHouse() {
  }
}

class ConsLoHouses implements ILoHouses {

  House first;
  ILoHouses rest;

  ConsLoHouses(House first, ILoHouses rest) {
    this.first = first;
    this.rest = rest;
  }
}

class ExamplesILoHouses {
  ExamplesILoHouses() {
  }

  Address brookline = new Address(23, "Maple Street", "Brookline");
  Address newton = new Address(5, "Joye Road", "Newton");
  Address waltham = new Address(83, "Winslow Road", "Waltham");

  House ranch = new House("Ranch", 7, brookline, 375000);
  House colonial = new House("Colonial", 9, newton, 450000);
  House cape = new House("Cape", 6, waltham, 235000);

  ILoHouses empty = new MtLoHouse();
  ILoHouses l1 = new ConsLoHouses(ranch, empty);
  ILoHouses l2 = new ConsLoHouses(ranch, new ConsLoHouses(colonial, empty));

}
