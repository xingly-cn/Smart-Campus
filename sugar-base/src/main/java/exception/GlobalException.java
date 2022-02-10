package exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import result.R;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalException  {

    // 全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("请求异常：" + e.getMessage());
    }

}
