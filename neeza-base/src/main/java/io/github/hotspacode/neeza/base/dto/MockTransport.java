package io.github.hotspacode.neeza.base.dto;


import io.github.hotspacode.neeza.base.api.INeezaSerialization;

import java.io.Serializable;

public class MockTransport implements Serializable {

    private boolean mocked = false;
    private String response;
    private Class methodReturnClass;

    private boolean primitive = false;
    private boolean returnNull = false;
    private boolean returnVoid = false;

    private INeezaSerialization neezaSerialization;

    public MockTransport() {
    }

    public MockTransport(boolean mocked) {
        this.mocked = mocked;
    }

    public static <T> T getObject(MockTransport methodSpiResponseDTO) {
        if (methodSpiResponseDTO.isReturnNull()) {
            return null;
        }
        if (null == methodSpiResponseDTO.getResponse()) {
            return null;
        }
        return (T) methodSpiResponseDTO.getNeezaSerialization().deserialize(methodSpiResponseDTO.getResponse().getBytes(), methodSpiResponseDTO.getMethodReturnClass());
    }

    public boolean isReturnNull() {
        return returnNull;
    }

    public boolean isReturnVoid() {
        return returnVoid;
    }

    public void setReturnVoid(boolean returnVoid) {
        this.returnVoid = returnVoid;
    }

    public void setReturnNull(boolean returnNull) {
        this.returnNull = returnNull;
    }

    public boolean isPrimitive() {
        return primitive;
    }

    public void setPrimitive(boolean primitive) {
        this.primitive = primitive;
    }

    public boolean isMocked() {
        if (this.isPrimitive() && this.returnNull) {
            return false;
        }
        if (null == neezaSerialization) {
            return false;
        }

        return mocked;
    }

    public void setMocked(boolean mocked) {
        this.mocked = mocked;
    }

    public Class getMethodReturnClass() {
        return methodReturnClass;
    }

    public void setMethodReturnClass(Class methodReturnClass) {
        this.methodReturnClass = methodReturnClass;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public INeezaSerialization getNeezaSerialization() {
        return neezaSerialization;
    }

    public void setNeezaSerialization(INeezaSerialization neezaSerialization) {
        this.neezaSerialization = neezaSerialization;
    }
}
