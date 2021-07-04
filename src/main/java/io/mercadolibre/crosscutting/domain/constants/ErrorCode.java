package io.mercadolibre.crosscutting.domain.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * ErrorCode.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
  DEFAULT_ERROR_CODE(HttpStatus.INTERNAL_SERVER_ERROR),
  NO_SOLUTION_FOUND(HttpStatus.CONFLICT),
  EXTERNAL_COMMUNICATION(HttpStatus.INTERNAL_SERVER_ERROR);

  private HttpStatus httpStatus;
}
