interface IShape {
  // to compute the area of this shape
  double area();

  // to compute the distance of
  // this shape to the origin
  double distTo0();

  // is the given point within?
  // the bounds of this shape
  boolean in(CartPt p);

  // compute the bounding box
  // for this shape
  Square bb();
}

class Square implements IShape {
  CartPt loc;
  double size;

  Square(CartPt loc, double size) {
    this.loc = loc;
    this.size = size;
  }

public double area() {
return this.size ∗ this.size;
}

  public double distTo0() {
    return this.loc.distTo0();
  }

  public boolean in(CartPt p) {
    return this.between(this.loc.x, p.x, this.size)
        &&
        this.between(this.loc.y, p.y, this.size);
  }

  public Square bb() {
    return this;
  }

  // is x in the interval [lft,lft+wdth]?
  boolean between(int lft, int x, int wdth) {
    return lft <= x && x <= lft + wdth;
  }
}

class CartPt {
  int x;
  int y;

  CartPt(int x, int y) {
    this.x = x;
    this.y = y;
  }

  // to compute the distance of this point to the origin

double distTo0(){
return Math.sqrt( (this.x ∗ this.x) + (this.y ∗ this.y));
}

  // are this CartPt and p the same?
  boolean same(CartPt p) {
    return (this.x == p.x) && (this.y == p.y);
  }

  // compute the distance between this CartPt and p
double distanceTo(CartPt p){
return
Math.sqrt((this.x − p.x) ∗ (this.x − p.x) + (this.y − p.y) ∗ (this.y − p.y));
}

  // create a point that is delta pixels (up,left) from this
CartPt translate(int delta) {
return new CartPt(this.x − delta, this.y − delta);
}
}
