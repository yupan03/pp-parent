package com.pp.config.handler;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

public class CusAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        // TODO 自动生成的方法存根

    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        // TODO 自动生成的方法存根
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO 自动生成的方法存根
        return false;
    }
}