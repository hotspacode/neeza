package io.github.hotspacode.neeza.core.domain.core.clazz;

import java.io.Serializable;
import java.util.List;

public class NeezaClazz implements Serializable {
    private String type;
    private List<NeezaClazz> fields;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<NeezaClazz> getFields() {
        return fields;
    }

    public void setFields(List<NeezaClazz> fields) {
        this.fields = fields;
    }
}
