package io.github.hotspacode.neeza.spy;

import com.alibaba.fastjson.parser.ParserConfig;
import io.github.hotspacode.neeza.base.api.INeezaMockPushSpyService;
import io.github.hotspacode.neeza.base.log.NeezaLog;
import io.github.hotspacode.neeza.base.util.NeezaConstant;
import io.github.hotspacode.neeza.transport.api.init.TransportServerCenterInitHandler;
import io.github.hotspacode.neeza.transport.client.http.TransportClient;

public class NeezaServer {
    private static volatile TransportClient transportClient = null;
    private static volatile TransportServerCenterInitHandler initHandler = null;
    private static String serverIp;
    private static Integer serverPort;
    private static INeezaMockPushSpyService neezaMockPushSpyService;
    private static NeezaServer instance = null;

    private NeezaServer() {
    }

    public static synchronized NeezaServer getInstance(){
        if (null == instance) {
            instance = new NeezaServer();
        }

        return instance;
    }

    public synchronized NeezaServer start(String serverIp, Integer serverPort, String packageName) {
        NeezaLog.info("neeza server初始化{},{},{}",serverIp,serverPort,packageName);
        System.getProperties().setProperty(NeezaConstant.SIMPLE_MOCK_VM_PACKAGE_NAME, packageName.replace(".","/"));
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

    public synchronized NeezaServer registerPush(INeezaMockPushSpyService pushSpyService){
        neezaMockPushSpyService = pushSpyService;
        return this;
    }

    public static INeezaMockPushSpyService getPushService(){
        return neezaMockPushSpyService;
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

    protected static String getServerIp() {
        return serverIp;
    }


    protected static Integer getServerPort() {
        return serverPort;
    }

}
