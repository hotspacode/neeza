package io.github.hotspacode.neeza.test.springboot;

import io.github.hotspacode.neeza.base.annotation.NeezaMock;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("index")
@NeezaMock
public class IndexController {
    @GetMapping("/name")
    @Transactional
    public String indexName() {
        return "IndexController index name";
    }


}
