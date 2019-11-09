package io.github.hotspacode.neeza.spy.service;

import io.github.hotspacode.neeza.deputy.api.IMockSpyService;
import io.github.hotspacode.neeza.deputy.dto.MockTransport;

public class MockSpyService implements IMockSpyService {
    @Override
    public MockTransport transport(String targetClassName, String targetMethodName) {
        MockTransport mockTransport = new MockTransport(false);


        return mockTransport;
    }
}
