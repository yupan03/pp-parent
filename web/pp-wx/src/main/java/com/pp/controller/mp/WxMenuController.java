package com.pp.controller.mp;

import io.swagger.annotations.Api;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信公众号菜单
 *
 * @author David
 */
@Api(tags = "微信公众号菜单")
@RestController
@RequestMapping(value = "/wx/mp/menu")
public class WxMenuController {
    @Autowired
    private WxMpService wxMpService;

    @PostMapping(value = "/create")
    public String menuCreate(@RequestBody WxMenu menu) {

        String msg = "";
        try {
            msg = wxMpService.getMenuService().menuCreate(menu);
        } catch (WxErrorException e) {
            e.printStackTrace();
            msg = "创建菜单失败，请重试！";
        }

        return msg;
    }
}