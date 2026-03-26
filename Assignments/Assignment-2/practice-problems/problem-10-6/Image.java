// represent information about an image
class Image {
  int width; // in pixels
  int height; // in pixels
  String source;

  Image(int width, int height, String source) {
    this.width = width;
    this.height = height;
    this.source = source;
  }

  // Determine whether this image is small or medium or large based on the number
  // of pixels
  String sizeString() {
    if (this.totalPixels() <= 10000) {
      return "small";
    } else if (this.totalPixels() <= 1000000) {
      return "medium";
    } else {
      return "large";
    }
  }

  int totalPixels() {
    return this.width * this.height;
  }
}

class ExamplesImage {
  ExamplesImage() {
  }

  Image i1 = new Image(100, 100, "Pic.png");
  Image i2 = new Image(10, 100, "Dog.png");
  Image i3 = new Image(10000, 100, "Cat.png");
}
