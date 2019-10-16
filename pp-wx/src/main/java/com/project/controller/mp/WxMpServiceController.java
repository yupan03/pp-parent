package com.project.controller.mp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.constant.BusinessStatus;

import common.result.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Api(tags = "微信公众号服务")
@RestController
@RequestMapping(value = "/wx/mp/service")
public class WxMpServiceController {
    @Autowired
    private WxMpService wxMpService;

    /**
     ** 网页授权获取微信用户信息
     * 
     * @param code
     * @return
     */
    @ApiOperation(value = "网页授权获取微信用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "微信重定位code", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "自定义校验参数", required = true, dataType = "String", paramType = "query") })
    @PostMapping(value = "/getUserInfoByCode")
    public ResultObj<?> getUserInfoByCode(String code, String state) {
        if (StringUtils.isBlank(code)) {
            return new ResultObj<>(BusinessStatus.ERROR_PARAM.status, "code不能为空");
        }
        if (StringUtils.isBlank(state) && !"true".equals(state)) {
            return new ResultObj<>(BusinessStatus.ERROR_PARAM.status, "非法请求");
        }

        ResultObj<WxMpUser> responseResult = new ResultObj<>(BusinessStatus.SUCCESS.status);
        WxMpUser userInfo = null;
        try {
            // 网页授权code获取获取认证信息
            WxMpOAuth2AccessToken oauth2getAccessToken = wxMpService.oauth2getAccessToken(code);
            // 获取微信用户基本信息
            userInfo = wxMpService.getUserService().userInfo(oauth2getAccessToken.getOpenId());
            if (userInfo == null) {
                return new ResultObj<>(BusinessStatus.ERROR.status, "获取微信用户信息为空");
            }
            responseResult.setData(userInfo);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return new ResultObj<>(BusinessStatus.ERROR.status, "获取微信用户信息错误");
        }

        return responseResult;
    }

    /**
     ** 根据openid获取微信用户信息
     * 
     * @param openId
     * @return
     */
    @ApiOperation(value = "根据openid获取微信用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "公众号openId", required = true, dataType = "String", paramType = "query") })
    @PostMapping(value = "/getUserInfoByOpenId")
    public ResultObj<?> getUserInfoByOpenId(String openId) {
        if (StringUtils.isBlank(openId)) {
            return new ResultObj<>(BusinessStatus.ERROR_PARAM.status, "openId不能为空");
        }
        ResultObj<WxMpUser> responseResult = new ResultObj<>(BusinessStatus.SUCCESS.status);
        try {
            // 获取微信用户基本信息
            responseResult.setData(wxMpService.getUserService().userInfo(openId));
        } catch (WxErrorException e) {
            e.printStackTrace();
            return new ResultObj<>(BusinessStatus.ERROR.status, "获取微信用户信息错误");
        }

        return responseResult;
    }

    /**
     ** 连接JSSDK的票据
     * 
     * @param url
     * @return
     */
    @ApiOperation(value = "连接JSSDK的票据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "使用JSSDK的地址", required = true, dataType = "String", paramType = "query") })
    @PostMapping(value = "/createJsapiSignature")
    public ResultObj<?> createJsapiSignature(String url) {
        if (StringUtils.isBlank(url)) {
            return new ResultObj<>(BusinessStatus.ERROR_PARAM.status, "url不能为空");
        }
        ResultObj<WxJsapiSignature> responseResult = new ResultObj<>(BusinessStatus.SUCCESS.status);
        try {
            responseResult.setData(wxMpService.createJsapiSignature(url));
        } catch (WxErrorException e) {
            return new ResultObj<>(BusinessStatus.ERROR.status, e.getMessage());
        }

        return responseResult;
    }

    /**
     * 获取带参数的永久二维码
     * 
     * @param secn
     */
    @ApiOperation(value = "获取带参数的永久二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneStr", value = "二维码参数", required = true, dataType = "String", paramType = "query") })
    @PostMapping(value = "/getQrCode")
    public WxMpQrCodeTicket getQrCode(String sceneStr) {
        WxMpQrCodeTicket qrCodeCreateLastTicket = null;
        try {
            qrCodeCreateLastTicket = wxMpService.getQrcodeService().qrCodeCreateLastTicket(sceneStr);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return qrCodeCreateLastTicket;
    }
}