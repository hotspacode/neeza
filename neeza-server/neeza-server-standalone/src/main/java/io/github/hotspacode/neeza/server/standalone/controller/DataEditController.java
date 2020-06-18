package io.github.hotspacode.neeza.server.standalone.controller;

import io.github.hotspacode.neeza.base.dto.PushTransportData;
import io.github.hotspacode.neeza.base.dto.Result;
import io.github.hotspacode.neeza.server.api.dto.DataEditPublishRequestDTO;
import io.github.hotspacode.neeza.server.standalone.service.DataEditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result publish(@RequestBody DataEditPublishRequestDTO request) {
        dataEditService.publish(request.getMethodDesc(), request.getContent(), request.getMethodType());
        return Result.success();
    }

    /**
     * 推送mock数据
     *
     * @return
     */
    @PostMapping("/push/{ip}/{port}")
    public Result push(@RequestBody PushTransportData request,
                       @PathVariable(value = "ip") String ip,
                       @PathVariable(value = "port") String port
    ) {
        //推送到应用

        //落库日志

        return Result.success(dataEditService.push(request, ip, port));
    }

    /**
     * 返回所有接口
     */
    @PostMapping("/api/list")
    public Result apiList(@PathVariable(value = "appName") String appName) {
        //推送到应用

        //落库日志

        return Result.success();
    }

}
