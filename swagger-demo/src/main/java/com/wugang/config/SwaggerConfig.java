package com.wugang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 //开启swagger2
public class SwaggerConfig {
    //配置了 swagger 的 Docket 的bean实例

    //如何设置多个分组？
    //配置多个Docket实例即可

    @Bean
    public Docket A(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket B(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }

    @Bean
    public Docket docket(Environment environment) {
        //如何配置swagger只在开发、测试环境中显示？

        //设置要监听的spring环境
        Profiles profiles = Profiles.of("dev", "test");

        //通过environment.acceptsProfiles(Profiles) 判断是否处在自己设定的环境中
        boolean acceptsProfiles = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //设置分组名称
                .groupName("张三")
                //.enable(false) 是否启用swagger，如果为false，则swagger不能在浏览器中访问
                .enable(acceptsProfiles)
                .select()
                //RequestHandlerSelectors  配置要扫描接口的方式
                //.basePackage() 指定要扫描的包
                //.any() 扫描全部
                //.none() 不扫描
                //.withClassAnnotation() 扫描类上的注解，参数是一个注解的反射对象
                //.withMethodAnnotation() 扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.wugang.controller"))
                //paths() 扫描过滤
                //.ant("") 指定扫描哪个路径下的api
                //.paths(PathSelectors.ant("/zhangs/**"))
                .build();
    }

    //配置swagger信息-->apiInfo
    private ApiInfo apiInfo() {

        //作者信息
        Contact DEFAULT_CONTACT = new Contact("wugang",
                "https://ynwugang.github.io/",
                "996528491@qq.com");

        return new ApiInfo("张三的SwaggerAPI文档",
                "书山有路勤为径，学海无涯苦作舟",
                "v1.0",
                "https://ynwugang.github.io/",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
