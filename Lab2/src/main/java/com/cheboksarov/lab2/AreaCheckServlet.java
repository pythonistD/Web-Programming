package com.cheboksarov.lab2;

import model.Point;
import model.Table;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long enterTime = System.nanoTime();
        resp.setContentType("text/html;charset=UTF-8");
        Table table = (Table) getServletContext().getAttribute("table");
        if (table == null) {
            table = new Table();
            getServletContext().setAttribute("table", table);
        }

        double x = getDoubleValue(req,"x");
        double y = getDoubleValue(req,"y");
        double r = getDoubleValue(req,"r");

        //Здесь должна выполняться валидация
        checkODZ(validateX(x),validateR(r));
        //Проверка на попадание
        boolean isHit = checkHitResult(x,y,r);



        // Работа с датой и временем
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Moscow");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        sdf.setTimeZone(timeZone);

        String curTime = sdf.format(calendar.getTime());
        String timeOfWork = String.valueOf(System.nanoTime() - enterTime);

        //Данные, которые нужно вывести по итогу
        Point point = new Point(x,y,r,curTime,timeOfWork,isHit);
        table.addPoint(point);
        PrintWriter writer = resp.getWriter();
        writer.println(point.getTr());
    }
    private boolean checkHitResult(double x, double y, double r) {
        return checkTriangle(x, y, r) || checkRectangle(x, y, r) || checkCircle(x, y, r);
    }

    private boolean checkTriangle(double x, double y, double r){
        return x >= 0 && y <= 0 && x - y < r;
    }

    private boolean checkRectangle(double x, double y, double r){
        return x <= 0 && x >= -r && y <= 0 && y >= -r;
    }

    private boolean checkCircle(double x, double y, double r){
        return x >= 0 && y >= 0 && x*x + y*y <= r*r;
    }

    public double getDoubleValue(HttpServletRequest request, String parameter) throws NumberFormatException {
        String t = request.getParameter(parameter).replace(",", ".");
        if(t.length() > 6){
            t = t.substring(0,6);
        }
        return Double.parseDouble(t);
    }
    public int getIntValue(HttpServletRequest request, String parameter) throws NumberFormatException {
        return Integer.parseInt(request.getParameter(parameter).replace(",", "."));
    }
    public boolean validateX(double x){
        return x > -5 && x < 5;
    }
    public boolean validateR(double r){
        return r > 1 && r < 4;
    }
    public void checkODZ(boolean isX, boolean isR){
        if(!isR || !isX){
            throw new IllegalArgumentException("Bad arguments");
        }
    }




}
