package edu.project1;

public class UnitTestReader implements Reader {
    private final String[] strings;
    private int index = 0;

    public UnitTestReader(String[] strings) {
        this.strings = strings;
    }

    @Override
    public String getLine() {
        return strings[index++];
    }
}
