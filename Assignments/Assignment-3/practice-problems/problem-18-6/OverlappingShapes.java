import tester.Tester;

class CartPt {
  int x;
  int y;

  CartPt(int x, int y) {
    this.x = x;
    this.y = y;
  }

  double distTo0() {
    return Math.sqrt(this.x * this.x + this.y * this.y);
  }

  double distTo(CartPt other) {
    return Math.sqrt(
        (this.x - other.x) * (this.x - other.x) +
            (this.y - other.y) * (this.y - other.y));
  }
}

interface IComposite {
  // compute the area of this shape
  double area();

  // compute the distance from this shape to the origin
  double distTo0();

  // is the given point within this shape?
  boolean in(CartPt p);

  // is this shape the same size as other, within delta?
  boolean same(IComposite other, double delta);

  // is this shape closer to origin than other?
  boolean closerTo(IComposite other);
}

// abstract class to lift common fields and methods
abstract class AComposite implements IComposite {
  CartPt loc;

  AComposite(CartPt loc) {
    this.loc = loc;
  }

  // distTo0 is the same for Square and Circle
  // (overridden in Circle)
  public double distTo0() {
    return this.loc.distTo0();
  }

  // same for ALL classes — lifted here!
  public boolean same(IComposite other, double delta) {
    return Math.abs(this.area() - other.area()) <= delta;
  }

  // closerTo for ALL classes — lifted here!
  public boolean closerTo(IComposite other) {
    return this.distTo0() < other.distTo0();
  }
}

class Square extends AComposite {
  int size;

  Square(CartPt loc, int size) {
    super(loc);
    this.size = size;
  }

  public double area() {
    return this.size * this.size;
  }

  public boolean in(CartPt p) {
    return (this.loc.x <= p.x) &&
        (p.x <= this.loc.x + this.size) &&
        (this.loc.y <= p.y) &&
        (p.y <= this.loc.y + this.size);
  }
}

class Circle extends AComposite {
  int radius;

  Circle(CartPt loc, int radius) {
    super(loc);
    this.radius = radius;
  }

  public double area() {
    return Math.PI * this.radius * this.radius;
  }

  // overrides AComposite.distTo0()
  public double distTo0() {
    return this.loc.distTo0() - this.radius;
  }

  public boolean in(CartPt p) {
    return this.loc.distTo(p) <= this.radius;
  }
}

class SuperImp implements IComposite {
  IComposite bot;
  IComposite top;

  SuperImp(IComposite bot, IComposite top) {
    this.bot = bot;
    this.top = top;
  }

  public double area() {
    return this.bot.area() + this.top.area();
  }

  public double distTo0() {
    return Math.min(this.bot.distTo0(), this.top.distTo0());
  }

  public boolean in(CartPt p) {
    return this.bot.in(p) || this.top.in(p);
  }

  public boolean same(IComposite other, double delta) {
    return Math.abs(this.area() - other.area()) <= delta;
  }

  public boolean closerTo(IComposite other) {
    return this.distTo0() < other.distTo0();
  }
}

class ExamplesComposite {
  ExamplesComposite() {
  }

  // basic shapes
  IComposite s1 = new Square(new CartPt(40, 30), 40);
  IComposite s2 = new Square(new CartPt(120, 50), 50);
  IComposite c1 = new Circle(new CartPt(50, 120), 20);
  IComposite c2 = new Circle(new CartPt(30, 40), 20);

  // superimposed shapes
  IComposite u1 = new SuperImp(s1, s2);
  IComposite u2 = new SuperImp(s1, c2);
  IComposite u3 = new SuperImp(c1, u1);
  IComposite u4 = new SuperImp(u3, u2);

  // extra examples
  IComposite smallSquare = new Square(new CartPt(0, 0), 5);
  IComposite bigSquare = new Square(new CartPt(0, 0), 10);
  IComposite smallCircle = new Circle(new CartPt(0, 0), 5);

  // test area
  boolean testArea(Tester t) {
    return t.checkInexact(this.s1.area(), 1600.0, 0.01) &&
        t.checkInexact(this.s2.area(), 2500.0, 0.01) &&
        t.checkInexact(this.c1.area(), Math.PI * 400, 0.01) &&
        t.checkInexact(this.c2.area(), Math.PI * 400, 0.01) &&
        // SuperImp area = sum of both
        t.checkInexact(this.u1.area(), 4100.0, 0.01) &&
        t.checkInexact(this.u2.area(), 1600.0 + Math.PI * 400, 0.01);
  }

  // test distTo0
  boolean testDistTo0(Tester t) {
    return
    // Square distTo0 = loc.distTo0()
    t.checkInexact(this.s1.distTo0(), 50.0, 0.01) &&
        t.checkInexact(this.s2.distTo0(), 130.0, 0.01) &&
        // Circle distTo0 = loc.distTo0() - radius
        t.checkInexact(this.c1.distTo0(), 110.0, 0.01) &&
        t.checkInexact(this.c2.distTo0(), 30.0, 0.01) &&
        // SuperImp distTo0 = min of both
        t.checkInexact(this.u1.distTo0(), 50.0, 0.01) &&
        t.checkInexact(this.u2.distTo0(), 30.0, 0.01);
  }

  // test in
  boolean testIn(Tester t) {
    return
    // Square contains point
    t.checkExpect(this.s1.in(new CartPt(42, 42)), true) &&
        t.checkExpect(this.s1.in(new CartPt(100, 100)), false) &&
        // Circle contains point
        t.checkExpect(this.c2.in(new CartPt(35, 45)), true) &&
        t.checkExpect(this.c2.in(new CartPt(100, 100)), false) &&
        // SuperImp contains point if either shape contains it
        t.checkExpect(this.u1.in(new CartPt(42, 42)), true) &&
        t.checkExpect(this.u2.in(new CartPt(45, 40)), true) &&
        t.checkExpect(this.u2.in(new CartPt(20, 5)), false);
  }

  // test same
  boolean testSame(Tester t) {
    return
    // same area within delta
    t.checkExpect(this.c1.same(this.c2, 0.01), true) &&
    // different areas
        t.checkExpect(this.s1.same(this.s2, 0.01), false) &&
        // within a large delta
        t.checkExpect(this.s1.same(this.s2, 1000.0), true) &&
        // square vs circle
        t.checkExpect(this.smallSquare.same(this.smallCircle, 50.0), true) &&
        t.checkExpect(this.smallSquare.same(this.bigSquare, 0.01), false);
  }

  // test closerTo
  boolean testCloserTo(Tester t) {
    return
        //
    t.checkExpect(this.c2.closerTo(th
    t.checkExpect(this.c1.closerTo(this.c2), false) &&
        // s1 is closer than s2
        t.checkExpect(this.s1.closerTo(this.s2), true) &&
        t.checkExpect(this.s2.closerTo(this.s1), false) &&
        // SuperImp uses min distance
        t.checkExpect(this.u2.closerTo(this.u1), true);
  }
}
