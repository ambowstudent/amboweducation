package com.ambowEducation.configuration;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthorizationInterceptor extends UserFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            setHeader(httpRequest,httpResponse);
            return true;
        }
        return super.preHandle(httpRequest, httpResponse);
    }



    private void setHeader(HttpServletRequest request,HttpServletResponse response){
        //跨域的header设置
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", request.getMethod()+",PUT");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        //防止乱码，适用于传输JSON数据
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
    }



}
