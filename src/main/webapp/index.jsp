<%--
  Created by IntelliJ IDEA.
  User: yuan wang
  CreateTime: 2023-01-26  16:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>

        <h1>
            当前有 <span style="color: blue"><%=this.getServletConfig().getServletContext().getAttribute("OnlineCount")%></span>人在线
        </h1>

    </body>
</html>
