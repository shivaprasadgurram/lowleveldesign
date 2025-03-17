# SOLID PRINCIPLES

* <b>S</b>ingle Responsibility Principle
* <b>O</b>pen/Closed Principle
* <b>L</b>iskov Substitution Principle
* <b>I</b>nterface Segregation Principle
* <b>D</b>ependency Inversion Principle

The SOLID principles are a set of five design principles that help developers write more maintainable, reusable, and scalable code. They are particularly important in object-oriented programming (OOP) and serve as a foundation for building robust and flexible systems.

### Single Responsibility Principle (SRP)
#### Definition:
1) A class should have only one reason to change.
2) A class must focus on a single functionality or responsibility.

#### Why it matters:
1) Enforces cohesion in your code.
2) Makes classes less prone to bugs since they have focused responsibilities.
3) Easier to understand, maintain, and test code.

#### Anti-pattern (What happens if SRP is violated):
If a single class does too much, changing one functionality could unintentionally break another. This leads to a ripple effect.

```
class Invoice {
    public void calculateTotal() {
        // Logic to calculate total.
    }

    public void printInvoice() {
        // Logic to format and print the invoice.
    }

    public void saveInvoiceToDB() {
        // Logic to save invoice to database.
    }
}
```

#### Issue:
The Invoice class is responsible for:

1) Business logic (calculateTotal)
2) Presentation (printInvoice)
3) Database persistence (saveInvoiceToDB)

If you want to modify how data is saved (e.g., change the database), you might accidentally break the printing logic.

#### Solution - Apply SRP:
```
class Invoice {
    public void calculateTotal() {
        // Logic to calculate total.
    }
}

class InvoicePrinter {
    public void printInvoice(Invoice invoice) {
        // Logic to format and print the invoice.
    }
}

class InvoicePersistence {
    public void saveInvoiceToDB(Invoice invoice) {
        // Logic to save invoice to database.
    }
}
```

Now:

1) If you need to change how invoices are saved, you modify InvoicePersistence.
2) If you need to change printing logic, you modify InvoicePrinter.

### Open/Closed Principle (OCP)

#### Definition:
Software entities (classes, modules, functions) should be open for extension, but closed for modification.

#### Why it matters:
1) Makes your code more extensible without modifying existing code.
2) Avoids breaking existing functionality.
3) Promotes the use of polymorphism.

#### Anti-pattern (What happens if OCP is violated):
If changes to functionality require modifying the existing class, it might break working code.

```
class DiscountCalculator {
    public double calculateDiscount(String userType, double totalPrice) {
        if ("regular".equals(userType)) {
            return totalPrice * 0.1; // 10% discount
        } else if ("premium".equals(userType)) {
            return totalPrice * 0.2; // 20% discount
        }
        return 0;
    }
}
```

#### Issue:
Adding a new user type requires modifying the calculateDiscount method. The class is not "closed for modification," and changes might inadvertently break existing functionality.

#### Solution - Apply OCP:
```
interface Discount {
    double apply(double totalPrice);
}

class RegularDiscount implements Discount {
    @Override
    public double apply(double totalPrice) {
        return totalPrice * 0.1; // 10% discount
    }
}

class PremiumDiscount implements Discount {
    @Override
    public double apply(double totalPrice) {
        return totalPrice * 0.2; // 20% discount
    }
}

class DiscountCalculator {
    public double calculateDiscount(Discount discount, double totalPrice) {
        return discount.apply(totalPrice);
    }
}
```
Now:
1) Adding new user types (e.g., SpecialDiscount) only requires implementing a new class, without modifying the existing code.
2) The DiscountCalculator stays unchanged.

### Liskov Substitution Principle (LSP)
#### Definition:
Subtypes must be substitutable for their base types.

#### Why it matters:
1) Ensures polymorphism works correctly.
2) Prevents fragile and unpredictable behavior when using inheritance.

#### Anti-pattern (What happens if LSP is violated):
If a subclass violates the expected behavior of its parent class, it can lead to incorrect, unexpected, or broken behavior.
```
class Bird {
    public void fly() {
        System.out.println("I can fly!");
    }
}

class Ostrich extends Bird {
    @Override
    public void fly() {
        // Ostrich cannot fly.
        throw new UnsupportedOperationException("I cannot fly!");
    }
}

public class Zoo {
    public static void main(String[] args) {
        Bird bird = new Ostrich();
        bird.fly(); // Runtime exception!
    }
}
```

