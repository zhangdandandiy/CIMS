package com.ruoyi.common.exception.user;

/**
 * 用户信息不同步
 *
 * @author Dandan
 * @date 2024/3/11 12:52
 **/
public class UserInfoNoSyncException extends UserException {

    private static final long serialVersionUID = 1L;

    public UserInfoNoSyncException() {
        super("user.information.is.out.of.sync", null);
    }

}
