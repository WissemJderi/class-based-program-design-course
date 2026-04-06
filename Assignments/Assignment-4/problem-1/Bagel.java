import tester.Tester;

class BagelRecipe {
  double flour;
  double water;
  double yeast;
  double salt;
  double malt;

  BagelRecipe(double flour, double water, double yeast,
      double salt, double malt) {
    if (Math.abs(flour - water) > 0.001) {
      throw new IllegalArgumentException(
          "Flour must equal water! flour=" + flour + " water=" + water);
    }
    if (Math.abs(yeast - malt) > 0.001) {
      throw new IllegalArgumentException(
          "Yeast must equal malt! yeast=" + yeast + " malt=" + malt);
    }
    if (Math.abs(flour / 20 - (salt + yeast)) > 0.001) {
      throw new IllegalArgumentException(
          "Salt + yeast must equal flour/20!");
    }
    this.flour = flour;
    this.water = water;
    this.yeast = yeast;
    this.salt = salt;
    this.malt = malt;
  }

  BagelRecipe(double flour, double yeast) {
    this(flour,
        flour,
        yeast,
        flour / 20 - yeast,
        yeast);
  }

  BagelRecipe(double flourVol, double yeastVol, double saltVol) {
    this(flourVol * 4.25,
        flourVol * 4.25,
        (yeastVol / 48) * 5,
        (saltVol / 48) * 10,
        (yeastVol / 48) * 5);
  }

  boolean sameRecipe(BagelRecipe other) {
    return Math.abs(this.flour - other.flour) <= 0.001
        && Math.abs(this.water - other.water) <= 0.001
        && Math.abs(this.yeast - other.yeast) <= 0.001
        && Math.abs(this.salt - other.salt) <= 0.001
        && Math.abs(this.malt - other.malt) <= 0.001;
  }
}

class ExamplesBagel {
  ExamplesBagel() {
  }

  BagelRecipe recipe1 = new BagelRecipe(20.0, 20.0, 0.5, 0.5, 0.5);
  BagelRecipe recipe2 = new BagelRecipe(20.0, 0.5);
  BagelRecipe recipe3 = new BagelRecipe(1.0, 1.0, 1.0);

  boolean testValidRecipes(Tester t) {
    return t.checkExpect(this.recipe1.sameRecipe(this.recipe2), true) &&
        t.checkInexact(this.recipe1.flour, 20.0, 0.001) &&
        t.checkInexact(this.recipe1.water, 20.0, 0.001) &&
        t.checkInexact(this.recipe2.flour, 20.0, 0.001) &&
        t.checkInexact(this.recipe2.malt, 0.5, 0.001);
  }

  boolean testVolumeConstructor(Tester t) {
    return t.checkInexact(this.recipe3.flour, 4.25, 0.001) &&
        t.checkInexact(this.recipe3.water, 4.25, 0.001);
  }

  boolean testExceptions(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException(
            "Flour must equal water! flour=20.0 water=10.0"),
        "BagelRecipe",
        20.0, 10.0, 0.5, 0.5, 0.5) &&
        t.checkConstructorException(
            new IllegalArgumentException(
                "Yeast must equal malt! yeast=0.5 malt=1.0"),
            "BagelRecipe",
            20.0, 20.0, 0.5, 0.5, 1.0)
        &&
        t.checkConstructorException(
            new IllegalArgumentException(
                "Salt + yeast must equal flour/20!"),
            "BagelRecipe",
            20.0, 20.0, 0.5, 1.0, 0.5);
  }
}