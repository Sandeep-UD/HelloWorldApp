// src/Greeter.java

public class Greeter {
    private String name;

    // Constructor
    public Greeter(String name) {
        this.name = name;
    }

    // Method to generate greeting message
    public String sayHello() {
        return "Hello, " + name + "! Welcome to Canarys.";
    }
}
