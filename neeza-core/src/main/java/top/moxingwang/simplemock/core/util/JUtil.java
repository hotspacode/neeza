package top.moxingwang.simplemock.core.util;

import java.lang.management.ManagementFactory;

public class JUtil {
    public static String getPid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        return pid;
    }
}
