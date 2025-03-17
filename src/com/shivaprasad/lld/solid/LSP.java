package com.shivaprasad.lld.solid;

// Without LSP
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

class Zoo {
    public static void main(String[] args) {
        Bird bird = new Ostrich();
        bird.fly(); // Runtime exception!
    }
}
public class LSP {
    // Above/Below Zoo/Zoo1 class's code will be here in general
}

// With LSP

abstract class Bird1 {
    public abstract void move();
}

class FlyingBird extends Bird1 {
    public void move() {
        System.out.println("I can fly!");
    }
}

class Ostrich1 extends Bird1 {
    public void move() {
        System.out.println("I cannot fly, but I can run!");
    }
}

class Zoo1 {
    public static void main(String[] args) {
        Bird1 ostrich = new Ostrich1();
        Bird1 sparrow = new FlyingBird();

        ostrich.move();  // Correct behavior: "I cannot fly, but I can run!"
        sparrow.move();  // Correct behavior: "I can fly!"
    }
}