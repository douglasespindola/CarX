package service;

import dto.AdsDTO;
import dto.UserDto;
import entity.Ads;
import entity.User;

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
    @Transactional
    public List<AdsDTO> getAllAds() {
        try {
            Query query = entityManager.createNamedQuery("Ads.getAllAds");
            List<Ads> ads = query.getResultList();

            List adsDto = new ArrayList<AdsDTO>();

            for (Ads a : ads) {
                AdsDTO adsDtoInsert = new AdsDTO();
                adsDtoInsert.setValues((Ads) a);
                if (a.getUser() != null) {
                    System.out.println(a.getUser().getId());
                    UserDto userDtoInsert = new UserDto();
                    userDtoInsert.setEmail(a.getUser().getEmail());
                    userDtoInsert.setName(a.getUser().getName());
                    adsDtoInsert.setUser(userDtoInsert);
                }
                adsDto.add(adsDtoInsert);
            }
            return adsDto;
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
