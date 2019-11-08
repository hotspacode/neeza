package top.moxingwang.simplemock.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moxingwang.simplemock.server.service.MockIndexService;

@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private MockIndexService mockIndexService;

}
