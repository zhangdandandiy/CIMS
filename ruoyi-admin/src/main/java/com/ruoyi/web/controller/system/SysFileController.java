package com.ruoyi.web.controller.system;

import com.ruoyi.common.constant.FileConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysChunk;
import com.ruoyi.system.domain.SysFileList;
import com.ruoyi.system.domain.vo.CheckChunkVo;
import com.ruoyi.system.service.ISysFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Dandan
 * @date 2023/8/15 8:29
 **/
@Api(tags = "分片文件相关接口")
@RestController
@RequestMapping("/system/file")
public class SysFileController extends BaseController {

    @Autowired
    private ISysFileService iSysFileService;

    /**
     * 上传分片文件
     */
    @ApiOperation("上传分片文件")
    @PostMapping("/upload")
    public AjaxResult postFileUpload(@ModelAttribute SysChunk chunk, HttpServletResponse response) {
        int i = iSysFileService.postFileUpload(chunk, response);
        return toAjax(i);
    }

    /**
     * 检查文件上传状态
     */
    @ApiOperation("检查文件上传状态")
    @GetMapping("/check")
    public CheckChunkVo checkFileUpload(@ModelAttribute SysChunk chunk, HttpServletResponse response) {
        // 查询根据MD5查询文件是否存在
        return iSysFileService.getFileUpload(chunk, response);
    }

    /**
     * 合并文件
     */
    @ApiOperation("合并文件")
    @PostMapping("/merge")
    public AjaxResult merge(SysFileList fileList) {
        int i = iSysFileService.mergeFile(fileList);
        if (i == FileConstants.UPDATE_EXISTS.intValue()) {
            // 应对合并时断线导致的无法重新申请合并的问题
            return new AjaxResult(200, "已合并，无需再次提交");
        }
        return toAjax(i);
    }

}
