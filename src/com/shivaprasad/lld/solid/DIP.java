package com.shivaprasad.lld.solid;

// Without DIP
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
public class DIP {
    public static void main(String[] args) {
        Database db = new MySQLDatabase1();
        DatabaseService1 service = new DatabaseService1(db);
        service.connectDatabase();
    }
}

//With DIP
interface Database {
    void connect();
}

class MySQLDatabase1 implements Database {
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

class DatabaseService1 {
    private Database db;

    public DatabaseService1(Database db) {
        this.db = db; // Dependency injection
    }

    public void connectDatabase() {
        db.connect();
    }
}
