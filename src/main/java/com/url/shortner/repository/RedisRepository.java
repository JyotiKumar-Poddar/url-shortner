package com.url.shortner.repository;

public interface RedisRepository {
    /**
     * Method to save the string data and return the id of data saved
     *
     * @param data {@link String }
     * @return key of data saved {@link Long }
     */
    Long save(String data);

    /**
     * @param keyId key to get the data {@link String }
     * @return data of type {@link String }
     */
    String fetch(Long keyId);
}
