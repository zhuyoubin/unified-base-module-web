package unified.base.module.resp;
 
/**
 * @version 1.0.0
 * @Date: 2023/6/2 16:21
 * @Copyright (C) ZhuYouBin
 * @Description 统一响应结果枚举类
 */
public enum ResultEnum {
    OK(20000, "操作成功"),
    ERROR(50000, "服务异常");
    
    /** 状态码 */
    private int code;
    /** 提示信息 */
    private String message;
 
    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
}