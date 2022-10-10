package app.jjickda.global.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select() // ApiSelectorBuilder 생성
                .apis(RequestHandlerSelectors.basePackage("app.jjickda.api")) // API 패키지 경로
                .paths(PathSelectors.ant("/api/**")) // path 조건에 따라서 API 문서화
                .build()
                .apiInfo(apiInfo()) // API 문서에 대한 정보 추가
                .useDefaultResponseMessages(false) // swagger에서 제공하는 기본 응답 코드 설명 제거
                //.ignoredParameterTypes(MemberInfo.class) //제외할 파라미터
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("찍다 API 문서")
                .description("API에 대해서 설명해주는 문서입니다.")
                .version("1.0")
                .build();
    }
}
