import tester.Tester;

// a list of integers
interface ILin {
  // how many times does i appear in this list?
  int howMany(int i);

  // how many elements are in this list?
  int size();

  // remove one occurrence of i from this list
  ILin rem(int i);
}

// empty list of integers
class MTin implements ILin {
  MTin() {
  }

  public int howMany(int i) {
    return 0;
  }

  public int size() {
    return 0;
  }

  public ILin rem(int i) {
    return this; // nothing to remove
  }
}

// non-empty list of integers
class Cin implements ILin {
  int first;
  ILin rest;

  Cin(int first, ILin rest) {
    this.first = first;
    this.rest = rest;
  }

  public int howMany(int i) {
    if (this.first == i) {
      return 1 + this.rest.howMany(i);
    } else {
      return this.rest.howMany(i);
    }
  }

  public int size() {
    return 1 + this.rest.size();
  }

  public ILin rem(int i) {
    if (this.first == i) {
      return this.rest; // remove first occurrence only
    } else {
      return new Cin(this.first, this.rest.rem(i));
    }
  }
}

// common interface for Set and Bag
interface ICollection {
  // is i a member of this collection?
  boolean in(int i);

  // how many elements does this collection contain?
  int size();

  // remove one occurrence of i from this collection
  ICollection rem(int i);
}

// abstract superclass for Set and Bag
abstract class ACollection implements ICollection {
  ILin elements;

  ACollection(ILin elements) {
    this.elements = elements;
  }

  public boolean in(int i) {
    return this.elements.howMany(i) > 0;
  }

  public int size() {
    return this.elements.size();
  }

  // rem is abstract — different behavior for Set and Bag
  public abstract ICollection rem(int i);
}

// a set of integers — each element at most once
class Set extends ACollection {

  Set(ILin elements) {
    super(elements);
  }

  // add i to this set unless already present
  public Set add(int i) {
    if (this.in(i)) {
      return this;
    } else {
      return new Set(new Cin(i, this.elements));
    }
  }

  // remove i from this set
  public ICollection rem(int i) {
    return new Set(this.elements.rem(i));
  }
}

// a bag of integers — elements may appear multiple times
class Bag extends ACollection {

  Bag(ILin elements) {
    super(elements);
  }

  // add i to this bag
  public Bag add(int i) {
    return new Bag(new Cin(i, this.elements));
  }

  // how many times does i appear in this bag?
  public int howMany(int i) {
    return this.elements.howMany(i);
  }

  // remove one occurrence of i from this bag
  public ICollection rem(int i) {
    return new Bag(this.elements.rem(i));
  }
}

class ExamplesCollections {
  ExamplesCollections() {
  }

  // empty list
  ILin empty = new MTin();

  // lists of integers
  ILin list1 = new Cin(1, new Cin(2, new Cin(3, empty)));
  ILin list2 = new Cin(1, new Cin(1, new Cin(2, empty)));

  // sets
  Set emptySet = new Set(empty);
  Set set1 = new Set(list1); // {1, 2, 3}
  Set set2 = emptySet.add(1).add(2).add(3);
  Set set3 = emptySet.add(5).add(10).add(15);

  // bags
  Bag emptyBag = new Bag(empty);
  Bag bag1 = new Bag(list1); // [1, 2, 3]
  Bag bag2 = new Bag(list2); // [1, 1, 2]
  Bag bag3 = emptyBag.add(5).add(5).add(10);

  // test howMany in ILin
  boolean testHowMany(Tester t) {
    return t.checkExpect(this.empty.howMany(1), 0) &&
        t.checkExpect(this.list1.howMany(1), 1) &&
        t.checkExpect(this.list2.howMany(1), 2) && // appears twice!
        t.checkExpect(this.list1.howMany(5), 0); // not in list
  }

  // test size in ILin
  boolean testLinSize(Tester t) {
    return t.checkExpect(this.empty.size(), 0) &&
        t.checkExpect(this.list1.size(), 3) &&
        t.checkExpect(this.list2.size(), 3);
  }

  // test rem in ILin
  boolean testLinRem(Tester t) {
    return t.checkExpect(this.list1.rem(1),
        new Cin(2, new Cin(3, empty))) &&
        t.checkExpect(this.list2.rem(1),
            new Cin(1, new Cin(2, empty)))
        && // only removes first!
        t.checkExpect(this.empty.rem(1), empty);
  }

  // test in for Set
  boolean testSetIn(Tester t) {
    return t.checkExpect(this.set1.in(1), true) &&
        t.checkExpect(this.set1.in(5), false) &&
        t.checkExpect(this.emptySet.in(1), false);
  }

  // test add for Set
  boolean testSetAdd(Tester t) {
    return
    // adding existing element → no change
    t.checkExpect(this.set1.add(1), this.set1) &&
    // adding new element → set grows
        t.checkExpect(this.emptySet.add(1),
            new Set(new Cin(1, empty)));
  }

  // test size for Set
  boolean testSetSize(Tester t) {
    return t.checkExpect(this.emptySet.size(), 0) &&
        t.checkExpect(this.set1.size(), 3) &&
        t.checkExpect(this.set2.size(), 3);
  }

  // test rem for Set
  boolean testSetRem(Tester t) {
    return t.checkExpect(this.emptySet.rem(1), new Set(empty)) &&
        t.checkExpect(this.set1.rem(1),
            new Set(new Cin(2, new Cin(3, empty))));
  }

  // test in for Bag
  boolean testBagIn(Tester t) {
    return t.checkExpect(this.bag1.in(1), true) &&
        t.checkExpect(this.bag1.in(5), false) &&
        t.checkExpect(this.emptyBag.in(1), false);
  }

  // test add for Bag
  boolean testBagAdd(Tester t) {
    return
    // adding always works — even duplicates!
    t.checkExpect(this.emptyBag.add(1),
        new Bag(new Cin(1, empty))) &&
        t.checkExpect(this.bag1.add(1),
            new Bag(new Cin(1, list1)));
  }

  // test howMany for Bag
  boolean testBagHowMany(Tester t) {
    return t.checkExpect(this.bag2.howMany(1), 2) && // appears twice!
        t.checkExpect(this.bag1.howMany(1), 1) &&
        t.checkExpect(this.bag1.howMany(5), 0);
  }

  // test size for Bag
  boolean testBagSize(Tester t) {
    return t.checkExpect(this.emptyBag.size(), 0) &&
        t.checkExpect(this.bag1.size(), 3) &&
        t.checkExpect(this.bag2.size(), 3) &&
        t.checkExpect(this.bag3.size(), 3);
  }

  // test rem for Bag
  boolean testBagRem(Tester t) {
    return
    // removes only ONE occurrence!
    t.checkExpect(this.bag2.rem(1),
        new Bag(new Cin(1, new Cin(2, empty)))) &&
        t.checkExpect(this.emptyBag.rem(1), new Bag(empty));
  }
}
