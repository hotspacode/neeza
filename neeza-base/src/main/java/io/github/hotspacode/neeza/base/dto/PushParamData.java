package io.github.hotspacode.neeza.base.dto;

import java.io.Serializable;

public class PushParamData implements Serializable {
    private String className;
    private String content;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
