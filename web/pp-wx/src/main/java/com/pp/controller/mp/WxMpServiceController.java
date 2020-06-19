package com.pp.controller.mp;

import com.pp.common.exception.BizException;
import com.pp.common.result.ResultObj;
import com.pp.constant.BusinessStatus;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wx/mp/service")
public class WxMpServiceController {
    @Autowired
    private WxMpService wxMpService;

    /**
     * * 网页授权获取微信用户信息
     *
     * @param code 授权码
     * @return 用户信息
     */
    @PostMapping(value = "/getUserInfoByCode")
    public ResultObj<?> getUserInfoByCode(String code, String state) {
        if (StringUtils.isBlank(code)) {
            throw new BizException(BusinessStatus.ERROR_PARAM.status, "code不能为空");
        }
        if (StringUtils.isBlank(state) && !"true".equals(state)) {
            throw new BizException(BusinessStatus.ERROR_PARAM.status, "非法请求");
        }

        WxMpUser userInfo;
        try {
            // 网页授权code获取获取认证信息
            WxMpOAuth2AccessToken oauth2getAccessToken = wxMpService.oauth2getAccessToken(code);
            // 获取微信用户基本信息
            userInfo = wxMpService.getUserService().userInfo(oauth2getAccessToken.getOpenId());
            if (userInfo == null) {
                throw new BizException(BusinessStatus.ERROR.status, "获取微信用户信息为空");
            }
            return new ResultObj<>(BusinessStatus.SUCCESS.status, "", userInfo);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new BizException(BusinessStatus.ERROR.status, "获取微信用户信息错误");
        }

    }


    /**
     * * 根据openid获取微信用户信息
     *
     * @param openId openid
     * @return 用户信息
     */
    @PostMapping(value = "/getUserInfoByOpenId")
    public ResultObj<?> getUserInfoByOpenId(String openId) {
        if (StringUtils.isBlank(openId)) {
            throw new BizException(BusinessStatus.ERROR_PARAM.status, "openId不能为空");
        }
        try {
            // 获取微信用户基本信息
            return new ResultObj<>(BusinessStatus.SUCCESS.status, "", wxMpService.getUserService().userInfo(openId));
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new BizException(BusinessStatus.ERROR.status, "获取微信用户信息错误");
        }
    }

    /**
     * * 连接JSSDK的票据
     *
     * @param url 需要票据的地址
     */
    @PostMapping(value = "/createJsapiSignature")
    public ResultObj<?> createJsapiSignature(String url) {
        if (StringUtils.isBlank(url)) {
            throw new BizException(BusinessStatus.ERROR_PARAM.status, "url不能为空");
        }
        try {
            return new ResultObj<>(BusinessStatus.SUCCESS.status, "", wxMpService.createJsapiSignature(url));
        } catch (WxErrorException e) {
            throw new BizException(BusinessStatus.ERROR.status, e.getMessage());
        }
    }

    /**
     * 获取带参数的永久二维码
     *
     * @param sceneStr 二维码参数
     */
    @PostMapping(value = "/getQrCode")
    public WxMpQrCodeTicket getQrCode(String sceneStr) {
        try {
            return wxMpService.getQrcodeService().qrCodeCreateLastTicket(sceneStr);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new BizException(BusinessStatus.ERROR.status, "获取二维码失败");
        }
    }
}