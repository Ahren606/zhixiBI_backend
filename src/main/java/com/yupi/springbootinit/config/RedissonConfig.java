package com.yupi.springbootinit.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "spring.redis") //读取application.yml中已有的redis ip和端口配置
@Data
public class RedissonConfig {

    private String host;
    private String port;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        String address = String.format("redis://%s:%s",host,port);
        config.useSingleServer().setAddress(address).setDatabase(3);

        //创建并返回实例
        return Redisson.create(config);
    }
}
