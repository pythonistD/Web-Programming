package com.cheboksarov.lab2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "ControllerServlet", value = "/main")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double y = getDoubleValue(req,"y");
        int x = getIntValue(req,"x");
        int r = getIntValue(req,"r");
    }
    public double getDoubleValue(HttpServletRequest request, String parameter) {
        return Double.parseDouble(request.getParameter(parameter).replace(",", "."));
    }
    public int getIntValue(HttpServletRequest request, String parameter) {
        return Integer.parseInt(request.getParameter(parameter).replace(",", "."));
    }
}
