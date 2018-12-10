package com.url.shortner;

import com.url.shortner.decoder.Base62EncoderDecoder;
import io.lettuce.core.ReadFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class AppConfig {

    @Autowired
    private LettuceConnectionFactory redisConnectionFactory;

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(@Value("${spring.redis.port}") int port, @Value("${spring.redis.host}") String host) {
        LettuceClientConfiguration configuration = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.MASTER)
                .build();
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port),
                configuration);
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return template;
    }

    @Bean
    public Base62EncoderDecoder base62EncoderDecoder() {
        return new Base62EncoderDecoder();
    }
}