package com.project;

import com.project.utils.JSONUtil;
import common.result.ResultObj;
import org.junit.Test;

public class JSONTest {
    @Test
    public void testJSon() throws Exception {
        String json = "{\"status\":200,\"msg\":\"成功\",\"data\":\"hello world\"}";
        ResultObj<String> resultObj = JSONUtil.jsonToObject(json, ResultObj.class);

        System.out.println(resultObj.getData());
    }
}
