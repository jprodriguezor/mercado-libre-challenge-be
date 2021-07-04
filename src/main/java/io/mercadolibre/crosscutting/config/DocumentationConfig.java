package io.mercadolibre.crosscutting.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * DocumentationConfig.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@OpenAPIDefinition(
    info =
        @Info(
            title = "Mercado Libre API",
            version = "1.0",
            contact = @Contact(name = "Support", email = "jrodriguezor@gmail.com")))
public class DocumentationConfig {

  public DocumentationConfig() {
    super();
  }
}
