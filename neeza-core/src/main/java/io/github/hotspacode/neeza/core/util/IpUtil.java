package io.github.hotspacode.neeza.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtil {
    public static String getIp() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        return addr.getHostAddress();
    }

    private IpUtil() {
    }
}
