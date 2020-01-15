package com.project;

import com.project.utils.JSONUtil;
import common.result.Result;
import common.result.ResultList;
import common.result.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class BaseTest {
    @Autowired
    protected MockMvc mockMvc;

    /**
     * 获取token
     */
    protected String getHeard() {
        MvcResult result = null;
        try {
            result = mockMvc.perform(get("/user/say"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print()).andReturn();
            result.getResponse().getContentAsString();// 获取返回值
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Result getResult(String json) {
        return JSONUtil.jsonToObject(json, Result.class);
    }

    protected <T> ResultObj<T> getResultObj(String json, Class<T> tClass) {
        return JSONUtil.jsonToObject(json, ResultObj.class);
    }

    protected <T> ResultList<T> getResultList(String json) {
        return JSONUtil.jsonToObject(json, ResultList.class);
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