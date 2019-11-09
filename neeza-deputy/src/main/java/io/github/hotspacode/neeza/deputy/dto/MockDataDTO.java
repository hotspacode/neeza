package io.github.hotspacode.neeza.deputy.dto;

import java.io.Serializable;

public class MockDataDTO implements Serializable {

    private Type type;
    private String body;

    public enum Type {
        NONE,ReturnBody, VoidReturn, ReturnNull
    }

    public MockDataDTO() {
    }

    public MockDataDTO(Type type, String body) {
        this.type = type;
        this.body = body;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
