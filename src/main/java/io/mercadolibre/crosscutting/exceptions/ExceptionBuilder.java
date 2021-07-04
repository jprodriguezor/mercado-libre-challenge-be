package io.mercadolibre.crosscutting.exceptions;

import io.mercadolibre.crosscutting.domain.constants.ErrorCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * ExceptionBuilder.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionBuilder {

  private ErrorCode code;
  private String message;
  private Throwable parentException;

  public static ExceptionBuilder builder() {
    return new ExceptionBuilder();
  }

  public ExceptionBuilder withMessage(final String param) {
    this.message = param;
    return this;
  }

  public ExceptionBuilder withCode(final ErrorCode param) {
    this.code = param;
    return this;
  }

  public ExceptionBuilder withParentException(final Throwable param) {
    this.parentException = param;
    return this;
  }

  public BusinessException buildBusinessException() {
    return new BusinessException(code, message, parentException);
  }

  public SystemException buildSystemException() {
    return new SystemException(code, message, parentException);
  }
}
