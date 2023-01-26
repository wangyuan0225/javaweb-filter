package com.kuang.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: javaweb-filter
 * @BelongsPackage: com.kuang.servlet
 * @Author: yuan wang
 * @CreateTime: 2023-01-26  14:25
 * @Description: TODO
 * @Version: 1.0
 */
@WebServlet(name = "ShowServlet", value = {"/servlet/show", "/show"})
public class ShouServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("你好世界");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
