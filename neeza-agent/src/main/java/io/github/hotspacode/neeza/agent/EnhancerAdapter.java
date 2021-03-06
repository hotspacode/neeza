package io.github.hotspacode.neeza.agent;

import io.github.hotspacode.neeza.agent.adapter.ObjectMethodAdapter;
import io.github.hotspacode.neeza.agent.adapter.PrimitiveMethodAdapter;
import io.github.hotspacode.neeza.agent.adapter.VoidMethodAdapter;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class EnhancerAdapter extends ClassVisitor implements Opcodes {

    public static final String NEEZA_MOCK_ANNOTATION_NAME = "io/github/hotspacode/neeza/base/annotation/NeezaMock";

    private boolean isInterface;
    private boolean isAnnotationType = false;


    public EnhancerAdapter(ClassVisitor classVisitor) {
        super(ASM5, classVisitor);
    }


    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        isInterface = (access & ACC_INTERFACE) != 0;
    }

    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        System.out.println("访问注解" + descriptor);

        System.out.println(descriptor);

        if (descriptor.contains(NEEZA_MOCK_ANNOTATION_NAME)) {
            isAnnotationType = true;
        }
        return this.cv != null ? this.cv.visitAnnotation(descriptor, visible) : null;
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
                                     String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);

        System.out.println("准备访问方法: " + Type.getReturnType(descriptor).getSort() + "--" + name + "--" + descriptor);


        if (isAnnotationType && !isInterface && mv != null && !name.equals("<init>") && !name.equals("<clinit>")) {
            System.out.println("访问成功: " + Type.getReturnType(descriptor).getSort() + "--" + name + "--" + descriptor);

            int methodReturnType = Type.getReturnType(descriptor).getSort();

            AdviceAdapter adviceAdapter = null;

            Type[] argumentTypes = Type.getArgumentTypes(descriptor);
            int argumentTypeSize = argumentTypes.length + 1;

            String returnClassName = Type.getReturnType(descriptor).getClassName();

            System.out.println("真正访问方法: " + Type.getReturnType(descriptor).getSort() + "--" + name + "--" + descriptor + "--" + "返回class:" +
                    Type.getReturnType(descriptor).getClassName() + "---" + "分发methodReturnType：" + methodReturnType + "returnClassType：" + returnClassName);


            String argumentTypeDescriptors = "";
            if (argumentTypes.length > 0) {
                argumentTypeDescriptors = Stream.of(argumentTypes).map(Type::getDescriptor).collect(Collectors.joining(","));
            }

            //byte、short、int、long、float、double、char、boolean 、void 、object 、Array
//            mv.visitLocalVariable();


            if (Type.VOID == methodReturnType) {
                adviceAdapter = new VoidMethodAdapter(mv, access, name, descriptor, argumentTypeSize, argumentTypeDescriptors);
            } else {
                Class returnClass = null;

                boolean isPrimitive = false;
                try {
                    String className = Type.getReturnType(descriptor).getClassName();
                    if (!className.contains(".")) {
                        isPrimitive = true;
                    }

                    if (Type.BYTE == methodReturnType) {
                        returnClass = Byte.class;
                    } else if (Type.SHORT == methodReturnType) {
                        returnClass = Short.class;
                    } else if (Type.INT == methodReturnType) {
                        returnClass = Integer.class;
                    } else if (Type.LONG == methodReturnType) {
                        returnClass = Long.class;
                    } else if (Type.FLOAT == methodReturnType) {
                        returnClass = Float.class;
                    } else if (Type.DOUBLE == methodReturnType) {
                        returnClass = Double.class;
                    } else if (Type.CHAR == methodReturnType) {
                        returnClass = Character.class;
                    } else if (Type.BOOLEAN == methodReturnType) {
                        returnClass = Boolean.class;
                    } else {
                        returnClass = Class.forName(className);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return adviceAdapter;
                }

                if (isPrimitive) {
                    adviceAdapter = new PrimitiveMethodAdapter(mv, access, name, descriptor, returnClass, argumentTypeSize, argumentTypeDescriptors);
                } else {
                    System.out.println("访问方法参数"+argumentTypeSize+"方法"+name+descriptor+returnClassName);
                    adviceAdapter = new ObjectMethodAdapter(mv, access, name, descriptor, returnClass, argumentTypeSize, argumentTypeDescriptors);
                }
            }


            if (null != adviceAdapter) {
                return adviceAdapter;
            }
        }

        return mv;
    }


}
