package com.ruoyi.common.core.page;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dandan
 * @date 2024/3/27 12:57
 **/

@Data
@NoArgsConstructor
public class TableDataInfoFlowable<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 列表数据
     */
    private List<T> rows;

    /**
     * 消息状态码
     */
    private int code;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public TableDataInfoFlowable(List<T> list, long total) {
        this.rows = list;
        this.total = total;
    }

    public static <T> TableDataInfoFlowable<T> build(IPage<T> page) {
        TableDataInfoFlowable<T> rspData = new TableDataInfoFlowable<>();
        rspData.setCode(HttpStatus.HTTP_OK);
        rspData.setMsg("查询成功");
        rspData.setRows(page.getRecords());
        rspData.setTotal(page.getTotal());
        return rspData;
    }

    public static <T> TableDataInfoFlowable<T> build(List<T> list) {
        TableDataInfoFlowable<T> rspData = new TableDataInfoFlowable<>();
        rspData.setCode(HttpStatus.HTTP_OK);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(list.size());
        return rspData;
    }

    public static <T> TableDataInfoFlowable<T> build() {
        TableDataInfoFlowable<T> rspData = new TableDataInfoFlowable<>();
        rspData.setCode(HttpStatus.HTTP_OK);
        rspData.setMsg("查询成功");
        return rspData;
    }

}
