import tester.Tester;

interface IPicture {
  int getWidth();

  int countShapes();

  IPicture mirror();

  String pictureRecipe(int depth);
}

class Shape implements IPicture {
  String kind;
  int size;

  Shape(String kind, int size) {
    this.kind = kind;
    this.size = size;
  }

  public int getWidth() {
    return this.size;
  }

  public int countShapes() {
    return 1;
  }

  public IPicture mirror() {
    return new Shape(this.kind, this.size);
  }

  public String pictureRecipe(int depth) {
    return this.kind;

  }
}

class Combo implements IPicture {
  String name;
  IOperation operation;

  Combo(String name, IOperation operation) {
    this.name = name;
    this.operation = operation;
  }

  public int getWidth() {
    return this.operation.getWidthHelper();
  }

  public int countShapes() {
    return this.operation.countShapesHelper();
  }

  public IPicture mirror() {
    return this;
  }

  public String pictureRecipe(int depth) {
    if (depth <= 0) {
      return this.name;
    } else {
      return this.operation.recipeHelper(depth - 1);
    }

  }

}

interface IOperation {

  int getWidthHelper();

  int countShapesHelper();

  IOperation mirrorHelper();

  String recipeHelper(int depth);
}

class Scale implements IOperation {
  IPicture picture;

  Scale(IPicture picture) {
    this.picture = picture;
  }

  public int getWidthHelper() {
    return this.picture.getWidth() * 2;

  }

  public int countShapesHelper() {
    return this.picture.countShapes();
  }

  public IOperation mirrorHelper() {
    return new Scale(this.picture.mirror());
  }

  public String recipeHelper(int depth) {
    return "scale(" + this.picture.pictureRecipe(depth) + ")";
  }

}

class Beside implements IOperation {
  IPicture picture1;
  IPicture picture2;

  Beside(IPicture picture1, IPicture picture2) {
    this.picture1 = picture1;
    this.picture2 = picture2;
  }

  public int getWidthHelper() {
    return this.picture1.getWidth() + this.picture2.getWidth();
  }

  public int countShapesHelper() {
    return this.picture1.countShapes() + this.picture2.countShapes();
  }

  public IOperation mirrorHelper() {
    return new Beside(this.picture2.mirror(), this.picture1.mirror());
  }

  public String recipeHelper(int depth) {
    return "beside(" + this.picture1.pictureRecipe(depth) + ", " + this.picture2.pictureRecipe(depth) + ")";
  }

}

class Overlay implements IOperation {
  IPicture top;
  IPicture bottom;

  Overlay(IPicture top, IPicture bottom) {
    this.top = top;
    this.bottom = bottom;
  }

  public int getWidthHelper() {
    if (this.top.getWidth() > this.bottom.getWidth()) {
      return this.top.getWidth();
    }
    return this.bottom.getWidth();

  }

  public int countShapesHelper() {
    return this.top.countShapes() + this.bottom.countShapes();
  }

  public IOperation mirrorHelper() {
    return new Overlay(this.top.mirror(), this.bottom.mirror());
  }

  public String recipeHelper(int depth) {
    return "overlay(" + this.top.pictureRecipe(depth) + ", " + this.bottom.pictureRecipe(depth) + ")";
  }

}

class ExamplesPictures {
  IPicture circle = new Shape("circle", 20);
  IPicture square = new Shape("square", 30);
  IPicture bigCircle = new Combo("big circle", new Scale(circle));
  IPicture squareOnCircle = new Combo("square on circle",
      new Overlay(square, bigCircle));
  IPicture doubledSquareOnCircle = new Combo("doubled square on circle",
      new Beside(squareOnCircle, squareOnCircle));
  IPicture bigSquare = new Combo("big square", new Scale(this.square));
  IPicture circleAndSquare = new Combo("circle and square",
      new Beside(this.circle, this.square));

  IPicture circleOnSquare = new Combo("circle on square",
      new Overlay(this.circle, this.square));

