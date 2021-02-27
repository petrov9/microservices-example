package org.example.license;

import lombok.extern.slf4j.Slf4j;
import org.example.license.config.ServiceConfig;
import org.example.license.model.CheckerChangeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@EnableCircuitBreaker
@EnableEurekaClient
@EnableBinding(Sink.class)
@Slf4j
public class LicenseApp {

    @Autowired
    private ServiceConfig serviceConfig;

    public static void main(String[] args) {
        SpringApplication.run(LicenseApp.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void loggerSink(CheckerChangeModel model) {
        log.info("Received an {} event from checker service for license {}", model.getAction(), model.getLicense());
    }

    /*Have to be created in config package, but according to book*/
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        String hostname = serviceConfig.getRedisServer();
        int port = Integer.parseInt(serviceConfig.getRedisPort());

        RedisStandaloneConfiguration redisStandaloneConfig = new RedisStandaloneConfiguration(hostname, port);
        return new JedisConnectionFactory(redisStandaloneConfig);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());

        return template;
    }
}
