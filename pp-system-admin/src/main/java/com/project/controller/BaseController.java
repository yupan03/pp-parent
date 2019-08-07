package com.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import common.jwt.JWTUtils;
import common.model.LoginUser;

public class BaseController {
	protected LoginUser getCurrentUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		return JWTUtils.getUserFromToken(request.getHeader(HttpHeaders.AUTHORIZATION));
	}
}