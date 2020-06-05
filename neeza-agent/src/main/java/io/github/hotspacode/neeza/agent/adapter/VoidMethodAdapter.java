package io.github.hotspacode.neeza.agent.adapter;


import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class VoidMethodAdapter extends AdviceAdapter {

    private int argumentTypeSize;
    private String argumentTypeDescriptors;

    public VoidMethodAdapter(MethodVisitor mv, int access, String name, String desc,int argumentTypeSize,String argumentTypeDescriptors) {
        super(ASM5, mv, access, name, desc);
        this.argumentTypeSize = argumentTypeSize;
        this.argumentTypeDescriptors = argumentTypeDescriptors;
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



        for (int i = 0; i < argumentTypeSize-1; i++) {
            mv.visitLabel(new Label());
            mv.visitVarInsn(ALOAD, argumentTypeSize);
            mv.visitVarInsn(ALOAD, i+1);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
        }


        Label l3 = new Label();
        mv.visitLabel(l3);

        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getStackTrace", "()[Ljava/lang/StackTraceElement;", false);
        mv.visitInsn(ICONST_1);
        mv.visitInsn(AALOAD);
        mv.visitVarInsn(ALOAD, argumentTypeSize );
        mv.visitLdcInsn(argumentTypeDescriptors);
        mv.visitMethodInsn(INVOKESTATIC, "io/github/hotspacode/neeza/base/core/MockSpy", "getMockData", "(Ljava/lang/StackTraceElement;Ljava/util/List;Ljava/lang/String;)Lio/github/hotspacode/neeza/base/dto/MockTransport;", false);
        mv.visitVarInsn(ASTORE, argumentTypeSize +1);
        Label l4 = new Label();
        mv.visitLabel(l4);

        mv.visitVarInsn(ALOAD, argumentTypeSize +1);
        mv.visitMethodInsn(INVOKEVIRTUAL, "io/github/hotspacode/neeza/base/dto/MockTransport", "isMocked", "()Z", false);
        Label l5 = new Label();
        mv.visitJumpInsn(IFEQ, l5);
        Label l6 = new Label();
        mv.visitLabel(l6);

        mv.visitVarInsn(ALOAD, argumentTypeSize +1);
        mv.visitMethodInsn(INVOKEVIRTUAL, "io/github/hotspacode/neeza/base/dto/MockTransport", "isReturnVoid", "()Z", false);
        mv.visitJumpInsn(IFEQ, l5);
        Label l7 = new Label();
        mv.visitLabel(l7);

        mv.visitInsn(RETURN);
        mv.visitLabel(l5);

        mv.visitFrame(F_APPEND, 2, new Object[]{"java/util/List", "io/github/hotspacode/neeza/base/dto/MockTransport"}, 0, null);


    }
}