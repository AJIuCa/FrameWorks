package ru.odintsov;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


//@WebServlet("/first-servlet-app")
@WebServlet("/1")
public class FirstServlet implements Servlet {

    private ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        this.config = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {

        return this.config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        servletResponse.getWriter().println("<h1>Privet Медвед</h1>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
