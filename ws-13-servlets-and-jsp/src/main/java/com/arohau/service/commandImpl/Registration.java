package com.arohau.service.commandImpl;

import com.arohau.domain.User;
import com.arohau.repository.Repository;
import com.arohau.repository.impl.UserRepositoryImpl;
import com.arohau.service.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.arohau.controller.FrontController.*;
import static com.arohau.repository.impl.UserRepositoryImpl.INSTANCE;

public class Registration implements Command {

    private final Repository<Long, User> userRepository = INSTANCE;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        String email = req.getParameter(EMAIL);
        User user = new User(userRepository.getNextId(), login, password, email);

        Long savedUserId = userRepository.save(user);

        req.setAttribute("id", savedUserId);
        req.setAttribute("savedUser", user);
        req.setAttribute("msg", "User is saved [" + user + "]");
//        req.setAttribute("result", "User is saved [" + user + "]");
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
