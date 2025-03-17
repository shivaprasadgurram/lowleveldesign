package com.shivaprasad.lld.solid;

// Without ISP
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
public class ISP {
    // Just main method code
}

// With ISP
interface Runnable {
    void run();
}

interface Flyable {
    void fly();
}

class Dog1 implements Runnable {
    @Override
    public void run() {
        System.out.println("Dog runs");
    }
}

class Bird2 implements Runnable, Flyable {
    @Override
    public void run() {
        System.out.println("Bird2 can run");
    }

    @Override
    public void fly() {
        System.out.println("Bird2 can fly");
    }
}