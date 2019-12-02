package com.project.service.outside;

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
    }

    public Lock getTokenLock() {
        return tokenLock;
    }

    public void setTokenLock(Lock tokenLock) {
        this.tokenLock = tokenLock;
    }
}
