package io.github.hotspacode.neeza.core.domain.core;

import java.io.Serializable;
import java.util.List;

/**
 * 资源实体
 */
public class Entity implements Serializable {
    private String descriptor;//资源描述
    private String className;
    private String methodName;
    private List<Rule> rules;
}
