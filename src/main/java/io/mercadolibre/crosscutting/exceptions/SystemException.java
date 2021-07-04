package io.mercadolibre.crosscutting.exceptions;

import io.mercadolibre.crosscutting.domain.constants.ErrorCode;

/**
 * SystemException.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
public class SystemException extends ApplicationException {

  private static final long serialVersionUID = 1L;

  /**
   * @param errorType
   * @param message
   * @param cause
   * @param payload
   */
  public SystemException(final ErrorCode code, final String message, final Throwable cause) {
    super(code, message, cause);
  }
}
