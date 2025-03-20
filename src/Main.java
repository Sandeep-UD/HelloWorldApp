// src/Main.java

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Hello Canarys: ");
        String name = scanner.nextLine();

        // Use Greeter class
        Greeter greeter = new Greeter(name);
        System.out.println(greeter.sayHello());

        // File I/O
        System.out.println("\nWriting to file...");
        FileHandler.writeToFile("data.txt", "Hello, " + name);

        System.out.println("Reading from file...");
        String content = FileHandler.readFromFile("data.txt");
        System.out.println("File content: " + content);

        scanner.close();
    }
}
