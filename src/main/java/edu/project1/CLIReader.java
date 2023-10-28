package edu.project1;

import java.util.Scanner;

public class CLIReader implements Reader {
    private final Scanner scanner = new Scanner(System.in);

    public String getLine() {
        return scanner.nextLine();
    }
}
