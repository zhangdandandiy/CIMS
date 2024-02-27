package com.ruoyi.system.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分片文件实体类
 *
 * @author Dandan
 * @date 2023/8/14 14:00
 **/
@Data
public class CheckChunkVo implements Serializable {

    private boolean skipUpload = false;

    private String url;

    private List<Integer> uploaded = new ArrayList<>();

    private boolean needMerge = true;

    // 暂时没用上，通过设置此标识，可以在前端做判断，将一些状态码为200却有问题的返回信息进行拦截，前端相关代码已注释
    private boolean result = true;

}
