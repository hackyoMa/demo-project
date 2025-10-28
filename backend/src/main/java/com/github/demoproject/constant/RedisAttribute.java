package com.github.demoproject.constant;

/**
 * RedisAttribute
 *
 * @author hackyo
 * @since 2022/4/1
 */
public final class RedisAttribute {

    public static final String SEPARATOR = ":";
    public static final String CACHE_PREFIX = "cache:";
    public static final String TOKEN_PREFIX = "token:";
    public static final String LOCK_PREFIX = "lock:";

    public enum LockType {
        cache
    }

}
