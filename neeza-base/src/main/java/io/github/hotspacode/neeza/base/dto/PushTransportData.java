package io.github.hotspacode.neeza.base.dto;

import java.io.Serializable;
import java.util.List;

public class PushTransportData implements Serializable {
    private String serviceName;
    private String methodName;
    private List<PushParamData> paramList;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<PushParamData> getParamList() {
        return paramList;
    }

    public void setParamList(List<PushParamData> paramList) {
        this.paramList = paramList;
    }
}

