package io.github.hotspacode.neeza.test.asm;

import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

public class Test {

    public static void main(String... args1) throws IOException {
        ClassReader cr = new ClassReader(POJO.class.getName());
        ReadMethodArgNameClassVisitor classVisitor = new ReadMethodArgNameClassVisitor();
        cr.accept(classVisitor, 0);
        for (Entry<String, List<String>> entry : classVisitor.nameArgMap.entrySet()) {
            System.out.println(entry.getKey());
            for (String s : entry.getValue()) {
                System.out.println("    " + s);
            }
        }
    }

}