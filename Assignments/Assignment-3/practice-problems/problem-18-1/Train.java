class Route {
}

class Schedule {
}

class Stops {
}

class Train {
  Schedule s;
  Route r;

  Train(Schedule s, Route r) {
    this.s = s;
    this.r = r;
  }
}

class ExpressTrain extends Train {
  Stops st;
  String name;

  ExpressTrain(Stops st, String name, Schedule s, Route r) {
    super(s, r);
    this.st = st;
    this.name = name;
  }
}
