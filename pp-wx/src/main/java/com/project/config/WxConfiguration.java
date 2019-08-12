package com.project.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;
import com.project.handler.KfSessionHandler;
import com.project.handler.LocationHandler;
import com.project.handler.LogHandler;
import com.project.handler.MenuHandler;
import com.project.handler.MsgHandler;
import com.project.handler.NullHandler;
import com.project.handler.ScanHandler;
import com.project.handler.StoreCheckNotifyHandler;
import com.project.handler.SubscribeHandler;
import com.project.handler.UnsubscribeHandler;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import cn.binarywang.wx.miniapp.message.WxMaMessageHandler;
import cn.binarywang.wx.miniapp.message.WxMaMessageRouter;
import me.chanjar.weixin.common.api.WxConsts.EventType;
import me.chanjar.weixin.common.api.WxConsts.MenuButtonType;
import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;

@Configuration
@EnableConfigurationProperties({ WxMpProperties.class, WxMiniappProperties.class })
public class WxConfiguration {
    @Autowired
    private WxMpProperties wxMpProperties;
    @Autowired
    private WxMiniappProperties wxMaProperties;
    @Autowired
    private LogHandler logHandler;
    @Autowired
    private NullHandler nullHandler;
    @Autowired
    private KfSessionHandler kfSessionHandler;
    @Autowired
    private StoreCheckNotifyHandler storeCheckNotifyHandler;
    @Autowired
    private LocationHandler locationHandler;
    @Autowired
    private MenuHandler menuHandler;
    @Autowired
    private MsgHandler msgHandler;
    @Autowired
    private UnsubscribeHandler unsubscribeHandler;
    @Autowired
    private SubscribeHandler subscribeHandler;
    @Autowired
    private ScanHandler scanHandler;

    // 微信公众号
    @Bean
    public WxMpService getWxMpService() {
        WxMpService service = new WxMpServiceImpl();
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();

        configStorage.setAppId(wxMpProperties.getAppId());
        configStorage.setSecret(wxMpProperties.getSecret());
        configStorage.setToken(wxMpProperties.getToken());
        configStorage.setAesKey(wxMpProperties.getAesKey());

        service.setWxMpConfigStorage(configStorage);

        return service;
    }

    @Bean
    public WxMpMessageRouter newMpRouter(WxMpService wxMpService) {
        WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(this.logHandler).next();
        // 接收客服会话管理事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION).handler(this.kfSessionHandler).end();
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION).handler(this.kfSessionHandler).end();
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION).handler(this.kfSessionHandler).end();
        // 门店审核事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event(WxMpEventConstants.POI_CHECK_NOTIFY)
                .handler(this.storeCheckNotifyHandler).end();
        // 自定义菜单事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event(MenuButtonType.CLICK).handler(this.menuHandler)
                .end();
        // 点击菜单连接事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event(MenuButtonType.VIEW).handler(this.nullHandler)
                .end();
        // 关注事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event(EventType.SUBSCRIBE)
                .handler(this.subscribeHandler).end();
        // 取消关注事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event(EventType.UNSUBSCRIBE)
                .handler(this.unsubscribeHandler).end();
        // 上报地理位置事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event(EventType.LOCATION).handler(this.locationHandler)
                .end();
        // 接收地理位置消息
        newRouter.rule().async(false).msgType(XmlMsgType.LOCATION).handler(this.locationHandler).end();
        // 扫码事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event(EventType.SCAN).handler(this.scanHandler).end();
        // 默认
        newRouter.rule().async(false).handler(this.msgHandler).end();

        return newRouter;
    }

    // 小程序
    @Bean
    public WxMaService getWxMaService() {
        WxMaService wxMaService = new WxMaServiceImpl();
        WxMaInMemoryConfig config = new WxMaInMemoryConfig();
        config.setAppid(wxMaProperties.getAppId());
        config.setSecret(wxMaProperties.getSecret());
        config.setToken(wxMaProperties.getToken());
        config.setAesKey(wxMaProperties.getAesKey());
        config.setMsgDataFormat(wxMaProperties.getMsgDataFormat());

        wxMaService.setWxMaConfig(config);

        return wxMaService;
    }

    @Bean
    public WxMaMessageRouter newRouter(WxMaService service) {
        final WxMaMessageRouter router = new WxMaMessageRouter(service);
        router.rule().handler(logMaHandler).next().rule().async(false).content("模板").handler(templateMsgHandler).end()
                .rule().async(false).content("文本").handler(textHandler).end().rule().async(false).content("图片")
                .handler(picHandler).end().rule().async(false).content("二维码").handler(qrcodeHandler).end();
        return router;
    }

    private final WxMaMessageHandler templateMsgHandler = (wxMessage, context, service, sessionManager) -> service
            .getMsgService()
            .sendTemplateMsg(WxMaTemplateMessage.builder().templateId("此处更换为自己的模板id").formId("自己替换可用的formid")
                    .data(Lists.newArrayList(new WxMaTemplateData("keyword1", "339208499", "#173177")))
                    .toUser(wxMessage.getFromUser()).build());

    private final WxMaMessageHandler logMaHandler = (wxMessage, context, service, sessionManager) -> {
        System.out.println("收到消息：" + wxMessage.toString());
        service.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("收到信息为：" + wxMessage.toJson())
                .toUser(wxMessage.getFromUser()).build());
    };

    private final WxMaMessageHandler textHandler = (wxMessage, context, service, sessionManager) -> service
            .getMsgService()
            .sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("回复文本消息").toUser(wxMessage.getFromUser()).build());

    private final WxMaMessageHandler picHandler = (wxMessage, context, service, sessionManager) -> {
        try {
            WxMediaUploadResult uploadResult = service.getMediaService().uploadMedia("image", "png",
                    ClassLoader.getSystemResourceAsStream("tmp.png"));
            service.getMsgService().sendKefuMsg(WxMaKefuMessage.newImageBuilder().mediaId(uploadResult.getMediaId())
                    .toUser(wxMessage.getFromUser()).build());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    };

    private final WxMaMessageHandler qrcodeHandler = (wxMessage, context, service, sessionManager) -> {
        try {
            final File file = service.getQrcodeService().createQrcode("123", 430);
            WxMediaUploadResult uploadResult = service.getMediaService().uploadMedia("image", file);
            service.getMsgService().sendKefuMsg(WxMaKefuMessage.newImageBuilder().mediaId(uploadResult.getMediaId())
                    .toUser(wxMessage.getFromUser()).build());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    };
}