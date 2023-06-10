package unified.base.module.exp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import unified.base.module.resp.Result;

import java.util.List;

/**
 * @version 1.0.0
 * @Date: 2023/6/2 16:44
 * @Copyright (C) ZhuYouBin
 * @Description: 全局异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 统一异常处理
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Result<Void> commonHandler(Exception e) {
        logger.error("异常信息: ", e);
        return Result.error(e.getMessage());
    }

    /**
     * 捕获我们自定义的异常
     */
    @ExceptionHandler(value = {BizException.class})
    @ResponseBody
    public Result<Void> bizExceptionHandler(BizException e) {
        logger.error("异常信息: ", e);
        if (e.getCode() != 0) {
            return Result.error(e.getCode(), e.getMsg());
        }
        return Result.error(e.getMessage());
    }

    /** 参数校验异常处理 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<Void> exception(MethodArgumentNotValidException e) {
        logger.error("异常信息: ", e);
        String message = "参数校验失败";
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(allErrors)) {
            message = allErrors.get(0).getDefaultMessage();
        }
        return Result.error(message);
    }
}
