package com.ruoyi.web.controller.lab;

import com.alibaba.druid.filter.config.ConfigTools;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileMergeUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.lab.domain.LabSysTestItem;
import com.ruoyi.lab.domain.LabSysTestRequire;
import com.ruoyi.lab.domain.dto.LabSysPlanContentResultItemDto;
import com.ruoyi.lab.domain.dto.LabSysPlanContentSearchDto;
import com.ruoyi.lab.service.ILabSysTestRequireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dandan
 * @date 2023/9/20 9:44
 **/
@Api(tags = "Item测试项相关接口")
@RestController
@RequestMapping("/lab/result/item")
public class LabSysTestItemController extends BaseController {

    @Autowired
    private ILabSysTestRequireService labSysTestGroupService;

    /**
     * 修改item
     */
    @ApiOperation("修改单条测试内容")
    @Log(title = "测试要求", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult editItem(@RequestBody LabSysTestItem labSysTestItem) {
        labSysTestItem.setUploadBy(getUsername());
        return toAjax(labSysTestGroupService.updateLabSysTestItem(labSysTestItem));
    }

    /**
     * 通过testItemId删除测试项目
     */
    @ApiOperation("通过testItemId删除测试项目")
    @Log(title = "测试要求", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public AjaxResult removeItem(Long[] ids) {
        return toAjax(labSysTestGroupService.deleteLabSysTestItemByTestItemId(ids));
    }

    /**
     * 查询详细测试结果
     */
    @ApiOperation("查询详细测试结果")
    @GetMapping("/itemResult")
    public TableDataInfo searchLabSysTestItemResult(LabSysPlanContentSearchDto search) {
        startPage();
        List<LabSysPlanContentResultItemDto> list = labSysTestGroupService.searchLabSysTestItemResult(search);
        return getDataTable(list);
    }

    /**
     * 测试项目结果文件上传
     */
    @ApiOperation("测试项目结果文件上传")
    @Log(title = "测试要求", businessType = BusinessType.UPDATE)
    @PostMapping("/upload")
    @PreAuthorize("@ss.hasPermi('lab:resMaintain:result:upload')")
    public AjaxResult uploadTestItemFile(@RequestPart @RequestParam("testFile") MultipartFile file,
                                         @RequestParam("testItemId") Long testItemId,
                                         @RequestParam("testItemResult") String testItemResult) throws Exception {
        if (!file.isEmpty()) {
            String filePath = FileUploadUtils.uploadFile(RuoYiConfig.getUploadPath(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
            boolean uploadResult = labSysTestGroupService.updateTestPlanFile(testItemId, getUsername(), filePath, testItemResult);
            boolean allCompleted = labSysTestGroupService.allCompleted(testItemId);
            if (uploadResult) {
                // 上传成功，开始拼接文件
                LabSysTestRequire labSysTestRequire = labSysTestGroupService.selectLabSysTestGroupByTestItemId(testItemId);
                String noCompletedMergeFile = labSysTestRequire.getGroupNoCompletedMergeFile();
                String downloadFileName = "";
                String newFilePath = "";
                String dbFilePath = "";
                if (allCompleted) {
                    // 如果所有测试项全部完成，文件路径为 专案-阶段-里程-group
                    downloadFileName = labSysTestGroupService.getFileNameByTestRequire(labSysTestRequire);
                } else {
                    // 如果所有测试项未全部完成，文件路径为 时间戳
                    downloadFileName = System.currentTimeMillis() + ".xlsx";
                }
                newFilePath = RuoYiConfig.getUploadPath() + "/" + DateUtils.datePath() + "/" + downloadFileName;
                dbFilePath = "/upload/" + DateUtils.datePath() + "/" + downloadFileName;
                List<String> filePathList = new ArrayList<>();
                if (noCompletedMergeFile != null) {
                    // 不是第一次拼接文件
                    filePathList.add(RuoYiConfig.getDownloadPath() + noCompletedMergeFile);
                }
                filePathList.add(RuoYiConfig.getDownloadPath() + filePath);
                FileMergeUtils.combineMultipleExcel(filePathList, newFilePath);

                // 更新数据库记录
                LabSysTestRequire require = new LabSysTestRequire();
                require.setTestGroupId(labSysTestRequire.getTestGroupId());
                require.setGroupNoCompletedMergeFile(dbFilePath);
                if (allCompleted) {
                    // 如果所有测试项已完成，更新合并文件路径
                    require.setGroupMergeFile(dbFilePath);
                }
                labSysTestGroupService.updateLabSysTestGroupStatus(require);
                AjaxResult ajax = AjaxResult.success();
                ajax.put("fileUrl", filePath);
                return ajax;
            }
        }
        return error("上传文件异常，请联系管理员");
    }

    @ApiOperation("测试1")
    @GetMapping("/test1")
    public AjaxResult test1(Long testItemId) {
        return success(labSysTestGroupService.allCompleted(testItemId));
    }

    /**
     * 测试项目结果文件预览
     */
    @ApiOperation("测试项目结果文件预览")
    @GetMapping("/preview")
    public AjaxResult previewTestItem(Long testItemId) {
        LabSysTestItem testItem = labSysTestGroupService.selectLabSysTestItemByTestItemId(testItemId);
        String filePath = testItem.getFilePath();
        if (filePath != null) {
            return success("/profile" + filePath);
        } else {
            return success(null);
        }
    }

    /**
     * 通过testItemId查询测试详细信息
     */
    @ApiOperation("通过testItemId查询测试详细信息")
    @GetMapping("/itemInfo")
    public AjaxResult selectLabSysTestItemByTestItemId(Long testItemId) {
        return success(labSysTestGroupService.selectLabSysTestItemByTestItemId(testItemId));
    }

    /**
     * 修改测试项目详细信息数据
     */
    @ApiOperation("修改测试项目测试结果")
    @PostMapping("/editResult")
    public AjaxResult updateLabSysTestItemResult(String testResult, Long testItemId) {
        LabSysTestItem labSysTestItem = new LabSysTestItem();
        labSysTestItem.setTestItemId(testItemId);
        labSysTestItem.setUploadBy(getUsername());
        labSysTestItem.setTestItemResult(testResult);
        return toAjax(labSysTestGroupService.updateLabSysTestItemResult(labSysTestItem));
    }

    /**
     * 测试项目结果文件下载
     */
    @ApiOperation(value = "测试项目结果文件下载", produces = "application/octet-stream")
    @PreAuthorize("@ss.hasPermi('lab:resMaintain:result:download')")
    @PostMapping("/download")
    public void itemFileDownload(Long testItemId, HttpServletResponse response, HttpServletRequest request) {
        LabSysTestItem testItem = labSysTestGroupService.selectLabSysTestItemByTestItemId(testItemId);
        String fileName = testItem.getFilePath();
        FileMergeUtils.downloadFile(fileName, response);
    }

    /**
     * 将多个 excel 文件合并下载
     */
    @ApiOperation(value = "多个测试项目文件合并成一个sheet工作簿", produces = "application/octet-stream")
    @PostMapping("/merge-excel")
    public void mergeExcel(Long testGroupId, HttpServletResponse response) throws IOException {

        LabSysTestRequire labSysTestRequire = labSysTestGroupService.selectLabSysTestGroupByTestGroupId(testGroupId);
        // 获取合并文件路径
        String mergeFilePath = labSysTestRequire.getGroupMergeFile();
        if (mergeFilePath != null) {
            // 合并文件路径存在，说明已全部合并测试项文件，直接下载
            FileMergeUtils.downloadFile(mergeFilePath, response);
        } else {
            // 设置文件名及文件下载路径
            String downloadFileName = labSysTestGroupService.getFileNameByTestRequire(labSysTestRequire);
            String newFile = RuoYiConfig.getUploadPath() + "/" + DateUtils.datePath() + "/" + downloadFileName;
            String dbFilePath = "/upload/" + DateUtils.datePath() + "/" + downloadFileName;
            // 创建文件路径
            try {
                File file = new File(newFile);
                File parentDir = file.getParentFile();
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<String> filePaths = labSysTestGroupService.getFilePathByGroupId(testGroupId);
            filePaths = filePaths.stream()
                    .filter(s -> s != null)
                    .collect(Collectors.toList());
            String prefix = RuoYiConfig.getDownloadPath();
            for (int i = 0; i < filePaths.size(); i++) {
                String originalString = filePaths.get(i);
                String modifiedString = prefix.concat(originalString);
                filePaths.set(i, modifiedString);
            }
            if (filePaths.size() == 0) {
                // 如果filePaths为空，下载一个空白的Excel文件
                FileMergeUtils.createEmptyExcelFile(newFile);
                FileMergeUtils.downloadFile(dbFilePath, response);
            } else {
                // 存储并下载文件
                FileMergeUtils.combineMultipleExcel(filePaths, newFile);
                FileMergeUtils.downloadFile(dbFilePath, response);

                // 获取测试项完成状态
                boolean completeStatus = labSysTestGroupService.getLabSysTestGroupResult(testGroupId);
                LabSysTestRequire require = new LabSysTestRequire();
                require.setTestGroupId(testGroupId);
                require.setGroupNoCompletedMergeFile(dbFilePath);
                if (completeStatus) {
                    // 如果所有测试项已完成，更新合并文件路径
                    require.setGroupMergeFile(dbFilePath);
                }
                labSysTestGroupService.updateLabSysTestGroupStatus(require);
            }

        }

    }

    /**
     * 测试项目合并文件预览
     */
    @ApiOperation("测试项目合并文件预览")
    @GetMapping("/previewMerge")
    public AjaxResult previewMergeFile(Long testGroupId) {
        LabSysTestRequire testRequire = labSysTestGroupService.selectLabSysTestGroupByTestGroupId(testGroupId);
        String filePath = testRequire.getGroupNoCompletedMergeFile();
        if (filePath != null) {
            return success("/profile" + filePath);
        } else {
            return success(null);
        }
    }

    @ApiOperation("获取数据库解密密码")
    @GetMapping("/getPassword")
    public AjaxResult getPassword() throws Exception {
        String password = ConfigTools.decrypt(
                "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALO0OJd0wYSrdnhoVR1arL4r5JY1U9q3iwzlft/Tfu06R0e/TOk2bkIgJIrw8+GLKaus8FdnmhWn+3uZMcdXbbUCAwEAAQ==",
                "HpsEB2iMAVgUzFi0N3IdWgOM1DYCw9dNRszEFr3/IZT2y3jWdHe8lbiaDyMnpW/qqC2Lz1D0AJqaQ7S8QoPSMg==");
        return success(password);
    }

     @ApiOperation("测试")
    @GetMapping("/test")
    public void test(HttpServletResponse response) throws IOException {
        String newFile = "D:\\Desktop\\test.xlsx";

//        String filePath0 = "D:\\Desktop\\0.xlsx";
        String filePath1 = "D:\\Desktop\\1.xlsx";
        String filePath2 = "D:\\Desktop\\2.xlsx";
        String filePath3 = "D:\\Desktop\\3.xlsx";
        String filePath4 = "D:\\Desktop\\4.xlsx";
        String filePath5 = "D:\\Desktop\\5.xlsx";
        String filePath6 = "D:\\Desktop\\6.xlsx";
        String filePath7 = "D:\\Desktop\\7.xlsx";
        String filePath8 = "D:\\Desktop\\8.xlsx";
        String filePath9 = "D:\\Desktop\\9.xlsx";
        String filePath10 = "D:\\Desktop\\10.xlsx";
        String filePath11 = "D:\\Desktop\\11.xlsx";
        String filePath12 = "D:\\Desktop\\12.xlsx";
        String filePath13 = "D:\\Desktop\\13.xlsx";
        String filePath14 = "D:\\Desktop\\14.xlsx";
        String filePath15 = "D:\\Desktop\\15.xlsx";
        String filePath16 = "D:\\Desktop\\16.xlsx";
        String filePath17 = "D:\\Desktop\\17.xlsx";
        String filePath18 = "D:\\Desktop\\18.xlsx";
        String filePath19 = "D:\\Desktop\\19.xlsx";
        String filePath20 = "D:\\Desktop\\20.xlsx";
        String filePath21 = "D:\\Desktop\\21.xlsx";
        String filePath22 = "D:\\Desktop\\22.xlsx";
        String filePath23 = "D:\\Desktop\\23.xlsx";
        String filePath24 = "D:\\Desktop\\24.xlsx";
        String filePath25 = "D:\\Desktop\\25.xlsx";
        String filePath26 = "D:\\Desktop\\26.xlsx";
        String filePath27 = "D:\\Desktop\\27.xlsx";
        String filePath28 = "D:\\Desktop\\28.xlsx";
        String filePath29 = "D:\\Desktop\\29.xlsx";
        String filePath30 = "D:\\Desktop\\30.xlsx";
        String filePath31 = "D:\\Desktop\\31.xlsx";
        String filePath32 = "D:\\Desktop\\32.xlsx";
        String filePath33 = "D:\\Desktop\\33.xlsx";
        String filePath34 = "D:\\Desktop\\34.xlsx";
        String filePath35 = "D:\\Desktop\\35.xlsx";
        String filePath36 = "D:\\Desktop\\36.xlsx";
        String filePath37 = "D:\\Desktop\\37.xlsx";
        String filePath38 = "D:\\Desktop\\38.xlsx";
        String filePath39 = "D:\\Desktop\\39.xlsx";
        String filePath40 = "D:\\Desktop\\40.xlsx";
        String filePath41 = "D:\\Desktop\\41.xlsx";
        String filePath42 = "D:\\Desktop\\42.xlsx";
        String filePath43 = "D:\\Desktop\\43.xlsx";
        String filePath44 = "D:\\Desktop\\44.xlsx";
        String filePath45 = "D:\\Desktop\\45.xlsx";
        String filePath46 = "D:\\Desktop\\46.xlsx";
        String filePath47 = "D:\\Desktop\\47.xlsx";

        List<String> filePaths = new ArrayList<>();
//        filePaths.add(filePath0);
        filePaths.add(filePath1);
        filePaths.add(filePath2);
        filePaths.add(filePath3);
        filePaths.add(filePath4);
        filePaths.add(filePath5);
        filePaths.add(filePath6);
        filePaths.add(filePath7);
        filePaths.add(filePath8);
        filePaths.add(filePath9);
        filePaths.add(filePath10);
        filePaths.add(filePath11);
        filePaths.add(filePath12);
        filePaths.add(filePath13);
        filePaths.add(filePath14);
        filePaths.add(filePath15);
        filePaths.add(filePath16);
        filePaths.add(filePath17);
        filePaths.add(filePath18);
        filePaths.add(filePath19);
        filePaths.add(filePath20);
        filePaths.add(filePath21);
        filePaths.add(filePath22);
        filePaths.add(filePath23);
        filePaths.add(filePath24);
        filePaths.add(filePath25);
        filePaths.add(filePath26);
        filePaths.add(filePath27);
        filePaths.add(filePath28);
        filePaths.add(filePath29);
        filePaths.add(filePath30);
        filePaths.add(filePath31);
        filePaths.add(filePath32);
        filePaths.add(filePath33);
        filePaths.add(filePath34);
        filePaths.add(filePath35);
        filePaths.add(filePath36);
        filePaths.add(filePath37);
        filePaths.add(filePath38);
        filePaths.add(filePath39);
        filePaths.add(filePath40);
        filePaths.add(filePath41);
        filePaths.add(filePath42);
        filePaths.add(filePath43);
        filePaths.add(filePath44);
        filePaths.add(filePath45);
        filePaths.add(filePath46);
        filePaths.add(filePath47);

        FileMergeUtils.combineMultipleExcel(filePaths, newFile);
    }

}
