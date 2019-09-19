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

                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000)) // 设置过期时间

                .signWith(signatureAlgorithm, secret)// 加密

                .compact();
    }

    public LoginAccount getAccountFromToken(String token) {
        try {

            Claims claims = getClaimsFromToken(token);

            LoginAccount account = new LoginAccount();

            account.setUsername((String) claims.get(CLAIM_KEY_ACCOUNT));
            account.setLoginTime((Date) claims.get(CLAIM_KEY_LOGIN_TIME));
            account.setType((String) claims.get(CLAIM_KEY_TYPE));

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
            // 非法token
            return null;
        }
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}