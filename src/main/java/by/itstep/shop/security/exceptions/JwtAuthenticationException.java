package by.itstep.shop.security.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServlet;

public class JwtAuthenticationException extends AuthenticationException {

    private HttpStatus httpStatus;

    public JwtAuthenticationException(String msg, Throwable cause, HttpStatus httpStatus) {
        super(msg, cause);
        this.httpStatus = httpStatus;
    }

    public JwtAuthenticationException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public JwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
