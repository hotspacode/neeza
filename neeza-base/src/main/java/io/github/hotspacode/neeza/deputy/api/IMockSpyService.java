package io.github.hotspacode.neeza.base.api;

import io.github.hotspacode.neeza.base.dto.MockTransport;

import java.lang.reflect.Method;
import java.util.List;

public interface IMockSpyService {

    MockTransport transport(String targetClassName,String targetMethodName ,String argumentTypeDescriptors, List<Object> localVariable);

}
