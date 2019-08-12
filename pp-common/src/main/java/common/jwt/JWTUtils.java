package common.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import common.LoginUser;
import common.result.ResultException;
import common.result.ResultStatusEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {
    /**
     ** 密钥(长度限定pp不可以会报错)
     */
    private static final String secret = "yupan03";

    /**
     ** 生成令牌
     *
     * @param userDetails 用户
     * @return 令牌
     */
    public static String generateToken(LoginUser userDetails) {
        Map<String, Object> claims = new HashMap<>(2);

        claims.put("account", userDetails.getAccount());
        claims.put("type", userDetails.getType());

        return generateToken(claims);
    }

    /**
     ** 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌1
     */
    private static String generateToken(Map<String, Object> claims) {
        // 一个月过期
        Date expirationDate = new Date(System.currentTimeMillis() + 2592000L * 1000);

        String token = Jwts.builder().setClaims(claims).setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret).compact();

        return token;
    }

    /**
     ** 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            throw new ResultException(ResultStatusEnum.ERROR, "非法token");
        }
    }

    public static LoginUser getUserFromToken(String token) {
        Claims claims = getClaimsFromToken(token);

        LoginUser user = new LoginUser();
        try {
            user.setAccount((String) claims.get("account"));
            // user.setEnable((String) claims.get("loginTime"));
        } catch (Exception e) {
            return null;
        }

        return user;
    }

    /**
     ** 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     ** 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put("created", new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }
}