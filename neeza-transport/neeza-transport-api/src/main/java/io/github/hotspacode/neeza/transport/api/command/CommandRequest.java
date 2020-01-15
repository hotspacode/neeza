package io.github.hotspacode.neeza.transport.api.command;

import io.github.hotspacode.neeza.base.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

public class CommandRequest {

    private final Map<String, String> metadata = new HashMap<String, String>();
    private final Map<String, String> parameters = new HashMap<String, String>();
    private byte[] body;

    public byte[] getBody() {
        return body;
    }

    public CommandRequest setBody(byte[] body) {
        this.body = body;
        return this;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getParam(String key) {
        return parameters.get(key);
    }

    public String getParam(String key, String defaultValue) {
        String value = parameters.get(key);
        return StringUtil.isBlank(value) ? defaultValue : value;
    }

    public CommandRequest addParam(String key, String value) {
        if (StringUtil.isBlank(key)) {
            throw new IllegalArgumentException("Parameter key cannot be empty");
        }
        parameters.put(key, value);
        return this;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public CommandRequest addMetadata(String key, String value) {
        if (StringUtil.isBlank(key)) {
            throw new IllegalArgumentException("Metadata key cannot be empty");
        }
        metadata.put(key, value);
        return this;
    }
}
