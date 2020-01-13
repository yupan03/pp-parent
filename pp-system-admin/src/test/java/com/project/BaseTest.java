package com.project;

import common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class BaseTest {
    @Autowired
    protected MockMvc mockMvc;

    /**
     * 获取token
     */
    protected String getHeard() {
        return null;
    }

    protected Result getResult(MvcResult result) {
        return null;
    }

    protected MockHttpServletRequestBuilder get(String uri) {
        return MockMvcRequestBuilders.get(uri);
    }

    protected MockHttpServletRequestBuilder post(String uri) {
        return MockMvcRequestBuilders.post(uri);
    }
}