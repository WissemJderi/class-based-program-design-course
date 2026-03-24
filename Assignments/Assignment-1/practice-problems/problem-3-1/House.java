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

class ExamplesHouses {
  ExamplesHouses() {
  }

  Address brookline = new Address(23, "Maple Street", "Brookline");
  Address newton = new Address(5, "Joye Road", "Newton");
  Address waltham = new Address(83, "Winslow Road", "Waltham");

  House ranch = new House("Ranch", 7, brookline, 375000);
  House colonial = new House("Colonial", 9, newton, 450000);
  House cape = new House("Cape", 6, waltham, 235000);

}
