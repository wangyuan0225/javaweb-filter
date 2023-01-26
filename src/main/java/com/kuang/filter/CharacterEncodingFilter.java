package com.kuang.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @BelongsProject: javaweb-filter
 * @BelongsPackage: com.kuang.filter
 * @Author: yuan wang
 * @CreateTime: 2023-01-26  14:20
 * @Description: TODO
 * @Version: 1.0
 */
@WebFilter(filterName = "CharacterEncodingFilter", value = "/show")
public class CharacterEncodingFilter implements Filter {

    //初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter初始化");
    }

    /**
     * @description: chain：链
     * 1. 过滤器中的所有代码，在过滤待定请求的时候都会执行
     * 2. 必须要让过滤
     * @author: yuan wang
     * @date: 2023/1/26 15:07
     * @param: request
     * @param: response
     * @param: chain
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;utf-8");

        System.out.println("执行前");
        //让请求继续走，如果不写，程序到这里就被拦截停止了（解决多个过滤器的问题，使请求继续往下交接）
        chain.doFilter(request, response);
        System.out.println("执行后");
    }

    //销毁(web服务器关闭的时候过滤器才销毁)
    @Override
    public void destroy() {
        System.out.println("CharacterEncodingFilter销毁");
    }
}
