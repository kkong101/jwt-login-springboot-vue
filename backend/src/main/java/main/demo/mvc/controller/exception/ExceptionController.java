package main.demo.mvc.controller.exception;

import main.demo.configuration.exception.CommonException;
import main.demo.configuration.exception.client.FaultParamException;
import main.demo.domain.basement.type.ResponseType;
import main.demo.domain.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({FaultParamException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Message wrong_parameter_exception(CommonException e) {
        return Message.error(e.getResponseType().getCode(), e.getResponseType().getMessage());
    }


    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Message exception(Exception e) {
        e.printStackTrace();
        return Message.error(ResponseType.SEVER_ERROR.getCode(), ResponseType.SEVER_ERROR.getMessage());
    }
}
