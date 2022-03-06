package main.demo.configuration.exception;


import lombok.Getter;
import main.demo.domain.basement.type.ResponseType;

@Getter
public class CommonException extends RuntimeException{
    private static final long serialVersionUID = 6466300659839571601L;

    private final ResponseType responseType;

    private Object object;

    public CommonException(ResponseType responsetype) {
        this.responseType = responsetype;
    }

    public CommonException(ResponseType responsetype,Object object) {
        this.responseType = responsetype;
        this.object = object;
    }


}