  boolean testGetWidth(Tester t) {
    return t.checkExpect(this.circle.getWidth(), 20) &&
        t.checkExpect(this.square.getWidth(), 30) &&

        t.checkExpect(this.bigCircle.getWidth(), 40) &&

        t.checkExpect(this.squareOnCircle.getWidth(), 40) &&

        t.checkExpect(this.doubledSquareOnCircle.getWidth(), 80);
  }

  boolean testCountShapes(Tester t) {
    return t.checkExpect(this.circle.countShapes(), 1) &&
        t.checkExpect(this.square.countShapes(), 1) &&

        t.checkExpect(this.bigCircle.countShapes(), 1) &&

        t.checkExpect(this.squareOnCircle.countShapes(), 2) &&

        t.checkExpect(this.doubledSquareOnCircle.countShapes(), 4) &&

        t.checkExpect(this.bigSquare.countShapes(), 1) &&
        t.checkExpect(this.circleAndSquare.countShapes(), 2) &&
        t.checkExpect(this.circleOnSquare.countShapes(), 2);
  }

  boolean testMirror(Tester t) {

    IPicture mirrorDoubled = this.doubledSquareOnCircle.mirror();

    IPicture leftCircleRightSquare = new Combo("circle+square",
        new Beside(this.circle, this.square));
    IPicture mirrored = leftCircleRightSquare.mirror();

    return t.checkExpect(this.circle.mirror().getWidth(), this.circle.getWidth()) &&
        t.checkExpect(this.square.mirror().getWidth(), this.square.getWidth()) &&

        t.checkExpect(this.bigCircle.mirror().getWidth(), this.bigCircle.getWidth()) &&

        t.checkExpect(this.squareOnCircle.mirror().getWidth(), this.squareOnCircle.getWidth()) &&

        t.checkExpect(mirrorDoubled.getWidth(), this.doubledSquareOnCircle.getWidth()) &&

        t.checkExpect(mirrorDoubled.countShapes(), this.doubledSquareOnCircle.countShapes()) &&

        t.checkExpect(mirrored.getWidth(), leftCircleRightSquare.getWidth()) &&
        t.checkExpect(mirrored.mirror().getWidth(), leftCircleRightSquare.getWidth());
  }

  boolean testPictureRecipe(Tester t) {
    // Depth 0 returns the name of the top-level Combo
    return t.checkExpect(this.doubledSquareOnCircle.pictureRecipe(0),
        "doubled square on circle") &&

    // Depth 2: beside(overlay(square, big circle), overlay(square, big circle))
        t.checkExpect(this.doubledSquareOnCircle.pictureRecipe(2),
            "beside(overlay(square, big circle), overlay(square, big circle))")
        &&

        // Depth 3 or more: beside(overlay(square, scale(circle)), overlay(square,
        // scale(circle)))
        t.checkExpect(this.doubledSquareOnCircle.pictureRecipe(3),
            "beside(overlay(square, scale(circle)), overlay(square, scale(circle)))")
        &&
        t.checkExpect(this.doubledSquareOnCircle.pictureRecipe(4),
            "beside(overlay(square, scale(circle)), overlay(square, scale(circle)))")
        &&

        // Additional tests for other pictures
        // Shape returns its kind
        t.checkExpect(this.circle.pictureRecipe(0), "circle") &&
        t.checkExpect(this.circle.pictureRecipe(5), "circle") &&

        // Combo at depth 0 returns its name
        t.checkExpect(this.bigCircle.pictureRecipe(0), "big circle") &&
        // At depth 1, expand scale
        t.checkExpect(this.bigCircle.pictureRecipe(1), "scale(circle)") &&
        // At deeper depth, still scale(circle) because shape doesn't expand further
        t.checkExpect(this.bigCircle.pictureRecipe(2), "scale(circle)") &&

        // squareOnCircle: name at depth 0, overlay(square, big circle) at depth 1,
        // overlay(square, scale(circle)) at depth 2
        t.checkExpect(this.squareOnCircle.pictureRecipe(0), "square on circle") &&
        t.checkExpect(this.squareOnCircle.pictureRecipe(1), "overlay(square, big circle)") &&
        t.checkExpect(this.squareOnCircle.pictureRecipe(2), "overlay(square, scale(circle))");

  }
}
