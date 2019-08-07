package com.project.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.api.WxConsts.MenuButtonType;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 ** 菜单处理类
 * 
 * @author yupan
 */
@Component
public class MenuHandler extends AbstractHandler {
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService weixinService,
			WxSessionManager sessionManager) {

		String msg = String.format("type:%s, event:%s, key:%s", wxMessage.getMsgType(), wxMessage.getEvent(),
				wxMessage.getEventKey());
		if (MenuButtonType.VIEW.equals(wxMessage.getEvent())) {
			return null;
		}

		return WxMpXmlOutMessage.TEXT().content(msg).fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
				.build();
	}
}