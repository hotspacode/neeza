package top.moxingwang.simplemock.core.dto;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class MethodSpiResponseDTO implements Serializable {

    private boolean mocked = false;
    private String response;
    private Class methodReturnClass;

    private boolean primitive = false;
    private boolean returnNull = false;
    private boolean returnVoid = false;

    public MethodSpiResponseDTO() {
    }

    public MethodSpiResponseDTO(boolean mocked) {
        this.mocked = mocked;
    }

    public static <T> T getObject(MethodSpiResponseDTO methodSpiResponseDTO) {
        if (methodSpiResponseDTO.isReturnNull()) {
            return null;
        }
        if (null == methodSpiResponseDTO.getResponse()) {
            return null;
        }
        return JSONObject.parseObject(methodSpiResponseDTO.getResponse().getBytes(), methodSpiResponseDTO.getMethodReturnClass());
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
}
