package com.ms.common.base;


import lombok.Getter;

@Getter
public enum ApiResultCodeMessage {

    SUCCESS(0, "success"),
    FILURE(-1, "filure"),

    REQUEST_ERROR(400, "请求错误"),
    UNAUTHORIZED(401, "未授权"),
    NOT_ACCESSIBLE(403, "不可访问"),
    METHOD_NOT_ALLOWED(405, "方法不被允许"),
    UNSUPPORTED_MEDIA_TYPE(415, "不支持当前媒体类型"),

    APP_NAME_ILLEGAL(400, "app_name 不合法"),
    APP_NAME_NULL(400, "app_name 为空"),
    APP_ID_NULL(400, "app_id 为空"),
    APP_SECRET_NULL(400, "app_secret 为空"),
    APP_NOT_EXISTS(400, "app_id 不存在"),
    APP_ID_NOT_EXISTS(400, "app_id 不存在"),
    APP_ID_OR_SECRET_ERROR(400, "app_id 或者 app_secret 错误"),
    TTG_NULL(400, "缺少请求头TTG"),
    TTG_ILLEGAL(400, "TTG 不合法"),
    TTG_PARAM_OS_NULL(400, "TTG os 参数为空"),
    TTG_PARAM_OS_ILLEGAL(400, "TTG os 参数不合法,只能是android,ios,windows,osx,linux"),

    USER_ALREADY_EXISTS(400, "用户名已存在"),


    USER_REGISTERED_FILURE(400, "用户注册失败"),
    PASSWORD_PARAM_NULL(400, "密码为null"),
    USERNAME_PARAM_NULL(400, "用户名为null"),
    USERNAME_EXISTS(400, "用户名为null"),
    USERNAME_NOT_EXISTS(400, "用户不存在"),
    WECHAT_LOGIN_FILURE(400, "微信登录失败"),

    // github 验证失败
    GITHUB_BAD_VERIFICATION_CODE(3100, "bad_verification_code"),
    GITHUB_CREATE_USER_FILURE(3200, "github 创建账户失败"),
    GITHUB_ILLEGAL_CALLBACK(3200, "非法的回调请求"),


    LOGIN_USERNAME_NOT_EXISTS(3000, ""),
    REGISTERED_USERNAME_EXISTS(3000, ""),


    PARAMS_NULL_USERNAME(3000, "用户名为空"),
    PARAMS_NULL_PASSWORD(3000, "密码为空"),
    PARAMS_NULL_CLASSIFICATION(3000, "类别为空"),
    PARAMS_NULL_TOKEN(3000, "密码为空"),
    PASSWORD_ERROR(3000, "密码错误"),

    PARAMS_NULL_FILE(3000, "文件为空"),

    ;

    private Integer code;
    private String message;

    ApiResultCodeMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiResultCodeMessage getByCode(String code) {
        for (ApiResultCodeMessage apiResultCodeMessage : ApiResultCodeMessage.values()) {
            if (apiResultCodeMessage.getCode().equals(code)) {
                return apiResultCodeMessage;
            }
        }
        return null;
    }
}