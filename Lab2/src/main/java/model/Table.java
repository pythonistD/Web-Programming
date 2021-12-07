package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Table implements Serializable {
    private static ArrayList<Point> pointList;

    public Table() {
        pointList = new ArrayList<>();
    }

    public void addPoint(Point point){
        pointList.add(point);
    }


    public void setPointList(ArrayList<Point> pointList) {
        Table.pointList = pointList;
    }

    public ArrayList<Point> getPointList() {
        return pointList;
    }

}
