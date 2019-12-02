package com.project.service.outside;

import com.project.constant.BusinessStatus;
import common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WxService {
    // url 常量，不可变
    @Autowired
    private RestTemplate restTemplate;
    // token存储器
    private TokenStorage tokenStorage;

    private int maxRetryTimes = 5; // 最大重试次数

    /**
     * 获取第三方凭证token,之后的请求都需要根据这个token去请求
     *
     * @return
     */
    private String getToken() {
        if (tokenStorage == null) {
            tokenStorage = new TokenStorage();

            String token = this.execute("", String.class, null);
            tokenStorage.setToken(token);
        }
        return tokenStorage.getToken();
    }

    /**
     * 外层封装一层重试机制
     *
     * @param url
     * @param params
     * @param resultClass
     * @param <T>
     * @return
     */
    private <T> T execute(String url, Class<T> resultClass, Object params) {
        // 重试次数
        int retryTimes = 0;
        do {
            try {
                return executeInternal(url, resultClass, params);
            } catch (BusinessException e) {
                if (retryTimes + 1 > this.maxRetryTimes) {
                    throw new BusinessException(BusinessStatus.ERROR_PARAM.status, "重试达到最大次数【\" + this.maxRetryTimes + \"】");
                }
            }
        } while (retryTimes++ < this.maxRetryTimes);

        throw new BusinessException(BusinessStatus.ERROR_PARAM.status, "重试达到最大次数【\" + this.maxRetryTimes + \"】");
    }


    private <T> T executeInternal(String url, Class<T> resultClass, Object params) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        headers.add("X-timevale-project-id", "");
        headers.add("X-timevale-signature", "");

        HttpEntity<Object> entity = new HttpEntity<>(params, headers);

        ResponseEntity<OutSideResult> responseEntity = restTemplate.postForEntity(url, entity, OutSideResult.class);

        if(responseEntity.getStatusCode() != HttpStatus.OK){
            // 会http状态码进行分析
        }

        OutSideResult<T> result = responseEntity.getBody();

        // 对body 里面的状态值进行业务判断
        switch (result.getCode()) {
            case 200:
                return result.getT();
            default:
                throw new BusinessException(BusinessStatus.ERROR_PARAM.status, result.getMsg());
        }
    }

    /**
     * 外部结果封装
     */
    class OutSideResult<T> {
        private int code;
        private String msg;
        private T t;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }
}