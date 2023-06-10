package unified.base.module.resp;

import java.io.Serializable;

/**
 * @version 1.0.0
 * @Date: 2023/6/2 16:21
 * @Copyright (C) ZhuYouBin
 * @Description: 统一响应结果类
 */
public class Result<T> implements Serializable {
    /** 状态码 */
    private int code;
    /** 提示信息 */
    private String message;
    /** 响应数据 */
    private T data;

    //************************** common method **************************/
    /** 响应成功 */
    public static <T> Result<T> ok() {
        Result <T> result = new Result<>();
        result.code(ResultEnum.OK.getCode()).message(ResultEnum.OK.getMessage());
        return result;
    }
    /** 响应成功 */
    public static <T> Result<T> ok(int code) {
        Result<T> result = new Result<>();
        result.code(code).message(ResultEnum.OK.getMessage());
        return result;
    }
    /** 响应成功 */
    public static <T> Result<T> ok(String message) {
        Result<T> result = new Result<>();
        result.code(ResultEnum.OK.getCode()).message(message);
        return result;
    }
    /** 响应成功 */
    public static <T> Result<T> ok(int code, String message) {
        Result<T> result = new Result<>();
        result.code(code).message(message);
        return result;
    }
    /** 响应成功 */
    public static <T> Result<T> ok(int code, String message, T data) {
        Result<T> result = new Result<>();
        result.code(code).message(message).data(data);
        return result;
    }
    /** 响应成功 */
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.code(ResultEnum.OK.getCode()).message(ResultEnum.OK.getMessage()).data(data);
        return result;
    }
    /** 响应成功 */
    public static <T> Result<T> ok(String message, T data) {
        Result<T> result = new Result<>();
        result.code(ResultEnum.OK.getCode()).message(message).data(data);
        return result;
    }
    /** 响应成功 */
    public static <T> Result<T> ok(int code, T data) {
        Result<T> result = new Result<>();
        result.code(code).message(ResultEnum.OK.getMessage()).data(data);
        return result;
    }

    /** 响应失败 */
    public static <T> Result<T> error() {
        Result <T> result = new Result<>();
        result.code(ResultEnum.ERROR.getCode()).message(ResultEnum.ERROR.getMessage());
        return result;
    }
    /** 响应失败 */
    public static <T> Result<T> error(int code) {
        Result<T> result = new Result<>();
        result.code(code).message(ResultEnum.ERROR.getMessage());
        return result;
    }
    /** 响应失败 */
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.code(ResultEnum.ERROR.getCode()).message(message);
        return result;
    }
    /** 响应失败 */
    public static <T> Result<T> error(int code, String message) {
        Result<T> result = new Result<>();
        result.code(code).message(message);
        return result;
    }
    /** 响应失败 */
    public static <T> Result<T> error(int code, String message, T data) {
        Result<T> result = new Result<>();
        result.code(code).message(message).data(data);
        return result;
    }
    /** 响应失败 */
    public static <T> Result<T> error(T data) {
        Result<T> result = new Result<>();
        result.code(ResultEnum.ERROR.getCode()).message(ResultEnum.ERROR.getMessage()).data(data);
        return result;
    }
    /** 响应失败 */
    public static <T> Result<T> error(String message, T data) {
        Result<T> result = new Result<>();
        result.code(ResultEnum.ERROR.getCode()).message(message).data(data);
        return result;
    }
    /** 响应失败 */
    public static <T> Result<T> error(int code, T data) {
        Result<T> result = new Result<>();
        result.code(code).message(ResultEnum.ERROR.getMessage()).data(data);
        return result;
    }

    /** 自定义响应状态码 */
    public Result<T> code(int code) {
        this.setCode(code);
        return this;
    }
    /** 自定义响应提示信息 */
    public Result<T> message(String message) {
        this.setMessage(message);
        return this;
    }
    /** 自定义响应数据 */
    public Result<T> data(T data) {
        this.setData(data);
        return this;
    }

    //************************** common method **************************/
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
