package oauth2.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    /**
     * 获取Token(支持GET和POST方式)
     * 
     * @param clinetId
     * @param clientSecret
     * @return
     */
    @RequestMapping(value = "/token", method = { RequestMethod.GET, RequestMethod.POST })
    public String getToken(String clinetId, String clientSecret) {
        StringUtils.isEmpty(clinetId);
        StringUtils.isEmpty(clientSecret);
        // 两个都不能为空
        return "token";
    }
}