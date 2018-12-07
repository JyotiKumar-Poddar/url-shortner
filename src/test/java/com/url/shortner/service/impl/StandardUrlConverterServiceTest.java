package com.url.shortner.service.impl;

import com.url.shortner.service.UrlConverterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StandardUrlConverterServiceTest {

    private final String longUrl = "https://github.com/TEST-TEST/";
    private final String subUrl = "vYb";

    @MockBean
    private UrlConverterService urlConverterService;

    @Test
    public void getShortUrl() throws Exception {
        final String hostUrl = "http://localhost:8080/";
        Mockito.when(urlConverterService.getShortUrl(longUrl)).thenReturn(hostUrl + subUrl);
        final String shortUrl = urlConverterService.getShortUrl(longUrl);
        assertEquals(hostUrl + subUrl, shortUrl);
    }

    @Test
    public void getLongUrl() throws Exception {
        Mockito.when(urlConverterService.getLongUrl(subUrl)).thenReturn(longUrl);
        assertEquals(longUrl, urlConverterService.getLongUrl(subUrl));
    }

}