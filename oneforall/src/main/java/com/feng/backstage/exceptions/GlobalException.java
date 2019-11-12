package com.feng.backstage.exceptions;

import com.feng.backstage.base.ResponseObject;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 处理全局异常、数据绑定、数据预处理
 * @author east
 * @date 19/10/22
 */
@Controller
@ControllerAdvice
public class GlobalException {

    /**
     * 返回ResponseObject封装的对象
     * @return ResponseObject
     */
    protected ResponseObject success() {
        return new ResponseObject();
    }

    /**
     * 回复ResponseObject封装的对象和data
     * @param dataObject
     * @param <T>
     * @return
     */
    protected <T> ResponseObject<T> success(T dataObject) {
        return new ResponseObject<>(dataObject);
    }

    @ExceptionHandler
    /**
     * @ResponseBody在方法头将数据返回JSON格式，便于前台处理；
     * 注解在方法修饰符之后是将接收到的数据（前台的JSON、XML等格式）转化为JavaBean对象，便于后台处理
     */
    public @ResponseBody
    ResponseObject exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        ResponseObject responseObject = new ResponseObject();
        exception.printStackTrace();

        try {
            // 自定义异常提示
            if (exception instanceof SystemException) {
                SystemException systemException = (SystemException) exception;
                responseObject.setCode(systemException.getCode());
                responseObject.setMessage(systemException.getMessage());
                return responseObject;
                // 参数无效
            } else if (exception instanceof MethodArgumentNotValidException) {
                // 请求数据不合法
                responseObject.setCode(SystemExceptionEnum.ILLEGAL_PARAM.getCode());
                String message = exception.getMessage();
                int start = message.lastIndexOf("default message");
                // 截取部分中文提示信息
                message = message.substring(start, message.length())
                        // 处理结尾
                        .replace("default message", "").replace("]]", "]");

                MethodParameter methodParameter = ((MethodArgumentNotValidException) exception).getParameter();
                String className = "," + methodParameter.getParameterType().getTypeName() + ",";
                responseObject.setMessage(SystemExceptionEnum.ILLEGAL_PARAM.getMessage() + className + message);
                return responseObject;
                // 数据库操作异常
            } else if (exception instanceof DataAccessException) {
                responseObject.setCode(SystemExceptionEnum.SQL_ERROR.getCode());
                responseObject.setMessage(SystemExceptionEnum.SQL_ERROR.getMessage());
                return responseObject;
            } else {
                // 系统内部错误
                responseObject.setCode(SystemExceptionEnum.INTERNAL_ERROR.getCode());
                responseObject.setMessage(SystemExceptionEnum.INTERNAL_ERROR.getMessage());
                return responseObject;
            }
            // 其他异常，系统内部错误
        } catch (Exception e) {
            responseObject.setCode(SystemExceptionEnum.INTERNAL_ERROR.getCode());
            responseObject.setMessage(SystemExceptionEnum.INTERNAL_ERROR.getMessage());
            return responseObject;
        }
    }

}
