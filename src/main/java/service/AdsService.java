package service;

import entity.Ads;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class AdsService {

    @PersistenceContext(name = "pi2017")
    private EntityManager entityManager;

    public List<Ads> getAllAds() {
        try {
            Query query = entityManager.createNamedQuery("Ads.getAllAds");
            return (List<Ads>) query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Ads getAds() {
        try {
            Query query = entityManager.createNamedQuery("Ads.getAds");
            return (Ads) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public Ads update(Ads ads) {
        return entityManager.merge(ads);
    }

    @Transactional
    public Ads create(Ads ads) {
        entityManager.persist(ads);
        return ads;
    }

    @Transactional
    public void remove(Integer id) {
        Ads ads = entityManager.find(Ads.class, id);
        entityManager.remove(ads);

    }
}
