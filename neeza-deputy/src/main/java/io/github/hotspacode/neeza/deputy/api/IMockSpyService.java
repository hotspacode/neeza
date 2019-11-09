package io.github.hotspacode.neeza.deputy.api;

import io.github.hotspacode.neeza.deputy.dto.MockTransport;

public interface IMockSpyService {

    MockTransport transport(String targetClassName, String targetMethodName);

}
