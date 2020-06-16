package io.github.hotspacode.neeza.spy;

import com.alibaba.fastjson.parser.ParserConfig;
import io.github.hotspacode.neeza.base.dto.MockData;
import io.github.hotspacode.neeza.base.log.NeezaLog;
import io.github.hotspacode.neeza.base.util.NeezaConstant;
import io.github.hotspacode.neeza.base.util.StringUtil;
import io.github.hotspacode.neeza.transport.api.init.TransportServerCenterInitHandler;
import io.github.hotspacode.neeza.transport.client.http.TransportClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NeezaServer {
    private static final ConcurrentHashMap<String, MockData> methodMockDataCacheMap = new ConcurrentHashMap<>();
    private static volatile TransportClient transportClient = null;
    private static volatile TransportServerCenterInitHandler initHandler = null;
    private static String serverIp;
    private static Integer serverPort;
    private static Map<String, Object> pushServiceMap;
    private static NeezaServer instance = null;
    private static String appName;

    private NeezaServer() {
    }

    public static synchronized NeezaServer getInstance() {
        if (null == instance) {
            instance = new NeezaServer();
        }

        return instance;
    }

    public synchronized NeezaServer start(String serverIp, Integer serverPort, String packageName) {
        NeezaLog.info("neeza server初始化{},{},{}", serverIp, serverPort, packageName);
        System.getProperties().setProperty(NeezaConstant.SIMPLE_MOCK_VM_PACKAGE_NAME, packageName.replace(".", "/"));
        ParserConfig.getGlobalInstance().addAccept(packageName);

        NeezaServer.serverIp = serverIp;
        NeezaServer.serverPort = serverPort;

        if (null == initHandler) {
            initHandler = new TransportServerCenterInitHandler();
            initHandler.init();
        }

        if (null == transportClient) {
            initTransportClient();
        }

        return this;
    }

    public synchronized NeezaServer registerPushServices(Map<String, Object> serviceMap) {
        pushServiceMap = serviceMap;
        return this;
    }

    public static Object getService(String serviceId) {
        if (StringUtil.isBlank(serviceId) || pushServiceMap == null || pushServiceMap.isEmpty()) {
            return null;
        }

        return pushServiceMap.get(serviceId);
    }

    protected static TransportClient getTransportClient() {
        if (transportClient == null) {
            initTransportClient();
        }
        return transportClient;
    }

    protected static synchronized void initTransportClient() {
        if (null == transportClient) {
            transportClient = new TransportClient();
        }
    }

    public static String getServerIp() {
        return serverIp;
    }


    public static Integer getServerPort() {
        return serverPort;
    }

    public static String getAppName() {
        return appName;
    }

    public static void expireMethodMcckKey(String key) {
        methodMockDataCacheMap.remove(key);
    }

    public static MockData getMethodMockCache(String key){
        return methodMockDataCacheMap.get(key);
    }
    public static void cacheMethodMock(String key,MockData mockData){
        methodMockDataCacheMap.put(key,mockData);
    }

    public static Map<String, MockData> getMockedService(){
        return methodMockDataCacheMap;
    }
}
