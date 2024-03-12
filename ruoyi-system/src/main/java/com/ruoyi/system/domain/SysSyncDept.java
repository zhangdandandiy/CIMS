package com.ruoyi.system.domain;

/**
 * @author Dandan
 * @date 2024/3/9 9:52
 **/
public class SysSyncDept {

    /**
     * 关键字id，最长32位，必填
     */
    private String id;

    /**
     * 上级部门id，为空时会自动取系统根节点的id
     */
    private String parentId;

    /**
     * 部门名称，必填
     */
    private String departName;

    /**
     * 排序，必填
     */
    private Integer departOrder;

    /**
     * 名称缩写
     */
    private String departNameEn;

    /**
     * 名称缩写
     */
    private String departNameAbbr;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String memo;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Integer getDepartOrder() {
        return departOrder;
    }

    public String getAddress() {
        return address;
    }

    public String getDepartName() {
        return departName;
    }

    public String getDepartNameAbbr() {
        return departNameAbbr;
    }

    public String getDepartNameEn() {
        return departNameEn;
    }

    public String getMemo() {
        return memo;
    }

    public String getMobile() {
        return mobile;
    }

    public String getParentId() {
        return parentId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public void setDepartNameAbbr(String departNameAbbr) {
        this.departNameAbbr = departNameAbbr;
    }

    public void setDepartNameEn(String departNameEn) {
        this.departNameEn = departNameEn;
    }

    public void setDepartOrder(Integer departOrder) {
        this.departOrder = departOrder;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
