package main.demo.domain.dto;

import lombok.Getter;
import main.demo.domain.basement.type.ResponseType;

import javax.annotation.Nullable;

@Getter
public class Message {

    private final Integer code;
    private final String message;

    public Message(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Message(ResponseType response){
        this.code = response.getCode();
        this.message = response.getMessage();
    }

    public static Message ok(){
        return new Message_Only(ResponseType.SUCCESS.getCode(), ResponseType.SUCCESS.getMessage());
    }

    public static Message ok(@Nullable Object result){
        return new Message_Has_Result<>(ResponseType.SUCCESS, result);
    }

    public static Message error(Integer code, String message){
        return new Message_Only(code, message);
    }

    public static Message error(Integer code, String message, @Nullable Object result){
        return new Message_Has_Result<>(code, message, result);
    }


    public static class Message_Only extends Message{

        public Message_Only(Integer code, String message){
            super(code, message);
        }

    }

    @Getter
    public static class Message_Has_Result<T> extends Message{

        private final T result;

        public Message_Has_Result(ResponseType response, T result) {
            super(response);
            this.result = result;
        }

        public Message_Has_Result(Integer code, String message, T result) {
            super(code, message);
            this.result = result;
        }

    }

}