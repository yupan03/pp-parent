package com.project.entity.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author David
 * @since 2019-08-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String account;

    private String name;

    private String password;

    private String email;

    private Boolean enable;

    private Integer type;

    private String createTime;
}