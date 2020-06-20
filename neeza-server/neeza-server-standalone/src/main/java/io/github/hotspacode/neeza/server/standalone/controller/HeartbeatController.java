package io.github.hotspacode.neeza.server.standalone.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.github.hotspacode.neeza.base.annotation.NeezaMock;
import io.github.hotspacode.neeza.base.log.NeezaLog;
import io.github.hotspacode.neeza.base.util.StringUtil;
import io.github.hotspacode.neeza.core.domain.core.clazz.NeezaClazz;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * 心跳请求
 */
@RestController
@RequestMapping("/heartbeat")
public class HeartbeatController {

    @GetMapping("/pull")
    public String pull(@RequestParam(value = "appName") String appName,
                       @RequestParam(value = "version") String version,
                       @RequestParam(value = "ip") String ip,
                       @RequestParam(value = "port") String port,
                       @RequestParam(value = "pid") String pid,
                       @RequestParam(value = "pulledMethods") String pulledMethodsStr,
                       @RequestParam(value = "mockClasses") String mockClassesStr,
                       HttpServletRequest request) {
        NeezaLog.info("心跳检测到服务端{0}",pid);
        Set<String> pulledMethods = null;
        Set<NeezaClazz> mockClasses = null;

        if (StringUtil.isNotBlank(pulledMethodsStr)) {
            pulledMethods = JSON.parseObject(pulledMethodsStr, new TypeReference<Set<String>>() {
            });
        }
        if (StringUtil.isNotBlank(mockClassesStr)) {
            mockClasses = JSON.parseObject(mockClassesStr, new TypeReference<Set<NeezaClazz>>() {
            });
        }


        return null;
    }

}
