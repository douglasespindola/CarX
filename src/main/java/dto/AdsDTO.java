package dto;

import entity.Ads;
import entity.User;

/**
 * Created by felipemoura on 17/06/2017.
 */
public class AdsDTO {

    private Integer id;

    private String titleAds;

    private Double value;

    private String branchKey;

    private Integer year;

    private String keyWords;

    private UserDto user;


    public void setValues(Ads ads) {
        this.id = ads.getId();
        this.titleAds = ads.getTitleAds();
        this.value = ads.getValue();
        this.branchKey = ads.getBranchKey();
        this.year = ads.getYear();
        //this.user = ads.getUser();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitleAds() {
        return titleAds;
    }

    public void setTitleAds(String titleAds) {
        this.titleAds = titleAds;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getBranchKey() {
        return branchKey;
    }

    public void setBranchKey(String branchKey) {
        this.branchKey = branchKey;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
