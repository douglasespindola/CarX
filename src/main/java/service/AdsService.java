package service;

import dto.AdsDto;
import dto.ImageAdsDto;
import dto.UserDto;
import entity.Ads;
import entity.ImageAds;
import entity.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

    @Inject
    private UserService userService;

    /***
     * @desc Sou totalmente contra isso, mas é o que tem pra hoje
     * @return
     */
    @Transactional
    public List<AdsDto> getAll() {
        try {
            Query query = entityManager.createNamedQuery("Ads.getAllAds");
            List<Ads> ads = query.getResultList();

            List adsDto = new ArrayList<AdsDto>();

            for (Ads a : ads) {
                AdsDto adsDtoInsert = new AdsDto();
                adsDtoInsert.setValues((Ads) a);
                adsDto.add(this.adsDtoInsert(a, adsDtoInsert));
            }
            return adsDto;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public AdsDto adsDtoInsert(Ads ads, AdsDto adsDtoInsert) {

        if (ads.getUser() != null) {
            UserDto userDtoInsert = new UserDto();
            userDtoInsert.setEmail(ads.getUser().getEmail());
            userDtoInsert.setName(ads.getUser().getName());
            adsDtoInsert.setUser(userDtoInsert);
        }

        if (ads.getImageAds() != null) {
            List imagesDto = new ArrayList<ImageAdsDto>();

            for (ImageAds ids : ads.getImageAds()) {
                ImageAdsDto imagesDtoInsert = new ImageAdsDto();
                imagesDtoInsert.setValues(ids);
                System.out.println(imagesDto);
                imagesDto.add(imagesDtoInsert);
            }
            adsDtoInsert.setImageAds(imagesDto);
        }
        return adsDtoInsert;
    }

    public AdsDto get(Integer id) {
        try {
            Query query = entityManager.createNamedQuery("Ads.getAds");
            query.setParameter("id", id);
            Ads ads = (Ads) query.getSingleResult();
            AdsDto adsDtoInsert = new AdsDto();
            adsDtoInsert.setValues(ads);
            return this.adsDtoInsert(ads, adsDtoInsert);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public AdsDto update(Ads ads) {
        User user = userService.getUser(ads.getUserId());
        ads.setUser(user);
        entityManager.merge(ads);
        AdsDto adsDto = new AdsDto();
        adsDto.setValues(ads);
        return adsDto;
    }

    @Transactional
    public AdsDto create(Ads ads) {
        User user = userService.getUser(ads.getUserId());
        ads.setUser(user);
        entityManager.persist(ads);
        AdsDto adsDto = new AdsDto();
        adsDto.setValues(ads);
        return adsDto;
    }

    @Transactional
    public void remove(Integer id) {
        Ads ads = entityManager.find(Ads.class, id);
        entityManager.remove(ads);
    }
}
