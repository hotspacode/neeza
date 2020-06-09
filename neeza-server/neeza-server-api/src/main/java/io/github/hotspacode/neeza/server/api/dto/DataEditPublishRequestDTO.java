package io.github.hotspacode.neeza.server.api.dto;

import java.io.Serializable;

public class DataEditPublishRequestDTO implements Serializable {
    private String methodDesc;
    private String content;
    private Integer methodType;

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMethodType() {
        return methodType;
    }

    public void setMethodType(Integer methodType) {
        this.methodType = methodType;
    }
}
