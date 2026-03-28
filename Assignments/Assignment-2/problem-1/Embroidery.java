import tester.Tester;

class EmbroideryPiece {
  String name;
  IMotif motif;

  EmbroideryPiece(String name, IMotif motif) {
    this.name = name;
    this.motif = motif;
  }

  double averageDifficulty() {
    return this.motif.totalDifficulty() / this.motif.count();
  }

  String embroideryInfo() {
    return name + ":" + " " + this.motif.info();
  }
}

interface IMotif {
  int count();

  double totalDifficulty();

  String info();
}

class CrossStitchMotif implements IMotif {
  String description;
  double difficulty; // a number between 0 and 5, with 5 being the most difficult
  String type = "cross stitch";

  CrossStitchMotif(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }

  public int count() {
    return 1;
  }

  public double totalDifficulty() {
    return this.difficulty;
  }

  public String info() {
    return this.description + " " + "(" + this.type + ")";
  }

}

class ChainStitchMotif implements IMotif {
  String description;
  double difficulty; // a number between 0 and 5, with 5 being the most difficult
  String type = "chain stitch";

  ChainStitchMotif(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }

  public int count() {
    return 1;
  }

  public double totalDifficulty() {
    return this.difficulty;
  }

  public String info() {

    return this.description + " " + "(" + this.type + ")";
  }

}

class GroupMotif implements IMotif {
  String description;
  ILoMotif motifs;

  GroupMotif(String description, ILoMotif motifs) {
    this.description = description;
    this.motifs = motifs;
  }

  public int count() {
    return this.motifs.countHelper();
  }

  public double totalDifficulty() {
    return this.motifs.totalDifficultyHelper();
  }

  public String info() {
    return this.motifs.infoHelper();
  }
}

interface ILoMotif {
  int countHelper();

  double totalDifficultyHelper();

  String infoHelper();

  boolean isEmpty(); // ← NEW!
}

class MtLoMotif implements ILoMotif {
  MtLoMotif() {
  }

  public int countHelper() {
    return 0;
  }

  public double totalDifficultyHelper() {
    return 0;
  }

  public String infoHelper() {
    return ".";
  }

  public boolean isEmpty() {
    return true;
  }
}

class ConsLoMotif implements ILoMotif {
  IMotif first;
  ILoMotif rest;

  ConsLoMotif(IMotif first, ILoMotif rest) {
    this.first = first;
    this.rest = rest;
  }

  public int countHelper() {
    return this.first.count() + this.rest.countHelper();
  }

  public double totalDifficultyHelper() {
    return this.first.totalDifficulty() + this.rest.totalDifficultyHelper();
  }

  public String infoHelper() {
    if (this.rest.isEmpty()) {
      return this.first.info() + ".";
    }

    else {
      return this.first.info() + ", " + this.rest.infoHelper();
    }
  }

  public boolean isEmpty() {
    return false;
  }

}

class ExamplesEmbroidery {
  ExamplesEmbroidery() {
  }

  IMotif bird = new CrossStitchMotif("bird", 4.5);
  IMotif tree = new ChainStitchMotif("tree", 3.0);
  IMotif rose = new CrossStitchMotif("rose", 5.0); // changed
  IMotif poppy = new ChainStitchMotif("poppy", 4.75);
  IMotif daisy = new CrossStitchMotif("daisy", 3.2); // changed

  ILoMotif empty = new MtLoMotif();

  ILoMotif loflowers = new ConsLoMotif(rose,
      new ConsLoMotif(poppy,
          new ConsLoMotif(daisy, empty)));

  IMotif flowers = new GroupMotif("flowers", loflowers);

  IMotif nature = new GroupMotif("nature",
      new ConsLoMotif(bird, new ConsLoMotif(tree, new ConsLoMotif(flowers, empty))));

  EmbroideryPiece pillowCover = new EmbroideryPiece("Pillow Cover", nature);

  boolean testAverageDifficulty(Tester t) {
    return t.checkExpect(this.pillowCover.averageDifficulty(), 4.09);
  }

  boolean testCount(Tester t) {
    return t.checkExpect(this.nature.count(), 5) && t.checkExpect(this.flowers.count(), 3);
  }

  boolean testEmbroideryInfo(Tester t) {
    return t.checkExpect(this.pillowCover.embroideryInfo(),
        "Pillow Cover: bird (cross stitch), tree (chain stitch), rose (cross stitch), poppy (chain stitch), daisy (cross stitch).");
  }
}
