package pizza.messaging;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

  /**
   * By default, message conversion is performed with SimpleMessageConverter, which is able to convert simple types
   * (like String) and Serializable objects to Message objects.
   */
  @Bean
  public Jackson2JsonMessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }

}
