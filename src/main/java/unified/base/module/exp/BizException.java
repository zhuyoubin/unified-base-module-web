package unified.base.module.exp;

import unified.base.module.resp.ResultEnum;

/**
 * @version 1.0.0
 * @Date: 2023/6/2 16:43
 * @Copyright (C) ZhuYouBin
 * @Description: 自定义异常类
 */
public class BizException extends RuntimeException {
    /** 状态码 */
    private int code;
    /** 提示信息 */
    private String msg;

    public BizException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 支持占位符【{}】替换的构造方法
     * @param message 提示消息
     * @param args 替换参数
     */
    public BizException(String message, Object... args) {
        this.code = ResultEnum.ERROR.getCode();
        this.msg = message;
        int len = args.length;
        if (len > 0) {
            message = "<START>" + message + "<END>";
            String[] split = message.split("\\{}");
            StringBuilder sb = new StringBuilder(split[0]);
            int index = 0;
            for (int i = 1; i < split.length && index < len; i++) {
                sb.append(args[index++]);
                sb.append(split[i]);
            }
            this.msg = sb.toString().replaceAll("<START>", "").replaceAll("<END>", "");
        }
    }

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    protected BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
