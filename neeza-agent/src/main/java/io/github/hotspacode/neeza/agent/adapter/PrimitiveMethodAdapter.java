package io.github.hotspacode.neeza.agent.adapter;


import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class PrimitiveMethodAdapter extends AdviceAdapter {

    private Class returnClass;
    private int argumentTypeSize;

    public PrimitiveMethodAdapter(MethodVisitor mv, int access, String name, String desc, Class returnClass,int argumentTypeSize) {
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
        mv.visitLineNumber(10, l0);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getStackTrace", "()[Ljava/lang/StackTraceElement;", false);
        mv.visitInsn(ICONST_1);
        mv.visitInsn(AALOAD);
        mv.visitMethodInsn(INVOKESTATIC, "top/moxingwang/simplemock/core/api/MockApi", "getMockData", "(Ljava/lang/StackTraceElement;)Ltop/moxingwang/simplemock/core/dto/MethodSpiResponseDTO;", false);
        mv.visitVarInsn(ASTORE, argumentTypeSize);
        Label l1 = new Label();
        mv.visitLabel(l1);
        mv.visitLineNumber(11, l1);
        mv.visitVarInsn(ALOAD, argumentTypeSize);
        mv.visitMethodInsn(INVOKEVIRTUAL, "top/moxingwang/simplemock/core/dto/MethodSpiResponseDTO", "isMocked", "()Z", false);
        Label l2 = new Label();
        mv.visitJumpInsn(IFEQ, l2);
        Label l3 = new Label();
        mv.visitLabel(l3);
        mv.visitLineNumber(12, l3);
        mv.visitVarInsn(ALOAD, argumentTypeSize);
        mv.visitInsn(POP);
        mv.visitVarInsn(ALOAD, argumentTypeSize);
        mv.visitMethodInsn(INVOKESTATIC, "top/moxingwang/simplemock/core/dto/MethodSpiResponseDTO", "getObject", "(Ltop/moxingwang/simplemock/core/dto/MethodSpiResponseDTO;)Ljava/lang/Object;", false);
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

        mv.visitLabel(l2);
        mv.visitLineNumber(15, l2);
        mv.visitFrame(F_APPEND, 1, new Object[]{"top/moxingwang/simplemock/core/dto/MethodSpiResponseDTO"}, 0, null);
    }
}