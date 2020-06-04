package io.github.hotspacode.neeza.test.springboot;

import io.github.hotspacode.neeza.base.annotation.NeezaMock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("index")
@NeezaMock
public class IndexController {
    @GetMapping("/name")
    public String indexName() {
        return "IndexController index name";
    }


}
