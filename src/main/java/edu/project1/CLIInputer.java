package edu.project1;

import java.util.Scanner;

public class CLIInputer implements Inputer {
    Scanner scanner = new Scanner(System.in);

    public String getLine() {
        return scanner.nextLine();
    }
}
