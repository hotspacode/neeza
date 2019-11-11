package io.github.hotspacode.neeza.deputy.api;

import io.github.hotspacode.neeza.deputy.dto.MockTransport;

import java.util.List;

public interface IMockSpyService {

    MockTransport transport(String targetClassName, String targetMethodName, List<Object> localVariable);

}
