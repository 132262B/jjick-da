package app.jjickda.global.config.error;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorPageConfig {
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        return new TomcatServletWebServerFactory() {
            @Override
            public void addErrorPages(ErrorPage... errorPages) {
                super.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error"));
            }

        };
    }
}
