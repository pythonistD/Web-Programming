package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Table implements Serializable {
    private ArrayList<Point> pointList;

    public Table() {
        this.pointList = new ArrayList<>();
    }

    public void addPoint(Point point){
        this.pointList.add(point);
    }


    public void setPointList(ArrayList<Point> pointList) {
        this.pointList = pointList;
    }

    public ArrayList<Point> getPointList() {
        return pointList;
    }

}
