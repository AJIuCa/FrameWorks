package ru.odintsov;

import ru.odintsov.persist.User;
import ru.odintsov.persist.UserRepo;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();

        UserRepo userRepo = new UserRepo();
        servletContext.setAttribute("UserRepo", userRepo);
        userRepo.insert(new User("Alice"));
        userRepo.insert(new User("Mark"));
        userRepo.insert(new User("Lex"));
        userRepo.insert(new User("Travolta"));
        userRepo.insert(new User("Ben"));

    }
}
