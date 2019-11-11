package io.github.hotspacode.neeza.agent.adapter;


import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class PrimitiveMethodAdapter extends AdviceAdapter {

    private Class returnClass;
    private int argumentTypeSize;

    public PrimitiveMethodAdapter(MethodVisitor mv, int access, String name, String desc, Class returnClass, int argumentTypeSize) {
        super(ASM7, mv, access, name, desc);
        this.returnClass = returnClass;
        this.argumentTypeSize = argumentTypeSize;
    }

    /**
     * 方法前置
     */
    @Override
    protected void onMethodEnter() {
        Label l0 = new Label();
        mv.visitLabel(l0);

        mv.visitTypeInsn(NEW, "java/util/ArrayList");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
        mv.visitVarInsn(ASTORE, argumentTypeSize);


        for (int i = 0; i < argumentTypeSize - 1; i++) {
            mv.visitLabel(new Label());
            mv.visitVarInsn(ALOAD, argumentTypeSize);
            mv.visitVarInsn(ALOAD, i + 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
        }


        Label l3 = new Label();
        mv.visitLabel(l3);


        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getStackTrace", "()[Ljava/lang/StackTraceElement;", false);
        mv.visitInsn(ICONST_1);
        mv.visitInsn(AALOAD);
        mv.visitVarInsn(ALOAD, argumentTypeSize);
        mv.visitMethodInsn(INVOKESTATIC, "io/github/hotspacode/neeza/deputy/core/MockSpy", "getMockData", "(Ljava/lang/StackTraceElement;Ljava/util/List;)Lio/github/hotspacode/neeza/deputy/dto/MockTransport;", false);
        mv.visitVarInsn(ASTORE, argumentTypeSize+1);
        Label l4 = new Label();
        mv.visitLabel(l4);

        mv.visitVarInsn(ALOAD, argumentTypeSize+1);
        mv.visitMethodInsn(INVOKEVIRTUAL, "io/github/hotspacode/neeza/deputy/dto/MockTransport", "isMocked", "()Z", false);
        Label l5 = new Label();
        mv.visitJumpInsn(IFEQ, l5);
        Label l6 = new Label();
        mv.visitLabel(l6);

        mv.visitVarInsn(ALOAD, argumentTypeSize+1);
        mv.visitInsn(POP);
        mv.visitVarInsn(ALOAD, argumentTypeSize+1);
        mv.visitMethodInsn(INVOKESTATIC, "io/github/hotspacode/neeza/deputy/dto/MockTransport", "getObject", "(Lio/github/hotspacode/neeza/deputy/dto/MockTransport;)Ljava/lang/Object;", false);


        String returnClassName = returnClass.getName().replace(".", "/");
        mv.visitTypeInsn(CHECKCAST, returnClassName);


        //byte、short、int、long、float、double、char、boolean
        if (Byte.class.equals(returnClass)) {
            mv.visitMethodInsn(INVOKEVIRTUAL, returnClassName, "byteValue", "()B", false);
            mv.visitInsn(IRETURN);
        } else if (Short.class.equals(returnClass)) {
            mv.visitMethodInsn(INVOKEVIRTUAL, returnClassName, "shortValue", "()S", false);
            mv.visitInsn(IRETURN);
        } else if (Integer.class.equals(returnClass)) {
            mv.visitMethodInsn(INVOKEVIRTUAL, returnClassName, "intValue", "()I", false);
            mv.visitInsn(IRETURN);
        } else if (Long.class.equals(returnClass)) {
            mv.visitMethodInsn(INVOKEVIRTUAL, returnClassName, "longValue", "()J", false);
            mv.visitInsn(IRETURN);
        } else if (Float.class.equals(returnClass)) {
            mv.visitMethodInsn(INVOKEVIRTUAL, returnClassName, "floatValue", "()F", false);
            mv.visitInsn(FRETURN);
        } else if (Double.class.equals(returnClass)) {
            mv.visitMethodInsn(INVOKEVIRTUAL, returnClassName, "doubleValue", "()D", false);
            mv.visitInsn(DRETURN);
        } else if (Character.class.equals(returnClass)) {
            mv.visitMethodInsn(INVOKEVIRTUAL, returnClassName, "charValue", "()C", false);
            mv.visitInsn(IRETURN);
        } else if (Boolean.class.equals(returnClass)) {
            mv.visitMethodInsn(INVOKEVIRTUAL, returnClassName, "booleanValue", "()Z", false);
            mv.visitInsn(IRETURN);
        }



        mv.visitLabel(l5);

        mv.visitFrame(F_APPEND, 2, new Object[]{"java/util/List", "io/github/hotspacode/neeza/deputy/dto/MockTransport"}, 0, null);



    }
}