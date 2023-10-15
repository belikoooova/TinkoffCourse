package edu.project1;

public class UnitTestInputer implements Inputer {
    private final String[] strings;
    private int index = 0;

    public UnitTestInputer(String[] strings) {
        this.strings = strings;
    }

    @Override
    public String getLine() {
        return strings[index++];
    }
}
