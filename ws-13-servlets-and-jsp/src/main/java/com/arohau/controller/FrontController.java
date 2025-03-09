package com.arohau.controller;

import com.arohau.exception.MyCustomException;
import com.arohau.service.Calculator;
import com.arohau.service.Command;
import com.arohau.service.calculatorImpl.Subtract;
import com.arohau.service.calculatorImpl.Sum;

import com.arohau.service.commandImpl.Logination;
import com.arohau.service.commandImpl.Registration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class FrontController extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(FrontController.class.getName());
    public static final String
            COMMAND = "command",
            OPERATION = "operation",
            LOGINATION = "log",
            REGISTRACTION = "reg",
            SUM = "sum",
            SUBTRACT = "subtract",
            NUMBER_1 = "number-1",
            NUMBER_2 = "number-2",
            LOGIN = "login",
            PASSWORD = "password",
            EMAIL = "email"
                    ;

    public Map<String, Calculator> operations = new HashMap<>();
    public Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() {
        operations.put(SUM, new Sum());
        operations.put(SUBTRACT, new Subtract());

        commands.put(LOGINATION, new Logination());
        commands.put(REGISTRACTION, new Registration());
    }

    private void doExec(HttpServletRequest req, HttpServletResponse resp) {
        readCookies(req, resp);
        try {
            final String operation = req.getParameter(OPERATION);
            if (operation != null && !operation.isEmpty()) {
                Calculator calculator = operations.get(operation);
                calculator.calculate(req, resp);
            } else {
                final String commandName = req.getParameter(COMMAND);
                LOGGER.info("CommandName=" + commandName);
                Command command = commands.get(commandName);
                command.execute(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readCookies(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(String.format("%s{%s=%s,%s}", "cookie", cookie.getName(), cookie.getValue(), cookie.getAttributes()));
//            System.out.println(cookie.toString());
        }
        resp.addCookie(new Cookie("hello", "guys"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doExec(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doExec(req, resp);
    }
}