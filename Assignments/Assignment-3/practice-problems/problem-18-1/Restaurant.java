class Place {
}

class Restaurant {
  String name;
  String price;
  Place place;

  Restaurant(String name, String price, Place place) {
    this.name = name;
    this.price = price;
    this.place = place;
  }
}

class ChineseRestaurant extends Restaurant {
  boolean usesMSG;

  ChineseRestaurant(String name, String price, Place place, boolean usesMSG) {
    super(name, price, place);
    this.usesMSG = usesMSG;
  }
}
