package io.github.hotspacode.neeza.agent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.Objects;

public class AgentTransformer implements ClassFileTransformer {
    private int agentType;//0 premain 1 agentmain
    private String targetClassName;
    private String targetPackageName;

    public AgentTransformer() {
    }

    public AgentTransformer(int agentType, String targetClassName, String targetPackageName) {
        this.agentType = agentType;
        this.targetClassName = targetClassName;
        this.targetPackageName = targetPackageName;
    }


    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classFileBuffer) {
        try {
            if (className == null || loader == null) {
                return null;
            }

            if (Objects.nonNull(targetClassName)) {
                if (!Objects.equals(targetClassName.replace(".", "/"), className)) {
                    return null;
                }
            }


            String packageName = System.getProperty("SIMPLE_MOCK_VM_PACKAGE_NAME");


            if (packageName == null || packageName.trim().length() <= 0 || !className.startsWith(packageName)) {
                return null;
            }

            System.out.println("最终执行：" + className + "------targetClassName：" + targetClassName);


            ClassReader cr = new ClassReader(className);
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            EnhancerAdapter enhancerAdapter = new EnhancerAdapter(cw);

            cr.accept(enhancerAdapter, ClassReader.EXPAND_FRAMES);

            return cw.toByteArray();
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return null;
    }
}
