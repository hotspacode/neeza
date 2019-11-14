package io.github.hotspacode.neeza.deputy.api;

import io.github.hotspacode.neeza.deputy.dto.MockTransport;

import java.lang.reflect.Method;
import java.util.List;

public interface IMockSpyService {

    MockTransport transport(String targetClassName,String targetMethodName ,String argumentTypeDescriptors, List<Object> localVariable);

}
