package ru.odintsov;

import ru.odintsov.persist.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/1/*")
public class FirstHttpServlet extends HttpServlet {

    private UserRepo userRepo;

    @Override
    public void init() throws ServletException {
        this.userRepo = (UserRepo) getServletContext().getAttribute("UserRepo");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Privet Медвед от http Servlet</h1>");
        resp.getWriter().println("<p>contextPath: " + req.getContextPath() + "</p>");
        resp.getWriter().println("<p>servletPath: " + req.getServletPath() + "</p>");
        resp.getWriter().println("<p>pathInfo " + req.getPathInfo() + "</p>");
        resp.getWriter().println("<p>queryString: " + req.getQueryString() + "</p>");
        resp.getWriter().println("<p>param1: " + req.getParameter("param1") + "</p>");
        resp.getWriter().println("<p>param2: " + req.getParameter("param2") + "</p>");
        resp.getWriter().println("<p>param3: " + req.getParameter("param3") + "</p>");
        resp.getWriter().println("<p>mm: " + req.getParameter("mm") + "</p>");
    }
}
