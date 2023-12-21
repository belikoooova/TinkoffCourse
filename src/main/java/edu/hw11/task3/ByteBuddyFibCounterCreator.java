package edu.hw11.task3;

import java.lang.reflect.Modifier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.Opcodes;

@SuppressWarnings({"AnonInnerLength", "LambdaBodyLength", "MagicNumber"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ByteBuddyFibCounterCreator {
    @SneakyThrows
    public static Class<?> createClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("edu.hw11.task3.ByteBuddyFibCounter")
            .defineMethod("fib", long.class, Modifier.PUBLIC)
            .withParameters(int.class)
            .intercept(new Implementation() {
                @Override
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override
                public ByteCodeAppender appender(Target target) {
                    return (mv, context, methodDescription) -> {
                        Label l1 = new Label();
                        Label l3 = new Label();
                        Label l7 = new Label();
                        Label l8 = new Label();

                        mv.visitCode();
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_1);
                        mv.visitJumpInsn(Opcodes.IF_ICMPNE, l1);

                        mv.visitInsn(Opcodes.LCONST_0);
                        mv.visitInsn(Opcodes.LRETURN);

                        mv.visitLabel(l1);
                        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitJumpInsn(Opcodes.IF_ICMPNE, l3);

                        mv.visitInsn(Opcodes.LCONST_1);
                        mv.visitInsn(Opcodes.LRETURN);

                        mv.visitLabel(l3);
                        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        mv.visitInsn(Opcodes.LCONST_0);
                        mv.visitVarInsn(Opcodes.LSTORE, 2);

                        mv.visitInsn(Opcodes.LCONST_1);
                        mv.visitVarInsn(Opcodes.LSTORE, 4);

                        mv.visitInsn(Opcodes.ICONST_0);
                        mv.visitVarInsn(Opcodes.ISTORE, 6);

                        mv.visitLabel(l7);
                        mv.visitFrame(
                            Opcodes.F_APPEND,
                            3,
                            new Object[] {Opcodes.LONG, Opcodes.LONG, Opcodes.INTEGER},
                            0,
                            null
                        );
                        mv.visitVarInsn(Opcodes.ILOAD, 6);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitInsn(Opcodes.ISUB);
                        mv.visitJumpInsn(Opcodes.IF_ICMPGE, l8);

                        mv.visitVarInsn(Opcodes.LLOAD, 4);
                        mv.visitVarInsn(Opcodes.LSTORE, 7);

                        mv.visitVarInsn(Opcodes.LLOAD, 4);
                        mv.visitVarInsn(Opcodes.LLOAD, 2);
                        mv.visitInsn(Opcodes.LADD);
                        mv.visitVarInsn(Opcodes.LSTORE, 4);

                        mv.visitVarInsn(Opcodes.LLOAD, 7);
                        mv.visitVarInsn(Opcodes.LSTORE, 2);

                        mv.visitIincInsn(6, 1);
                        mv.visitJumpInsn(Opcodes.GOTO, l7);

                        mv.visitLabel(l8);
                        mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
                        mv.visitVarInsn(Opcodes.LLOAD, 4);
                        mv.visitInsn(Opcodes.LRETURN);
                        mv.visitMaxs(0, 0);
                        mv.visitEnd();
                        return new ByteCodeAppender.Size(4, 9);
                    };
                }
            })
            .make()
            .load(ByteBuddyFibCounterCreator.class.getClassLoader())
            .getLoaded();
    }
}
