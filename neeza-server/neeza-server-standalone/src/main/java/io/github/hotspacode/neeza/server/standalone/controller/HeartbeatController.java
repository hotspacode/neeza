package io.github.hotspacode.neeza.server.standalone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 心跳请求
 */
@RestController
@RequestMapping("/heartbeat")
public class HeartbeatController {

    @GetMapping("/pull")
    public String pull(@RequestParam(value = "methodDesc") String methodDesc,
                       @RequestParam(value = "clientPort") Integer clientPort,
                       HttpServletRequest request) {


        return null;
    }

}
