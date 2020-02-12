package io.github.hotspacode.neeza.test.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("index")
public class IndexController {
    @GetMapping("/name")
    public String indexName() {
        return "IndexController index name";
    }


}
