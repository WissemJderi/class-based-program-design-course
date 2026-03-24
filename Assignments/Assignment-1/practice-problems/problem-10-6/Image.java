class Image {
  int width; // in pixels
  int height; // in pixels
  String source;

  Image(int width, int height, String source) {
    this.width = width;
    this.height = height;
    this.source = source;
  }

  /*
   * produces one of three strings,
   * depending on the number of pixels in the image:
   * 1. "small" for images with 10,000 pixels or fewer;
   * 2. "medium" for images with between 10,001 and 1,000,000 pixels;
   * 3. "large" for images that are even larger than that.
   */

  String sizeString() {
    int pixels = this.width * this.height;

    if (pixels <= 10000) {
      return "small";
    }
    if (pixels <= 1000000) {
      return "medium";
    } else {
      return "large";
    }
  }
}
