package app.jjickda.global.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.base-package}")
    private String API_BASE_PACKAGE;

    @Value("${swagger.path}")
    private String API_PATH;

    @Value("${swagger.title}")
    private String API_TITLE;

    @Value("${swagger.description}")
    private String API_DESCRIPTION;

    @Value("${swagger.version}")
    private String API_VERSION;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select() // ApiSelectorBuilder 생성
                .apis(RequestHandlerSelectors.basePackage(API_BASE_PACKAGE)) // API 패키지 경로
                .paths(PathSelectors.ant(API_PATH)) // path 조건에 따라서 API 문서화
                .build()
                .apiInfo(apiInfo()) // API 문서에 대한 정보 추가
                .useDefaultResponseMessages(false) // swagger에서 제공하는 기본 응답 코드 설명 제거
                //.ignoredParameterTypes(Mai.class) //제외할 파라미터
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(API_VERSION)
                .build();
    }
}
