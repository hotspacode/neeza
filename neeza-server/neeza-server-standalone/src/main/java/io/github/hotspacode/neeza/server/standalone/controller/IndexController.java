package io.github.hotspacode.neeza.server.standalone.controller;

import io.github.hotspacode.neeza.base.dto.Result;
import io.github.hotspacode.neeza.server.standalone.service.MockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
        List<String> appList = mockDataService.getAppList();
        return Result.success(appList);
    }

    /**
     * 获取app下所有service
     *
     * @return
     */
    @RequestMapping("/service/list/{appName}")
    @ResponseBody
    public Result<List<String>> serviceList(@PathVariable(value = "appName") String appName) {


        return Result.success();
    }

    /**
     * 获取服务明细
     *
     * @return
     */
    @RequestMapping("/method/detail/{appName}")
    @ResponseBody
    public Result<List<String>> methodDetail(@PathVariable(value = "methodDesc") String methodDesc) {


        return Result.success();
    }
}
