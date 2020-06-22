package com.project.controller;

import com.pp.result.Result;
import com.project.BaseControllerTest;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.JVM) // 按方法顺序执行
public class ControllerTest extends BaseControllerTest {

    /**
     * 测试一个业务流程
     *
     * @throws Exception 异常
     */
    @Test
    public void test() throws Exception {
        String token = super.getToken("yupan", "123456");

        Assert.assertNotNull(token);

        MvcResult result = mockMvc.perform(get("/user/say").header("Authorization", "Beare" + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        Result result1 = getResult(result.getResponse().getContentAsString());
        System.out.println(result1.getStatus() + ":" + result1.getMsg());
    }
}