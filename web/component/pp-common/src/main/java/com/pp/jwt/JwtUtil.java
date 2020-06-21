package com.pp.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private final String CLAIM_KEY_ACCOUNT = "account";
    private final String CLAIM_KEY_LOGIN_TIME = "loginTime";
    private final String CLAIM_KEY_TYPE = "type";

    private JwtProperties jwtProperties;

    public JwtUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 过期时间
     *
     * @return 过期秒数
     */
    public Long getExpiration() {
        return this.jwtProperties.getExpiration();
    }

    /**
     * 用户数据转换成token
     *
     * @param account 用户
     * @return token字符串
     */
    public String generateToken(LoginAccount account) {
        Map<String, Object> claims = new HashMap<>();

        claims.put(CLAIM_KEY_ACCOUNT, account.getUsername());
        claims.put(CLAIM_KEY_LOGIN_TIME, account.getLoginTime());
        claims.put(CLAIM_KEY_TYPE, account.getType());

        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    /**
     * 根据token获取登录用户
     *
     * @param token 字符串
     * @return 登录用户
     */
    public LoginAccount getAccountFromToken(String token) {
        String bearer = jwtProperties.getHead();
        LoginAccount account = new LoginAccount();
        if (token.startsWith(bearer)) {
            token = token.substring(bearer.length());
        }

        try {
            Claims claims = getClaimsFromToken(token);


            account.setUsername(claims.get(CLAIM_KEY_ACCOUNT).toString());
            account.setLoginTime(claims.get(CLAIM_KEY_LOGIN_TIME).toString());
            account.setType(Byte.valueOf(claims.get(CLAIM_KEY_TYPE).toString()));

            // 比较过期时间和当前时间的差
            long expiration = getClaimsFromToken(token).getExpiration().getTime();

            long now = new Date().getTime();

            if (now - expiration >= 5 * 60 * 1000) {
                // token离过期时间五分钟刷新
                account.setTokenType(TokenType.WILL_EXPIRE);
            } else {
                account.setTokenType(TokenType.NORMAL);
            }
        } catch (ExpiredJwtException e) {
            account.setTokenType(TokenType.OVERDUE);
        } catch (Exception e) {
            e.printStackTrace();
            // 非法token
            return null;
        }
        return account;
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
    }
}