package com.jfree.demo;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class Tokenfilter extends ZuulFilter {

    @Value("${zuul.routes.client1.path}")
    public String path;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
//        ctx.setSendZuulResponse(false);
//        ctx.setResponseStatusCode(401);
//        ctx.setResponseBody("ERR");
//        ctx.getResponse().setContentType("text/html;charset=UTF-8");
        return null;
    }
}
