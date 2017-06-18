package entity;

import dto.ImageAdsDto;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity(name="image_ads")
@NamedQueries({
        @NamedQuery(name = "ImageAds.getImages", query = "select ia from image_ads ia where ia.ads =:ads_id"),
})
public class ImageAds {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "ads_id")
    private Ads ads;

    @Transient
    private Integer ads_id;


    @Transient
    public Integer getAdsId() {
        return ads_id;
    }

    @Transient
    public void setAdsId(Integer ads_id) {
        this.ads_id = ads_id;
    }

    public void setValues(ImageAdsDto imageAdsDto) {
        this.name = imageAdsDto.getName();
        this.id = imageAdsDto.getId();
        this.ads_id = imageAdsDto.getAds().getId();
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
