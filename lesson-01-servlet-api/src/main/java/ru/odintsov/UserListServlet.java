package ru.odintsov;

import ru.odintsov.persist.User;
import ru.odintsov.persist.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet("/users/*")
public class UserListServlet extends HttpServlet {


    private UserRepo userRepo;
    private String prefix;

    @Override
    public void init() throws ServletException {
        this.userRepo = (UserRepo) getServletContext().getAttribute("UserRepo");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        prefix = req.getPathInfo();

        if (prefix == null) {

            for (Map.Entry<Long, User> entry : userRepo.getUserMap().entrySet()) {
                Long key = entry.getKey();
                User value = entry.getValue();
                resp.getWriter().println("<p>Id: " + key + " Name: " + value.getUsername() + "</p>");
            }
        } else {
            for (int i = 0; i < userRepo.size()-1; i++) {
                if (req.getPathInfo().equals("/"+(i+1))) {
                    resp.getWriter().println("<p>Id: "+(i+1)+" Username: "+userRepo.findById(i+1).getUsername()+"</p>");
                }
            }
        }
    }
}
