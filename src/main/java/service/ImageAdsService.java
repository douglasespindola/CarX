package service;

import dto.AdsDto;
import dto.ImageAdsDto;
import entity.Ads;
import entity.ImageAds;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by felipemoura on 18/06/2017.
 */
@Named
@RequestScoped
public class ImageAdsService {

    @PersistenceContext(name = "pi2017")
    private EntityManager entityManager;
    @Inject
    private AdsService adsService;

    @Transactional
    public ImageAdsDto create(ImageAds imageAds) {
        AdsDto adsDto = adsService.get(imageAds.getAdsId());
        Ads ads = new Ads();
        ads.setValues(adsDto);
        imageAds.setAds(ads);
        System.out.println(imageAds);
        entityManager.persist(imageAds);
        ImageAdsDto imageAdsDto = new ImageAdsDto();
        imageAdsDto.setValues(imageAds);
        return imageAdsDto;
    }

    @Transactional
    public ImageAdsDto update(ImageAds imageAds) {
        AdsDto adsDto = adsService.get(imageAds.getAdsId());
        Ads ads = new Ads();
        ads.setValues(adsDto);
        imageAds.setAds(ads);
        entityManager.merge(imageAds);
        ImageAdsDto imageAdsDto = new ImageAdsDto();
        imageAdsDto.setValues(imageAds);
        return imageAdsDto;
    }

    @Transactional
    public void remove(Integer id) {
        ImageAds imageAds = this.get(id);
        entityManager.remove(imageAds);
    }

    @Transactional
    public ImageAds get(Integer id){
        ImageAds imageAds = entityManager.find(ImageAds.class, id);
        return imageAds;
    }
}
