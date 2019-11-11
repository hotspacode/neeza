package io.github.hotspacode.neeza.server.standalone.controller;

import io.github.hotspacode.neeza.server.standalone.service.MockIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private MockIndexService mockIndexService;

}
