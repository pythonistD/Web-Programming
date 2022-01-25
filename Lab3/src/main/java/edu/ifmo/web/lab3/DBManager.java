package edu.ifmo.web.lab3;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@ApplicationScoped
public class DBManager implements Serializable {
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("points");
    private final EntityManager entityManager = factory.createEntityManager();

    public boolean addHits(Results results) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(results);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.err.println("Unable to add to DB: " + e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    public List<Results> getHits() {
        try {
            return entityManager
                .createQuery("SELECT h FROM Results h", Results.class)
                .getResultList();
        } catch (Exception e) {
            System.err.println("Unable to get data from DB: " + e.getMessage());
            return Collections.emptyList();
        }
    }
    public void delBase(){
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("DELETE FROM Results");
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }
}
