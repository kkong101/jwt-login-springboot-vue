package main.demo.configuration.exception.client;

public class FaultParamException extends RuntimeException{

    private static final long serialVersionUID = -6665956372005037314L;

    private final static String message = "잘못된 파라미터입니다.";


    public FaultParamException() {
        super(message);
    }
}
