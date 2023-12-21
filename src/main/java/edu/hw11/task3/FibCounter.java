package edu.hw11.task3;

public class FibCounter {
    public long fib(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        long a = 0;
        long b = 1;
        for (int i = 0; i < n - 2; ++i) {
            long temp = b;
            b += a;
            a = temp;
        }
        return b;
    }
}

/*
access flags 0x1
public fib(I)J
    // parameter  n
    L0
    LINENUMBER 5 L0
    ILOAD 1
    ICONST_1
    IF_ICMPNE L1
    L2
    LINENUMBER 6 L2
    LCONST_0
    LRETURN
    L1
    LINENUMBER 8 L1
    FRAME SAME
    ILOAD 1
    ICONST_2
    IF_ICMPNE L3
    L4
    LINENUMBER 9 L4
    LCONST_1
    LRETURN
    L3
    LINENUMBER 11 L3
    FRAME SAME
    LCONST_0
    LSTORE 2
    L5
    LINENUMBER 12 L5
    LCONST_1
    LSTORE 4
    L6
    LINENUMBER 13 L6
    ICONST_0
    ISTORE 6
    L7
    FRAME APPEND [J J I]
    ILOAD 6
    ILOAD 1
    ICONST_2
    ISUB
    IF_ICMPGE L8
    L9
    LINENUMBER 14 L9
    LLOAD 4
    LSTORE 7
    L10
    LINENUMBER 15 L10
    LLOAD 4
    LLOAD 2
    LADD
    LSTORE 4
    L11
    LINENUMBER 16 L11
    LLOAD 7
    LSTORE 2
    L12
    LINENUMBER 13 L12
    IINC 6 1
    GOTO L7
    L8
    LINENUMBER 18 L8
    FRAME CHOP 1
    LLOAD 4
    LRETURN
    L13
    LOCALVARIABLE temp J L10 L12 7
    LOCALVARIABLE i I L7 L8 6
    LOCALVARIABLE this Ledu/hw11/task3/FibCounter; L0 L13 0
    LOCALVARIABLE n I L0 L13 1
    LOCALVARIABLE a J L5 L13 2
    LOCALVARIABLE b J L6 L13 4
    MAXSTACK = 4
    MAXLOCALS = 9
    }*/
