package main.demo.configuration.exception;

public class FaultParamException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private final static String message = "잘못된 파라미터입니다.";

    public FaultParamException() {
        super(message);
    }
}
