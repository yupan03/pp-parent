package com.project;

import com.pp.result.Result;
import com.pp.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

/**
 * Controller测试基础类，封装基础方法
 */
@SuppressWarnings("unchecked")
public class BaseControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    /**
     * 获取登录的凭证
     *
     * @param account  账号
     * @param password 密码
     * @return 返回token
     */
    protected String getToken(String account, String password) {
        try {
            MvcResult result = mockMvc.perform(get("/user/say")
                    .param("account", account)
                    .param("password", password))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print()).andReturn();

            Result<String> resultObj = this.getResultObj(result.getResponse().getContentAsString());
            if (resultObj.getStatus() == HttpStatus.OK.value()) {
                return resultObj.getData();
            } else {
                System.out.println("获取token失败:" + resultObj.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    protected Result getResult(String json) {
        return JSONUtil.jsonToObject(json, Result.class);
    }

    protected <T> Result<T> getResultObj(String json) {
        return JSONUtil.jsonToObject(json, Result.class);
    }
    
	protected <T> Result<List<T>> getResultList(String json) {
        return JSONUtil.jsonToObject(json, Result.class);
    }

    protected MockHttpServletRequestBuilder get(String uri) {
        return MockMvcRequestBuilders.get(uri);
    }

    protected MockHttpServletRequestBuilder post(String uri) {
        return MockMvcRequestBuilders.post(uri);
    }

    protected MockHttpServletRequestBuilder delete(String uri) {
        return MockMvcRequestBuilders.delete(uri);
    }

    protected MockHttpServletRequestBuilder put(String uri) {
        return MockMvcRequestBuilders.put(uri);
    }
}