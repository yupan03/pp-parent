package com.project.controller.mp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Api(tags = "微信公众号")
@Controller
@RequestMapping(value = "/wx/mp/portal")
public class WxMpController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WxMpService wxService;
	@Autowired
	private WxMpMessageRouter wxMpMessageRouter;

	@ApiOperation(value = "公众号接入", notes = "公众号接入")
	@GetMapping
	public void authGet(@RequestParam(name = "signature", required = false) String signature,
			@RequestParam(name = "timestamp", required = false) String timestamp,
			@RequestParam(name = "nonce", required = false) String nonce,
			@RequestParam(name = "echostr", required = false) String echostr, HttpServletResponse response)
			throws IOException {

		this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);
		if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
			throw new IllegalArgumentException("请求参数非法，请核实!");
		}

		if (wxService.checkSignature(timestamp, nonce, signature)) {
			response.getOutputStream().println(echostr);
		}
	}

	@ApiOperation(value = "公众号消息", notes = "后台接收消息并处理消息")
	@PostMapping
	public void post(@RequestBody String requestBody, @RequestParam("signature") String signature,
			@RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce,
			@RequestParam("openid") String openid,
			@RequestParam(name = "encrypt_type", required = false) String encType,
			@RequestParam(name = "msg_signature", required = false) String msgSignature, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		this.logger.info(
				"\n接收微信请求：[openid=[{}], [signature=[{}], encType=[{}], msgSignature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				openid, signature, encType, msgSignature, timestamp, nonce, requestBody);

		if (!wxService.checkSignature(timestamp, nonce, signature)) {
			throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
		}

		String message = null;
		if (encType == null) {
			// 明文传输的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
			WxMpXmlOutMessage outMessage = this.route(inMessage);
			if (outMessage != null) {
				message = outMessage.toXml();
			}
		} else if ("aes".equalsIgnoreCase(encType)) {
			// aes加密的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody, wxService.getWxMpConfigStorage(),
					timestamp, nonce, msgSignature);
			this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
			WxMpXmlOutMessage outMessage = this.route(inMessage);
			if (outMessage != null) {
				message = outMessage.toEncryptedXml(wxService.getWxMpConfigStorage());
			}
		}

		this.logger.debug("\n组装回复信息：{}", message);
		PrintWriter out = response.getWriter();
		out.print(message);
		out.close();
	}

	private WxMpXmlOutMessage route(WxMpXmlMessage message) {
		try {
			return wxMpMessageRouter.route(message);
		} catch (Exception e) {
			this.logger.error("路由消息时出现异常！", e);
		}

		return null;
	}
}