package com.pp.controller.ma;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.pp.constant.BusinessStatus;
import com.pp.exception.BizException;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wx/ma/service")
public class WxMaServiceController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WxMaService wxMaService;

    /**
     * * 登陆接口
     */
    @GetMapping(value = "/login")
    public WxMaJscode2SessionResult login(String code) {
        if (StringUtils.isBlank(code)) {
            throw new BizException(BusinessStatus.ERROR.status, "code不能为空");
        }
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            return session;
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * * 获取用户信息接口
     */
    @GetMapping("/info")
    public WxMaUserInfo info(String sessionKey, String signature, String rawData, String encryptedData, String iv) {

        // 用户信息校验
        if (!wxMaService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            throw new BizException(BusinessStatus.ERROR.status, "用户信息校验失败");
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return userInfo;
    }

    /**
     * * 获取用户绑定手机号信息
     */
    @GetMapping("/phone")
    public WxMaPhoneNumberInfo phone(String sessionKey, String signature, String rawData, String encryptedData,
                                     String iv) {

        // 用户信息校验
        if (!wxMaService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            throw new BizException(BusinessStatus.ERROR.status, "用户信息校验失败");
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxMaService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        return phoneNoInfo;
    }
}