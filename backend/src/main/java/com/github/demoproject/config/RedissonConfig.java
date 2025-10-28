package com.github.demoproject.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * RedissonConfig
 *
 * @author hackyo
 * @since 2022/4/1
 */
@Configuration
public class RedissonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson(@Value("${redisson.file}") String file,
                                   @Value("${redisson.host}") String host,
                                   @Value("${redisson.port}") String port,
                                   @Value("${redisson.database}") Integer database,
                                   @Value("${redisson.password}") String password) throws IOException {
        Config config;
        if (StringUtils.hasLength(file)) {
            config = Config.fromYAML(new File(file));
        } else {
            config = new Config();
            config.useSingleServer()
                    .setAddress("redis://" + host + ":" + port)
                    .setDatabase(database)
                    .setPassword(password);
        }
        return Redisson.create(config);
    }

}
