package com.url.shortner.repository.impl;

import com.url.shortner.repository.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * Class for accessing redis database using redis template {@link RedisTemplate}
 * to perform save, fetch operation.
 */

@Repository
@Slf4j
public class StandardRedisRepository implements RedisRepository {

    private static final String KEY_URL = "URL";
    private static final String KEY_ID = "ID_URL";
    private final ValueOperations<String, String> valueOperations;
    private final HashOperations<String, Long, String> hashOperations;

    public StandardRedisRepository(final RedisTemplate redisTemplate) {
        this.valueOperations = redisTemplate.opsForValue();
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Long save(final String url) {
        Long sequenceId = 0L;
        boolean isUrlNotSaved = true;
        try {
            sequenceId = valueOperations.increment(KEY_ID);
            hashOperations.put(KEY_URL, sequenceId, url);
            isUrlNotSaved = false;
            log.info("The url: {} saved with key: {}", url, sequenceId);
        } catch (final Exception e) {
            log.error("Error due to " + e);
        } finally {
            if (isUrlNotSaved)
                valueOperations.decrement(KEY_ID);
        }
        return sequenceId;
    }

    @Override
    public String fetch(final Long keyId) {
        final String longUrl = hashOperations.get(KEY_URL, keyId);
        if (longUrl != null) {
            log.info("Url fetched: {} with key: {}", longUrl, keyId);
        } else {
            log.warn(" key: {} does not exits", keyId);
        }
        return longUrl;
    }
}
