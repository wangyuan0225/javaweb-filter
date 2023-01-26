package com.kuang.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @BelongsProject: javaweb-filter
 * @BelongsPackage: com.kuang.listener
 * @Author: yuan wang
 * @CreateTime: 2023-01-26  15:55
 * @Description: 统计网站在线人数：统计session
 * @Version: 1.0
 */
@WebListener
public class OnlineCountListener implements HttpSessionListener {
    /**
     * @description: 创建session监听
     * 一旦创建Session就会触发一次这个事件
     * @author: yuan wang
     * @date: 2023/1/26 15:58
     * @param: se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();

        System.out.println(se.getSession().getId());

        Object onlineCount = servletContext.getAttribute("OnlineCount");

        if (onlineCount == null) {
            onlineCount = 1;
        } else {
            int count = (int) onlineCount;
            onlineCount = count + 1;
        }

        servletContext.setAttribute("OnlineCount", onlineCount);
    }

    /**
     * @description: 销毁session监听
     * 一旦销毁Session就会触发一次这个事件
     * @author: yuan wang
     * @date: 2023/1/26 15:58
     * @param: se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();

        Object onlineCount = servletContext.getAttribute("OnlineCount");

        System.out.println(se.getSession().getId());
        if (onlineCount == null) {
            onlineCount = 0;
        } else {
            int count = (int) onlineCount;
            onlineCount = count - 1;
        }

        servletContext.setAttribute("OnlineCount", onlineCount);
    }
}

