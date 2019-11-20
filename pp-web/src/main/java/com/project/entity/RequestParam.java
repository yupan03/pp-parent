package com.project.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 ** 请求参数统一封装
 * 
 * @author yupan
 */
@Data
public class RequestParam<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    // 分页大小
    private int pageSize = 15;
    // 分页当前页码
    private int pageNum = 1;
    // 对象传参
    private T obj;
    // 对象数组
    private List<T> objs;

    /**
     ** 验证参数合法性
     * 
     * @return
     */
    public List<String> validateObj() {
        List<String> messageList = new ArrayList<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);

        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }

        return messageList;
    }
}