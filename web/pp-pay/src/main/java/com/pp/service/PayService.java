package com.pp.service;

import com.github.binarywang.wxpay.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 支付服务类
 *
 * @author David
 */
@Service
public class PayService {
    @Autowired
    private WxPayService wxPayService;

}