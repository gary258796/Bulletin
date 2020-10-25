package gary.springframework.bulletin.normalstuff.exception.exceptionHandler;

import gary.springframework.bulletin.data.model.response.GenericResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // 攔截並處理應用程式中全部Controller所拋出的Exception例外錯誤
public class HandleValidationExceptions extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public HandleValidationExceptions(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("handleMethodArgumentNotValid");
        logger.error("400 Status Code", ex);
        final BindingResult result = ex.getBindingResult();
        final GenericResponse bodyOfResponse = new GenericResponse(result.getAllErrors(), "Invalid" + result.getObjectName());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("handleBindException");
        logger.error("400 Status Code", ex);
        final BindingResult result = ex.getBindingResult();
        final GenericResponse bodyOfResponse = new GenericResponse(result.getAllErrors(), "Invalid" + result.getObjectName());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    // 500
    @ExceptionHandler({ MailAuthenticationException.class })
    public ResponseEntity<Object> handleMail(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        final GenericResponse bodyOfResponse = new GenericResponse(messageSource.getMessage("message.email.config.error", null, request.getLocale()), "MailError");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

