package com.pp.service.outside;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TokenStorage {
    private String token;
    // 过期时间
    private volatile long expiresTime;

    // 加锁，一次只能一个进行获取写入token
    private Lock tokenLock = new ReentrantLock();

    public boolean isTokenExpired() {
        return System.currentTimeMillis() > expiresTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        // 两小时少4分钟过期时间
        this.expiresTime = System.currentTimeMillis() + (2 * 60 * 60 - 4 * 60) * 1000;
    }

    public Lock getTokenLock() {
        return tokenLock;
    }

    public void setTokenLock(Lock tokenLock) {
        this.tokenLock = tokenLock;
    }
}