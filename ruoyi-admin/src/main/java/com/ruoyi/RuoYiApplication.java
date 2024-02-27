package com.ruoyi;

import com.ruoyi.web.core.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 启动程序
 *
 * @author Dandan
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import(CorsConfig.class)
public class RuoYiApplication {

    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("O(∩_∩)O    启动成功    哈哈~    \n" +
                " .---     ---.         .---.             \n" +
                " |  |     |  |        /     \\           \n" +
                " |  |     |  |       /  /`\\  \\         \n" +
                " |  |_____|  |      /  /___\\  \\        \n" +
                " |  |_____|  |     /  /_____\\  \\       \n" +
                " |  |     |  |    /  /       \\  \\      \n" +
                " |  |     |  |   /  /         \\  \\     \n" +
                " |__|     |__|  /__/           \\__\\    \n");
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // 配置 MappingJackson2HttpMessageConverter
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);

        return restTemplate;
    }

}
