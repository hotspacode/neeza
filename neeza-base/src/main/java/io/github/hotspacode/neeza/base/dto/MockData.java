package io.github.hotspacode.neeza.base.dto;

import java.io.Serializable;

public class MockData implements Serializable {
    private Type type;
    private String body;

    public enum Type {
        //未被mock到或者表达NULL VALUE的意思
        NONE,
        ReturnBody,
        VoidReturn,
        ReturnNull
    }

    public MockData() {
    }

    public static MockData getNullValue(){
        return new MockData(Type.NONE, null);
    }

    public MockData(Type type, String body) {
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
