package demo.app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by itaesu on 01/08/2019.
 */
@RequiredArgsConstructor
@Configuration
@EnableJpaAuditing
public class EnableAuditConfiguration {

}
