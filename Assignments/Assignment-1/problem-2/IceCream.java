interface IIceCream {

}

class EmptyServing implements IIceCream {
  boolean cone;

  EmptyServing(boolean cone) {
    this.cone = cone;
  }
}

class Scooped implements IIceCream {
  IIceCream more;
  String flavor;

  Scooped(IIceCream more, String flavor) {
    this.more = more;
    this.flavor = flavor;
  }
}

class ExamplesIceCream {
  ExamplesIceCream() {
  }

  IIceCream order1 = new Scooped(
      new Scooped(new Scooped(new Scooped(new EmptyServing(false), "mint chip"), "coffee"), "black raspberry"),
      "caramel swirl");
  IIceCream order2 = new Scooped(new Scooped(new Scooped(new EmptyServing(true), "chocolate"), "vanilla"),
      "strawberry");
}
