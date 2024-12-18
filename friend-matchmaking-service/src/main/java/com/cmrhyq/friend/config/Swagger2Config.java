package com.cmrhyq.friend.config;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v0.0.1
 * @classname Swagger2Config.java
 * @project friend-matchmaking-service
 * @package com.cmrhyq.friend.config
 * @date 2024/12/18 13:37
 * @email cmrhyq@163.com
 * @since v0.0.1
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Swagger + knife4j 使用的配置文件
 * 地址：http://localhost:8101/api/doc.html
 */
@Configuration
@EnableSwagger2WebMvc
public class Swagger2Config {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cmrhyq.friend.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //基本信息的配置，信息会在api文档上显示
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("伙伴系统后端接口文档")
                .description("伙伴系统后端相关接口的文档")
                .termsOfServiceUrl("https://github.com/cmrhyq")
                .version("1.0")
                .build();
    }
}
