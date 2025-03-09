package com.arohau.old.service.commandImpl;

import com.arohau.old.service.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Logination implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        // process logination
        // forward to main page OR redirect to authorization error servlet
    }
}
