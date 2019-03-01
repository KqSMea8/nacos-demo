package com.wxbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient //声明eureka客户端
@SpringBootApplication
@EnableHystrix
@EnableFeignClients
//@PropertySource(value = {"classpath:redis.properties","classpath:jdbc.properties","classpath:rabbitmq.properties"}, ignoreResourceNotFound = true)
public class IVApplication {
    public static void main(String[] args) {
        SpringApplication.run(IVApplication.class, args);
    }

    @Bean // 向Spring容器中定义RestTemplate对象
    @LoadBalanced //开启负载均衡
    public RestTemplate restTemplate() {

        OkHttp3ClientHttpRequestFactory requestFactory = new OkHttp3ClientHttpRequestFactory();
        requestFactory.setConnectTimeout(20000);
        requestFactory.setReadTimeout(20000);
        requestFactory.setWriteTimeout(20000);
        return new RestTemplate(requestFactory);
    }
}

@Configuration
@EnableSwagger2
class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors
                .basePackage("com.wxbc")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Qogir项目qogirCart,平台端").description("Qogir项目qogirCart可以直接访问的接口")
                .version("0.1")
                .build();
    }

}
