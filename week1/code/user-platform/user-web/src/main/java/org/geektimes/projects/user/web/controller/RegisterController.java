package org.geektimes.projects.user.web.controller;


import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.UserRepository;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/register")
public class RegisterController implements PageController {

    private UserService userService = new UserServiceImpl();

    @POST
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");

        System.out.println("RegisterController");

        if (name == null || password == null || email == null || phoneNumber == null) {
            return "registerFail.jsp";
        }

        User user = new User(null, name, password, email, phoneNumber);
        return userService.register(user) ? "registerOk.jsp" : "registerFail.jsp";
    }
}
