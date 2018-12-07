package com.url.shortner.repository.impl;

import com.url.shortner.repository.RedisRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class StandardRedisRepositoryTest {

    private final String keyValue = "https://github.com/TEST-TEST/";
    private RedisRepository redisRepository;
    private long keyId;


    @Autowired
    RedisTemplate redisTemplate;


    @Before
    public void setUp() throws Exception {
        redisRepository = new StandardRedisRepository(redisTemplate);
    }


    @Test
    public void save() throws Exception {
        keyId = redisRepository.save(keyValue);
        Assert.assertNotNull(keyId);
    }

    @Test
    public void fetch() throws Exception {
        keyId = redisRepository.save(keyValue);
        Assert.assertEquals(keyValue, redisRepository.fetch(keyId));
    }

}