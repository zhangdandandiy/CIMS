package com.ruoyi.web.controller.monitor;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.web.domain.Server;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器监控
 *
 * @author Dandan
 */
@Api(tags = "服务器监控相关接口")
@RestController
@RequestMapping("/monitor/server")
public class ServerController {

    @ApiOperation("获取服务信息")
    @PreAuthorize("@ss.hasPermi('monitor:server:list')")
    @GetMapping()
    public AjaxResult getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }

}
