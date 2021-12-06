package model;

import java.io.Serializable;

public class Point implements Serializable {
    private int x;
    private int r;
    private double y;
    private String curTime;
    private String timeOfWork;
    private boolean isHit;

    public Point() {
    }

    public Point(int x, double y, int r, String curTime, String timeOfWork, boolean isHit) {
        this.x = x;
        this.r = r;
        this.y = y;
        this.curTime = curTime;
        this.timeOfWork = timeOfWork;
        this.isHit = isHit;
    }

    public String getTr(){
        return "<tr>" +
                "<td>" +this.x + "</td>" +
                "<td>" +this.y + "</td>" +
                "<td>" +this.r + "</td>" +
                "<td>" +this.isHit + "</td>" +
                "<td>" +this.timeOfWork + "</td>" +
                "<td>" +this.curTime + "</td>" +
                "</tr>";
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getCurTime() {
        return curTime;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
    }

    public String getTimeOfWork() {
        return timeOfWork;
    }

    public void setTimeOfWork(String timeOfWork) {
        this.timeOfWork = timeOfWork;
    }
}