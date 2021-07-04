package io.mercadolibre.crosscutting.exceptions.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.mercadolibre.crosscutting.domain.constants.ErrorCode;
import io.mercadolibre.crosscutting.exceptions.ApplicationException;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * ApplicationExceptionHandler.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

  @ExceptionHandler(value = {ApplicationException.class})
  protected ResponseEntity<HttpExceptionResponse> handleRegularException(
      final ApplicationException ex, final WebRequest request) {

    ErrorCode errorCode = ErrorCode.DEFAULT_ERROR_CODE;
    if (ex.getCode() != null) {
      errorCode = ex.getCode();
    }

    log.error("[exception-handler][stack-trace]", ex);

    HttpExceptionResponse r =
        HttpExceptionResponse.builder().code(errorCode.name()).message(ex.getMessage()).build();

    return ResponseEntity.status(errorCode.getHttpStatus()).body(r);
  }

  /**
   * HttpExceptionResponse.
   *
   * @author jenny.rodriguez
   * @version 1.0
   * @since 2021-067-03
   */
  @Getter
  @Builder
  @JsonInclude(Include.NON_NULL)
  public static class HttpExceptionResponse {

    private String code;
    private String message;
  }
}
