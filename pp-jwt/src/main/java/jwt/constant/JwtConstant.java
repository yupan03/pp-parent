package jwt.constant;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtConstant {
    // 加密秘钥
    public static String SECRET = "yupan03";

    // token过期时间(秒)
    public static Long EXPIRATION = 1000L;

    // 加密方式
    public static SignatureAlgorithm SIGNATUREALGORITHM = SignatureAlgorithm.HS512;
}