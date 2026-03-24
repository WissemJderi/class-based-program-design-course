interface IBankAccount {
}

class CheckingAccount implements IBankAccount {
  int id;
  String name;
  double minBalance;
  double currentBalance;

  CheckingAccount(int id, String name, double minBalance, double currentBalance) {
    this.id = id;
    this.name = name;
    this.minBalance = minBalance;
    this.currentBalance = currentBalance;
  }
}

class SavingsAccount implements IBankAccount {
  int id;
  String name;
  double interestRate;
  double currentBalance;

  SavingsAccount(int id, String name, double interestRate, double currentBalance) {
    this.id = id;
    this.name = name;
    this.interestRate = interestRate;
    this.currentBalance = currentBalance;
  }
}

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

class CD implements IBankAccount {
  int id;
  String name;
  double interestRate;
  double currentBalance;
  Date maturityDate;

  CD(int id, String name, double interestRate, double currentBalance, Date maturityDate) {
    this.id = id;
    this.name = name;
    this.interestRate = interestRate;
    this.currentBalance = currentBalance;
    this.maturityDate = maturityDate;
  }
}

class ExamplesBankAccounts {
  ExamplesBankAccounts() {

  }

  IBankAccount acc1 = new CheckingAccount(1729, "Earl Gray", 500, 1250);

  Date d = new Date(1, 6, 2005);
  IBankAccount acc2 = new CD(4104, "Ima Flatt", 4, 10123, d);

  IBankAccount acc3 = new SavingsAccount(2992, "Annie Proulx", 3.5, 800);

}
