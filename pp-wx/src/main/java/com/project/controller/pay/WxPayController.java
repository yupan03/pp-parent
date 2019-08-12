package com.project.controller.pay;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
 * 微信支付接口
 * 
 * @author David
 */
@Api(tags = "微信支付")
@RestController
@RequestMapping(value = "/wx/pay")
public class WxPayController {
    /*
     * @Autowired private WxPayService wxPayService;
     */
}