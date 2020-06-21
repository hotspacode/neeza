package io.github.hotspacode.neeza.server.standalone.controller;

import io.github.hotspacode.neeza.base.dto.Result;
import io.github.hotspacode.neeza.base.dto.ServerNeezaClazz;
import io.github.hotspacode.neeza.server.standalone.service.MockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping
public class IndexController {
    @Autowired
    private MockDataService mockDataService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 获取所有app
     *
     * @return
     */
    @RequestMapping("/app/list")
    @ResponseBody
    public Result<List<String>> appList() {
        return Result.success(mockDataService.getAppList());
    }

    /**
     * 获取app下所有service
     *
     * @return
     */
    @RequestMapping("/service/list/{appName}")
    @ResponseBody
    public Result<List<String>> serviceList(@PathVariable(value = "appName") String appName) {
        return Result.success(mockDataService.serviceList(appName));
    }

    /**
     * 获取服务明细
     *
     * @return
     */
    @RequestMapping("/method/detail/{appName}")
    @ResponseBody
    public Result<ServerNeezaClazz> methodDetail(@PathVariable(value = "appName") String appName,
                                                 @RequestParam(value = "methodDesc") String methodDesc) {
        return Result.success(mockDataService.methodDetail(appName, methodDesc));
    }
}
