package com.url.shortner.decoder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Base62EncoderDecoderTest {

    private Base62EncoderDecoder base62EncoderDecoder;

    @Before
    public void setUp() throws Exception {
        base62EncoderDecoder = new Base62EncoderDecoder();
    }

    @Test
    public void baseToBase62() throws Exception {
        assertEquals("b", base62EncoderDecoder.baseToBase62(1L));
        assertEquals("kemuzg", base62EncoderDecoder.baseToBase62(9_223_372_036L));
    }

    @Test
    public void base62ToBase10() throws Exception {
        assertEquals(Long.valueOf(1), base62EncoderDecoder.base62ToBase10("b"));
    }

}