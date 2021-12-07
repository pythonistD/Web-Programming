package com.cheboksarov.lab2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getRequestDispatcher("/areaCheck").forward(req, resp);
        }catch (Exception e){
            resp.getWriter().println(e);
        }


    }
    public double getDoubleValue(HttpServletRequest request, String parameter) throws NumberFormatException {
        return Double.parseDouble(request.getParameter(parameter).replace(",", "."));
    }
    public int getIntValue(HttpServletRequest request, String parameter) throws NumberFormatException {
        return Integer.parseInt(request.getParameter(parameter).replace(",", "."));
    }
}
