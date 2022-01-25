package edu.ifmo.web.lab3;

import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class History implements Serializable {
    @Inject
    DBManager DBManager;
    private List<Results> resultsList;

    @PostConstruct
    public void initializeHits() {
        resultsList = DBManager.getHits();
    }

    public void updateAll(){
        formhit.setDefaultValues();
        DBManager.delBase();
        initializeHits();
        addStoredHitsToCanvas();
        formhit.setDefaultValues();
    }

    public List<Results> getHitResultList() {
        return resultsList;
    }

    @Inject
    Hit formhit;
    public void addFromForm() {
        if(formhit.validateValues()){
            double x = formhit.getX();
            double y = formhit.getY();
            double r = formhit.getR();
            addHits(calculateHit(x,y,r));
        }
    }

    private Results calculateHit(double x, double y, double radius) {
        return new Results(x, y, radius, doesItHit(x, y, radius));
    }

    private boolean doesItHit(double x, double y, double radius) {
        if (x >= 0 && y >= 0 && (y-radius)*0.5*radius <= -x*radius) {
            return true;
        }
        if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(radius/2, 2) && x <= 0 && y <= 0) {
            return true;
        }
        return x >=0 && y <= 0 && y >= -radius && x <= radius/2;
    }

    @Inject ChartHit chartHit;
    public void addFromChart() {
        if(1<=chartHit.getR() && chartHit.getR()<=4){
            Results results = calculateHit(chartHit.getX(), chartHit.getY(), chartHit.getR());
            addHits(results);
        }
    }

    private void addHits(Results hits) {
        if (DBManager.addHits(hits)) {
            resultsList.add(hits);
            addHitsToCanvas(Collections.singletonList(hits));
        }
    }

    public void addStoredHitsToCanvas() {
        addHitsToCanvas(resultsList);
    }

    private void addHitsToCanvas(List<Results> hits) {
        String json = hits.stream()
            .map(hit -> "{" +
                " x: " + hit.getX() + "," +
                " y: " + hit.getY() + "," +
                " r: " + hit.getR() + "," +
                " doesHit: " + hit.isDoesHit() + " }"
            )
            .collect(Collectors.joining(", ", "[", "]"));
        PrimeFaces.current().executeScript("addHits(" + json + ")");
    }
}
