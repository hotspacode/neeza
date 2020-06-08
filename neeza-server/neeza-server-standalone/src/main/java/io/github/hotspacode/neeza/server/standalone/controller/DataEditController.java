package io.github.hotspacode.neeza.server.standalone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataEditController {
    private static final Logger logger = LoggerFactory.getLogger(DataEditController.class);

    /**
     * 发布mock数据
     *
     * @return
     */
    @GetMapping("/publish")
    public String publish() {

        //落库

        //通知应用

        return null;
    }

    /**
     * 推送mock数据
     *
     * @return
     */
    @GetMapping("/push")
    public String push() {
        //推送到应用

        //落库日志

        return null;
    }

}
