package edu.project1;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Game game = new CLIGame(new BuiltInDictionary());
        game.run();
    }
}