#### Issue:
The Ostrich class violates LSP because it does not behave like other Bird subclasses. Not all birds can fly, so this hierarchy is flawed.

#### Solution - Apply LSP:
```
abstract class Bird {
    public abstract void move();
}

class FlyingBird extends Bird {
    public void move() {
        System.out.println("I can fly!");
    }
}

class Ostrich extends Bird {
    public void move() {
        System.out.println("I cannot fly, but I can run!");
    }
}

public class Zoo {
    public static void main(String[] args) {
        Bird ostrich = new Ostrich();
        Bird sparrow = new FlyingBird();

        ostrich.move();  // Correct behavior: "I cannot fly, but I can run!"
        sparrow.move();  // Correct behavior: "I can fly!"
    }
}
```

Now:
1) No assumptions about all birds being able to fly.
2) Subclasses behave according to their base class capabilities.

### Interface Segregation Principle (ISP)
#### Definition:
A class should not be forced to implement interfaces it doesn't use.

#### Why it matters:
1) Avoids bloated, monolithic interfaces.
2) Ensures minimal coupling between components.
3)Makes code easier to maintain.

#### Anti-pattern (What happens if ISP is violated):
If an interface contains methods irrelevant to some implementing classes, those classes may end up with empty or meaningless method implementations.

```
interface Animal {
    void run();
    void fly();
}

class Dog implements Animal {
    @Override
    public void run() {
        System.out.println("Dog runs");
    }

    @Override
    public void fly() {
        // Dogs can't fly
        throw new UnsupportedOperationException();
    }
}
```

#### Issue:
The Dog class is forced to implement the irrelevant fly method.

#### Solution - Apply ISP:
```
interface Runnable {
    void run();
}

interface Flyable {
    void fly();
}

class Dog implements Runnable {
    @Override
    public void run() {
        System.out.println("Dog runs");
    }
}

class Bird implements Runnable, Flyable {
    @Override
    public void run() {
        System.out.println("Bird can run");
    }

    @Override
    public void fly() {
        System.out.println("Bird can fly");
    }
}
```

Now:
1) Classes only implement what they need.
2) No meaningless methods or runtime exceptions.

### Dependency Inversion Principle (DIP)

#### Definition:
High-level modules should not depend on low-level modules. Both should depend on abstractions.

#### Why it matters:
1) Reduces coupling.
2) Makes the code more flexible and testable.

#### Anti-pattern (What happens if DIP is violated):
If high-level modules tightly depend on low-level modules, changes to low-level modules can break the high-level functionality.

```
class MySQLDatabase {
    public void connect() {
        System.out.println("Connecting to MySQL Database...");
    }
}

class DatabaseService {
    private MySQLDatabase db;

    public DatabaseService() {
        db = new MySQLDatabase(); // Hard-coded dependency
    }

    public void connectDatabase() {
        db.connect();
    }
}
```
#### Issue:
The DatabaseService is tightly coupled to MySQLDatabase. If you want to switch to another database, you must modify DatabaseService.

#### Solution - Apply DIP:

```
interface Database {
    void connect();
}

class MySQLDatabase implements Database {
    @Override
    public void connect() {
        System.out.println("Connecting to MySQL Database...");
    }
}

class PostgresDatabase implements Database {
    @Override
    public void connect() {
        System.out.println("Connecting to Postgres Database...");
    }
}

class DatabaseService {
    private Database db;

    public DatabaseService(Database db) {
        this.db = db; // Dependency injection
    }

    public void connectDatabase() {
        db.connect();
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Database db = new MySQLDatabase();
        DatabaseService service = new DatabaseService(db);
        service.connectDatabase();
    }
}
```

Now:
1) The dependency is abstracted into Database.
2) Switching databases (e.g., PostgresDatabase) is easy and requires no changes to DatabaseService.

#### By following the SOLID principles, you reduce coupling, enhance modularity, and make your code easier to maintain, extend, and test. Neglecting these principles can lead to spaghetti code, brittle systems, and higher costs of change. As a Java developer, mastering SOLID is essential to creating professional, scalable, and robust software systems.