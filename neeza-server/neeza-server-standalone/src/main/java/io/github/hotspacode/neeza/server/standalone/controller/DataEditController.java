package io.github.hotspacode.neeza.server.standalone.controller;

import io.github.hotspacode.neeza.server.api.dto.DataEditPublishRequestDTO;
import io.github.hotspacode.neeza.server.standalone.service.DataEditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataEditController {
    private static final Logger logger = LoggerFactory.getLogger(DataEditController.class);

    @Autowired
    private DataEditService dataEditService;

    /**
     * 发布mock数据
     *
     * @return
     */
    @PostMapping("/publish")
    public String publish(@RequestBody DataEditPublishRequestDTO request) {

        //落库

        //通知应用
        return null;
    }

    /**
     * 推送mock数据
     *
     * @return
     */
    @PostMapping("/push")
    public String push() {
        //推送到应用

        //落库日志

        return null;
    }

}
