package com.url.shortner.service;

public interface UrlConverterService {

    /**
     * Method receive the short url
     * and return the long url.
     *
     * @param url short url {@link String }
     * @return return the long url {@link String }
     */
    String getShortUrl(String url);

    /**
     * Method receive the long url
     * and return the short url.
     *
     * @param subUrl {@link String }
     * @return return the short url {@link String }
     */
    String getLongUrl(String subUrl);
}
