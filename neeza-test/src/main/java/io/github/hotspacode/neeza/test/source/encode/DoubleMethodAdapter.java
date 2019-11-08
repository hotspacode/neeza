package io.github.hotspacode.neeza.test.source.encode;


import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class DoubleMethodAdapter extends AdviceAdapter {

    private String returnClassType;

    public DoubleMethodAdapter(MethodVisitor mv, int access, String name, String desc, String returnClassType) {
        super(ASM7, mv, access, name, desc);
        this.returnClassType = returnClassType;
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
        mv.visitMethodInsn(INVOKESTATIC, "io/github/hotspacode/neeza/core/api/MockApi", "getMockData", "(Ljava/lang/StackTraceElement;)Lio/github/hotspacode/neeza/core/dto/MethodSpiResponseDTO;", false);
        mv.visitVarInsn(ASTORE, 1);
        Label l1 = new Label();
        mv.visitLabel(l1);
        mv.visitLineNumber(11, l1);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKEVIRTUAL, "io/github/hotspacode/neeza/core/dto//MethodSpiResponseDTO", "isMocked", "()Z", false);
        Label l2 = new Label();
        mv.visitJumpInsn(IFEQ, l2);
        Label l3 = new Label();
        mv.visitLabel(l3);
        mv.visitLineNumber(12, l3);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitInsn(POP);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "io/github/hotspacode/neeza/core/dto//MethodSpiResponseDTO", "getObject", "(Lio/github/hotspacode/neeza/core/dto/MethodSpiResponseDTO;)Ljava/lang/Object;", false);
        mv.visitTypeInsn(CHECKCAST, "java/lang/Double");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Double", "doubleValue", "()D", false);
        mv.visitInsn(DRETURN);
        mv.visitLabel(l2);
        mv.visitLineNumber(15, l2);
        mv.visitFrame(F_APPEND, 1, new Object[]{"io/github/hotspacode/neeza/core/dto//MethodSpiResponseDTO"}, 0, null);

    }
}