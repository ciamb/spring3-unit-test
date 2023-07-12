package com.ciamb.spring3stuff.global;

import com.ciamb.spring3stuff.user.exception.EmailAlreadyUsedException;
import com.ciamb.spring3stuff.user.exception.UserNotFoundException;
import com.ciamb.spring3stuff.user.exception.UsernameAlreadyUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    protected ProblemDetail handleUserNotFoundException(UserNotFoundException ex) {
        ProblemDetailWrapper pd = new ProblemDetailWrapper();
        pd.setStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("UserNotFoundException");
        pd.setDetail(ex.toString());
        return pd;
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    protected ProblemDetail handleEmailAlreadyUsedException(EmailAlreadyUsedException ex) {
        ProblemDetailWrapper pd = new ProblemDetailWrapper();
        pd.setStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("EmailAlreadyUsedException");
        pd.setDetail(ex.toString());
        return pd;
    }

    @ExceptionHandler(UsernameAlreadyUsedException.class)
    protected ProblemDetail handleUsernameAlreadyUedException(UsernameAlreadyUsedException ex) {
        ProblemDetailWrapper pd = new ProblemDetailWrapper();
        pd.setStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("UsernameAlreadyUsedException");
        pd.setDetail(ex.toString());
        return pd;
    }
}
