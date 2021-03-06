package io.github.hotspacode.neeza.core.domain.core;

import java.io.Serializable;
import java.util.List;

/**
 * 资源
 */
public class Entity implements Serializable {
    private String descriptor;//资源描述   io.github.hotspacode.neeza.core.domain.core.createOrder(java,long.String,)
    private String className;
    private String methodName;
    private List<String> methodParamClassNames;
    private String returnClassNames;

    private List<Rule> rules;
}
