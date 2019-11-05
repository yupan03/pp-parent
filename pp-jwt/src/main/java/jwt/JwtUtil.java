package jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jwt.constant.JwtConstant;
import jwt.constant.TokenType;

public class JwtUtil {
    private final String CLAIM_KEY_ACCOUNT = "account";
    private final String CLAIM_KEY_LOGIN_TIME = "loginTime";
    private final String CLAIM_KEY_TYPE = "type";

    private final String secret = JwtConstant.SECRET;

    private Long expiration = JwtConstant.EXPIRATION;

    private SignatureAlgorithm signatureAlgorithm = JwtConstant.SIGNATUREALGORITHM;

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
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                // 加密
                .signWith(signatureAlgorithm, secret)

                .compact();
    }

    public LoginAccount getAccountFromToken(String token) {
        String bearer = "Bearer ";
        if (token.startsWith(bearer)) {
            token = token.substring(bearer.length());
        }

        try {
            Claims claims = getClaimsFromToken(token);

            LoginAccount account = new LoginAccount();

            account.setUsername(claims.get(CLAIM_KEY_ACCOUNT).toString());
            account.setLoginTime(claims.get(CLAIM_KEY_LOGIN_TIME).toString());
            account.setType(claims.get(CLAIM_KEY_TYPE).toString());

            // 比较过期时间和当前时间的差
            long expiration = getClaimsFromToken(token).getExpiration().getTime();

            Long now = new Date().getTime();

            if (now >= expiration) {
                account.setTokenType(TokenType.OVERDUE);
            } else if (now - expiration >= 5 * 60 * 1000) {
                // token离过期时间五分钟刷新
                account.setTokenType(TokenType.WILL_EXPIRE);
            } else {
                account.setTokenType(TokenType.NORMAL);
            }

            return account;
        } catch (Exception e) {
            e.printStackTrace();
            // 非法token
            return null;
        }
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}