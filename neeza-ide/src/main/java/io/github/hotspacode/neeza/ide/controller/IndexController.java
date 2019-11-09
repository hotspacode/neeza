package io.github.hotspacode.neeza.ide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.hotspacode.neeza.server.service.MockIndexService;

@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private MockIndexService mockIndexService;

}
