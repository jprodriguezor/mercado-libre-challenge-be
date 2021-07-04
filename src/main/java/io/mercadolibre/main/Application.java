package io.mercadolibre.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Application.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@SpringBootApplication(
    scanBasePackages = {
      "io.mercadolibre.crosscutting.config",
      "io.mercadolibre.crosscutting.exceptions.handler",
      "io.mercadolibre.modules"
    })
public class Application {

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
