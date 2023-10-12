package edu.hw2.task4;

public class Utility {
    private Utility() {
    }

    public static CallingInfo callingInfo() {
        Throwable e = new Throwable();
        StackTraceElement info = e.getStackTrace()[1];
        return new CallingInfo(info.getClassName(), info.getMethodName());
    }
}
