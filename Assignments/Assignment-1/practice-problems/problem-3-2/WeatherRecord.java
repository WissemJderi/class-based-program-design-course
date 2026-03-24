class Date {
  int day;
  int month;
  int year;

  Date(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }
}

class TemperatureRange {
  int high;
  int low;

  TemperatureRange(int high, int low) {
    this.high = high;
    this.low = low;
  }

  // is the given range within this range?
  boolean isWithin(TemperatureRange other) {
    return this.low >= other.low && this.high <= other.high;
  }

  // is this range broke the record?
  boolean breakRecord(TemperatureRange other) {
    return this.low < other.low || this.high > other.high;
  }
}

class WeatherRecord {
  Date d;
  TemperatureRange today;
  TemperatureRange normal;
  TemperatureRange record;
  double precipitation;

  WeatherRecord(Date d, TemperatureRange today, TemperatureRange normal, TemperatureRange record,
      double precipitation) {
    this.d = d;
    this.today = today;
    this.normal = normal;
    this.record = record;
    this.precipitation = precipitation;
  }

  /*
   * determines whether today’s high and low were
   * within the normal range
   */
  boolean withinRange() {
    return this.today.isWithin(this.normal);
  }

  /*
   * determines whether the precipitation is higher than
   * some given value
   */

  boolean rainyDay(double other) {
    return this.precipitation > other;
  }

  /*
   * etermines whether the temperature broke either
   * the high or the low record.
   */

  boolean recordDay() {
    return this.today.breakRecord(this.record);
  }
}
