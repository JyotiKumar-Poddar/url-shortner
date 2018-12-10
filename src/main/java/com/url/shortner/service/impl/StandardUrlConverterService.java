package com.url.shortner.service.impl;


import com.url.shortner.decoder.Base62EncoderDecoder;
import com.url.shortner.repository.RedisRepository;
import com.url.shortner.service.UrlConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Class for interacting with redis repository and
 * doing encoding and decoding on request url.
 */
@Service
@Slf4j
public class StandardUrlConverterService implements UrlConverterService {

    @Value("${application.host.url}")
    private String hostUrl;

    @Value("${application.host.errorUrl}")
    private String errorUrl;

    private final Base62EncoderDecoder base62EncoderDecoder;
    private final RedisRepository redisRepository;

    public StandardUrlConverterService(final Base62EncoderDecoder base62EncoderDecoder, final RedisRepository redisRepository) {
        this.base62EncoderDecoder = base62EncoderDecoder;
        this.redisRepository = redisRepository;
    }

    @Override
    public String getShortUrl(final String url) {
        final StringBuilder shortURL = new StringBuilder(hostUrl);
        final Long sequenceId = redisRepository.save(url);
        if (sequenceId > 0) {
            shortURL.append(base62EncoderDecoder.baseToBase62(sequenceId));
        } else {
            shortURL.append("Internal server error");
        }
        log.info("Long url:{} Short url:{}", url, shortURL);
        return shortURL.toString();
    }


    @Override
    public String getLongUrl(final String subUrl) {
        final long urlId = base62EncoderDecoder.base62ToBase10(subUrl);
        final String longUrlById = redisRepository.fetch(urlId);
        // if the id is not in the system then return the application url
        return StringUtils.isEmpty(longUrlById) ? errorUrl : longUrlById;
    }

}
