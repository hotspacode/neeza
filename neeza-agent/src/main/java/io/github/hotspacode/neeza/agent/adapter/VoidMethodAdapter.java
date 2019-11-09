package io.github.hotspacode.neeza.agent.adapter;


import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class VoidMethodAdapter extends AdviceAdapter {

    private int argumentTypeSize;
    public VoidMethodAdapter(MethodVisitor mv, int access, String name, String desc,int argumentTypeSize) {
        super(ASM7, mv, access, name, desc);
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
        mv.visitMethodInsn(INVOKESTATIC, "io/github/hotspacode/neeza/deputy/core/MockSpy", "getMockData", "(Ljava/lang/StackTraceElement;)Lio/github/hotspacode/neeza/deputy/dto/MockTransport;", false);
        mv.visitVarInsn(ASTORE, argumentTypeSize);
        Label l1 = new Label();
        mv.visitLabel(l1);
        mv.visitLineNumber(11, l1);
        mv.visitVarInsn(ALOAD, argumentTypeSize);
        mv.visitMethodInsn(INVOKEVIRTUAL, "io/github/hotspacode/neeza/deputy/dto/MockTransport", "isMocked", "()Z", false);
        Label l2 = new Label();
        mv.visitJumpInsn(IFEQ, l2);
        Label l3 = new Label();
        mv.visitLabel(l3);
        mv.visitLineNumber(12, l3);
        mv.visitVarInsn(ALOAD, argumentTypeSize);
        mv.visitMethodInsn(INVOKEVIRTUAL, "io/github/hotspacode/neeza/deputy/dto/MockTransport", "isReturnVoid", "()Z", false);
        mv.visitJumpInsn(IFEQ, l2);
        Label l4 = new Label();
        mv.visitLabel(l4);
        mv.visitLineNumber(13, l4);
        mv.visitInsn(RETURN);
        mv.visitLabel(l2);
        mv.visitLineNumber(18, l2);
        mv.visitFrame(F_APPEND, 1, new Object[]{"io/github/hotspacode/neeza/deputy/dto/MockTransport"}, 0, null);

    }
}