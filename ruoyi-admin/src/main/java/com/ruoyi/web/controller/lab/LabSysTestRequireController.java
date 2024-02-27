package com.ruoyi.web.controller.lab;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.file.FileMergeUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.lab.domain.LabSysTestRequire;
import com.ruoyi.lab.domain.dto.LabSysPlanContentProductDto;
import com.ruoyi.lab.domain.dto.LabSysPlanContentResultGroupDto;
import com.ruoyi.lab.domain.dto.LabSysPlanContentSearchDto;
import com.ruoyi.lab.service.ILabSysTestRequireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.alibaba.druid.util.Utils.md5;

/**
 * group Controller
 *
 * @author Dandan
 * @date 2023-08-07
 */
@Api(tags = "Group测试项相关接口")
@RestController
@RequestMapping("/lab/result/group")
public class LabSysTestRequireController extends BaseController {

    @Autowired
    private ILabSysTestRequireService labSysTestGroupService;

    @Autowired
    private RedisCache redisCache;

    private static Random random = new Random();

    /**
     * 查询测试要求及内容
     */
    @ApiOperation("查询测试要求及内容")
    @GetMapping("/list")
    public AjaxResult list(LabSysPlanContentSearchDto contentSearch) {
        String cacheKey = "treeList:" + md5(contentSearch.toString()); // 使用 md5 值作为缓存键
        Set<LabSysPlanContentProductDto> oldTree = redisCache.getCacheSet(cacheKey);
        if (oldTree.size() > 0) {
            return success(oldTree);
        } else {
            List<LabSysPlanContentProductDto> list = labSysTestGroupService.selectLabSysTestGroupList(contentSearch);
            Set<LabSysPlanContentProductDto> newTree = new HashSet<>(list);
            redisCache.setCacheSet(cacheKey, newTree);
            return success(list);
        }
    }

    /**
     * 根据名称查询测试要求及内容
     */
    @ApiOperation("根据名称查询测试要求及内容")
    @GetMapping("/listByName")
    public AjaxResult selectLabSysTestGroupListByName(LabSysPlanContentSearchDto contentSearch) {
        List<LabSysTestRequire> list = labSysTestGroupService.selectLabSysTestGroupListByName(contentSearch);
        return success(list);
    }

    /**
     * 根据Id查询测试要求及内容详细信息
     */
    @ApiOperation("根据Id查询测试要求及内容详细信息")
    @GetMapping("/query")
    public AjaxResult getInfo(Long testGroupId) {
        return success(labSysTestGroupService.selectLabSysTestGroupByTestGroupId(testGroupId));
    }

    /**
     * 新增测试要求及其关联测试内容
     */
    @ApiOperation("新增测试要求及其关联测试内容")
    @Log(title = "测试要求", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody List<LabSysTestRequire> labSysTestGroupList) {
        List<String> message = new ArrayList<>();
        for (LabSysTestRequire labSysTestRequire : labSysTestGroupList) {
            labSysTestRequire.setCreateBy(getUsername());
            // 每次新增都获取最大ID
            labSysTestRequire.setTestGroupId((long) (labSysTestGroupService.getLabSysTestGroupMaxGroupId() + 1));
            String messageItem = labSysTestGroupService.insertLabSysTestGroup(labSysTestRequire);
            message.add(messageItem);
        }
        return success(message);
    }

    @ApiOperation("新增测试要求及其关联测试内容提交的参数")
    @PostMapping("/addParam")
    public AjaxResult addParam(@RequestBody LabSysPlanContentSearchDto contentSearch) {
        List<LabSysTestRequire> labSysTestGroupList = labSysTestGroupService.selectLabSysTestGroupListByName(contentSearch);
        return success(labSysTestGroupList);
    }


    /**
     * 修改测试要求及其关联测试内容
     */
    @ApiOperation("修改测试要求及其关联测试内容")
    @Log(title = "测试要求", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody LabSysTestRequire labSysTestGroup) {
        labSysTestGroup.setUploadBy(getUsername());
        String message = labSysTestGroupService.updateLabSysTestGroup(labSysTestGroup);
        return success(message);
    }

