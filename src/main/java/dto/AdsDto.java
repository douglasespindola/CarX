package dto;

import entity.Ads;
import entity.ImageAds;
import entity.User;
import service.UserService;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class AdsDto {

    private Integer id;

    private String description;

    private String modelName;
    
    private String modelKey;

    private Double value;

    private String branchKey;

    private String branchName;

    private Integer year;

    private UserDto user;

    private Date createAt;

    private Date updateAt;

    private List<ImageAdsDto> imageAds;

    private Integer user_id;

    private boolean flActive;

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public boolean isFlActive() {
        return flActive;
    }

    public void setFlActive(boolean flActive) {
        this.flActive = flActive;
    }

    public void setValues(Ads ads) {
        this.id = ads.getId();
        this.description = ads.getDescription();
        this.modelName = ads.getModelName();
        this.modelKey = ads.getModelKey();
        this.value = ads.getValue();
        this.branchKey = ads.getBranchKey();
        this.branchName = ads.getBranchName();
        this.year = ads.getYear();
        this.user_id = ads.getUserId();
        this.flActive = ads.isFlActive();
        this.updateAt = ads.getUpdateAt();
        this.createAt = ads.getCreateAt();
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<ImageAdsDto> getImageAds() {
        return imageAds;
    }

    public void setImageAds(List<ImageAdsDto> imageAds) {
        this.imageAds = imageAds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelKey() {
        return modelKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }
}
