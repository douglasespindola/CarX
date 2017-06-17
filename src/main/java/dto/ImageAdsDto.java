package dto;

import entity.Ads;
import entity.ImageAds;

public class ImageAdsDto {

    private Integer id;

    private String name;

    private Ads ads;

    private Integer ads_id;

    public void setValues(ImageAds imageAds) {
        this.name = imageAds.getName();
        this.id = imageAds.getId();
        this.ads_id = imageAds.getAds().getId();
    }

    public Integer getAdsId() {
        return ads_id;
    }

    public void setAdsId(Integer ads_id) {
        this.ads_id = ads_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ads getAds() {
        return ads;
    }

    public void setAds(Ads ads) {
        this.ads = ads;
    }
}