    /**
     * 删除测试要求及其关联测试内容
     */
    @ApiOperation("删除测试要求及其关联测试内容")
    @Log(title = "测试要求", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public AjaxResult removeGroup(Long[] ids) {
        return toAjax(labSysTestGroupService.deleteLabSysTestGroupByTestGroupIds(ids));
    }

    /**
     * 通过名称删除测试要求及其关联测试内容
     */
    @ApiOperation("通过名称删除测试要求及其关联测试内容")
    @Log(title = "测试要求", businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteByName")
    public AjaxResult removeGroupByName(@RequestBody LabSysPlanContentSearchDto labSysPlanContentSearchDto) {
        return toAjax(labSysTestGroupService.deleteLabSysTestGroup(labSysPlanContentSearchDto));
    }

    /**
     * 查询group测试结果
     */
    @ApiOperation("查询group测试结果")
    @GetMapping("/groupResult")
    public TableDataInfo searchLabSysTestGroupResult(LabSysPlanContentSearchDto search) {
        startPage();
        List<LabSysPlanContentResultGroupDto> list = labSysTestGroupService.searchLabSysTestGroupResult(search);
        return getDataTable(list);
    }

    /**
     * 获取 lab_sys_test_group表中最大的主键ID
     */
    @ApiOperation("获取 lab_sys_test_group 表中最大的主键ID")
    @GetMapping("/maxId")
    public AjaxResult getLabSysTestGroupMaxGroupId() {
        return success(labSysTestGroupService.getLabSysTestGroupMaxGroupId());
    }

    /**
     * 测试项目group文件上传
     */
    @ApiOperation("测试项目group文件上传")
    @Log(title = "测试要求", businessType = BusinessType.UPDATE)
    @PostMapping("/upload")
    public AjaxResult uploadTestGroupFile(@RequestPart @RequestParam("testFile") MultipartFile file,
                                          @RequestParam("testGroupId") Long testGroupId) throws IOException, InvalidExtensionException {
        if (!file.isEmpty()) {
            String filePath = FileUploadUtils.uploadFile(RuoYiConfig.getUploadPath(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
            boolean uploadResult = labSysTestGroupService.updateTestGroupFile(testGroupId, filePath, getUsername());
            if (uploadResult) {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("fileUrl", filePath);
                return ajax;
            }
        }
        return error("上传文件异常，请联系管理员");
    }

    /**
     * 测试项目group文件预览
     */
    @ApiOperation("测试项目group文件预览")
    @GetMapping("/preview")
    public AjaxResult previewTestGroupFile(Long testGroupId) {
        LabSysTestRequire testRequire = labSysTestGroupService.selectLabSysTestGroupByTestGroupId(testGroupId);
        String filePath = testRequire.getGroupFile();
        if (filePath != null) {
            return success("/profile" + filePath);
        } else {
            return success(null);
        }

    }

    /**
     * 测试项目group补正文件上传
     */
    @ApiOperation("测试项目group补正文件上传")
    @Log(title = "测试要求", businessType = BusinessType.UPDATE)
    @PostMapping("/bzUpload")
    public AjaxResult uploadTestBzGroupFile(@RequestPart @RequestParam("testFile") MultipartFile file,
                                            @RequestParam("testGroupId") Long testGroupId) throws IOException, InvalidExtensionException {
        if (!file.isEmpty()) {
            String filePath = FileUploadUtils.uploadFile(RuoYiConfig.getUploadPath(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
            boolean uploadResult = labSysTestGroupService.updateTestMergeBzGroupFile(testGroupId, filePath, getUsername());
            if (uploadResult) {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("fileUrl", filePath);
                return ajax;
            }
        }
        return error("上传文件异常，请联系管理员");
    }

    /**
     * 测试项目group补正文件预览
     */
    @ApiOperation("测试项目group补正文件预览")
    @GetMapping("/bzPreview")
    public AjaxResult previewTestBzGroupFile(Long testGroupId) {
        LabSysTestRequire testRequire = labSysTestGroupService.selectLabSysTestGroupByTestGroupId(testGroupId);
        String filePath = testRequire.getGroupMergeBzFile();
        if (filePath != null) {
            return success("/profile" + filePath);
        } else {
            return success(null);
        }
    }

    /**
     * 拼接文件（完整报告）上传
     */
    @ApiOperation("拼接完整报告上传")
    @Log(title = "测试要求", businessType = BusinessType.UPDATE)
    @PostMapping("/merUpload")
    public AjaxResult uploadTestMergeGroupFile(@RequestPart @RequestParam("testFile") MultipartFile file,
                                               @RequestParam("testGroupId") Long testGroupId) throws IOException, InvalidExtensionException {
        if (!file.isEmpty()) {
            String filePath = FileUploadUtils.uploadFile(RuoYiConfig.getUploadPath(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
            boolean uploadResult = labSysTestGroupService.updateTestMergeGroupFile(testGroupId, filePath, getUsername());
            if (uploadResult) {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("fileUrl", filePath);
                return ajax;
            }
        }
        return error("上传文件异常，请联系管理员");
    }

    /**
     * 测试项目group文件下载
     */
    @ApiOperation(value = "测试项目group文件下载", produces = "application/octet-stream")
    @PostMapping("/download")
    public void groupFileDownload(Long testGroupId, HttpServletResponse response, HttpServletRequest request) {
        LabSysTestRequire testRequire = labSysTestGroupService.selectLabSysTestGroupByTestGroupId(testGroupId);
        String groupFilePath = testRequire.getGroupFile();
        FileMergeUtils.downloadFile(groupFilePath, response);
    }

    /**
     * 测试项目group补正文件下载
     */
    @ApiOperation(value = "测试项目group补正文件下载", produces = "application/octet-stream")
    @PostMapping("/bzDownload")
    public void groupBzFileDownload(Long testGroupId, HttpServletResponse response, HttpServletRequest request) {
        LabSysTestRequire testRequire = labSysTestGroupService.selectLabSysTestGroupByTestGroupId(testGroupId);
        String groupFilePath = testRequire.getGroupMergeBzFile();
        FileMergeUtils.downloadFile(groupFilePath, response);
    }

    /**
     * 清除合并文件
     */
    @ApiOperation("清除合并文件")
    @PutMapping("/clearFile")
    public AjaxResult updateLabSysTestGroupFilePath(Long testGroupId) {
        return toAjax(labSysTestGroupService.updateLabSysTestGroupFilePath(testGroupId));
    }

}
