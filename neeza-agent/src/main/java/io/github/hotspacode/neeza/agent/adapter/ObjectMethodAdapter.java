package io.github.hotspacode.neeza.agent.adapter;


import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class ObjectMethodAdapter extends AdviceAdapter {

    private Class returnClass;
    private int argumentTypeSize;
    private String argumentTypeDescriptors;

    public ObjectMethodAdapter(MethodVisitor mv, int access, String name, String desc, Class returnClass, int argumentTypeSize,String argumentTypeDescriptors) {
        super(ASM7, mv, access, name, desc);
        this.returnClass = returnClass;
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
        mv.visitVarInsn(ALOAD, argumentTypeSize);
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
        mv.visitInsn(POP);
        mv.visitVarInsn(ALOAD, argumentTypeSize +1);
        mv.visitMethodInsn(INVOKESTATIC, "io/github/hotspacode/neeza/base/dto/MockTransport", "getObject", "(Lio/github/hotspacode/neeza/base/dto/MockTransport;)Ljava/lang/Object;", false);
        mv.visitTypeInsn(CHECKCAST, returnClass.getName().replace(".", "/"));
        mv.visitInsn(ARETURN);
        mv.visitLabel(l5);

        mv.visitFrame(F_APPEND, 2, new Object[]{"java/util/List", "io/github/hotspacode/neeza/base/dto/MockTransport"}, 0, null);

    }

    @Override
    public void visitLocalVariable(String name, String desc, String signature,
                                   Label start, Label end, int index) {
        if ("this".equals(name)) {
            return;
        }
        System.out.println("访问到方法参数名称：" + name);
    }
}