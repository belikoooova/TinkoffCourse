package edu.hw2.task4;

public record CallingInfo(String className, String methodName) {
    public static CallingInfo callingInfo() {
        Throwable e = new Throwable();
        StackTraceElement info = e.getStackTrace()[1];
        return new CallingInfo(info.getClassName(), info.getMethodName());
    }
}
