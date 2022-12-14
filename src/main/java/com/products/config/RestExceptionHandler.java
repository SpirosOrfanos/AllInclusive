package com.products.config;

import javax.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.products.dto.ApiError;
import com.products.enums.ApiErrorEnum;
import com.products.exception.ActionNotSupportedException;
import com.products.exception.NoItemFoundException;
import com.products.exception.UserAlreadyExistsException;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<ApiError> handleConstraintViolationException(
      ConstraintViolationException ex) {
    ApiError apiError = ApiErrorEnum.BAD_REQUEST_EXCEPTION.create();
    apiError.setDesc(ex.getMessage());
    return buildResponseEntity(apiError);
  }
  
  @ExceptionHandler(UserAlreadyExistsException.class)
  protected ResponseEntity<ApiError> handleUserAlreadyExistsException(
      UserAlreadyExistsException ex) {
    ApiError apiError = ApiErrorEnum.USER_ALREADY_EXISTS.create();
    apiError.setDesc(ex.getMessage());
    return buildResponseEntity(apiError);
  }


  @ExceptionHandler(ActionNotSupportedException.class)
  protected ResponseEntity<ApiError> handleApiResponseException(ActionNotSupportedException ex) {
    ApiError apiError = ApiErrorEnum.API_RESPONSE_EXCEPTION.create();
    apiError.setDesc(ex.getMessage());
    return buildResponseEntity(apiError);
  }
  
  @ExceptionHandler(NoItemFoundException.class)
  protected ResponseEntity<ApiError> handleNoItemFoundException(NoItemFoundException ex) {
    ApiError apiError = ApiErrorEnum.NOT_FOUND_ERROR.create();
    apiError.setDesc(ex.getMessage());
    return buildResponseEntity(apiError);
  }
  
  @ExceptionHandler(ConversionFailedException.class)
  public ResponseEntity<ApiError> handleConflict(RuntimeException ex) {
    System.out.println("CONVERSION_FAILED_ERROR");
    return buildResponseEntity(ApiErrorEnum.CONVERSION_FAILED_ERROR.create());
  }


  private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
    return ResponseEntity.status(apiError.getHttpCode()).body(apiError);
  }

}
