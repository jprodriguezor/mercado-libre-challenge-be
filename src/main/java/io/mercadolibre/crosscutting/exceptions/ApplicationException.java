package io.mercadolibre.crosscutting.exceptions;

import io.mercadolibre.crosscutting.domain.constants.ErrorCode;
import lombok.Getter;

/**
 * ApplicationException.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@Getter
public class ApplicationException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private ErrorCode code;

  public ApplicationException(final ErrorCode code, final String message, final Throwable cause) {
    super(message, cause);
    this.code = code;
  }
}
