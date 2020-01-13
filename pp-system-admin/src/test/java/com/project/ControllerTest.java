package com.project;

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
public class ControllerTest extends BaseTest {

    /**
     * 测试一个业务流程
     *
     * @throws Exception 异常
     */
    @Test
    public void test() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/say"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        result.getResponse().getContentAsString();// 获取返回值

    }

    @Test
    public void sayHello() {
        System.out.println("hello world");
    }
}