package entity;

import dto.AdsDto;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(name = "ads")
@NamedQueries({
        @NamedQuery(name = "Ads.getAllAds", query = "select a from ads a"),
        @NamedQuery(name = "Ads.getAds", query = "select a from ads a where a.id=:id")
})

public class Ads implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "description")
    private String description;
    @Column(name = "model_name")
    private String modelName;
    @Column(name = "model_key")
    private String modelKey;
    @Column
    private Double value;
    @Column(name = "branch_key")
    private String branchKey;
    @Column(name = "branch_name")
    private String branchName;
    @Column
    private Integer year;
    @Column(name = "create_at", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "update_at", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    //TODO: criar campos de created_at e updated_at, lembrar de reproduzir os campos no DTO
    @Transient
    private Integer user_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE}, mappedBy = "ads")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<ImageAds> imageAds;

    @Transient
    public Integer getUserId() {
        return user_id;
    }

    @Transient
    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }

    public void setValues(AdsDto ads) {
        this.id = ads.getId();
        this.description = ads.getDescription();
        this.modelName = ads.getModelName();
        this.modelKey = ads.getModelKey();
        this.value = ads.getValue();
        this.branchKey = ads.getBranchKey();
        this.branchName = ads.getBranchName();
        this.year = ads.getYear();
        this.user_id = ads.getUserId();
    }

    public List<ImageAds> getImageAds() {
        return imageAds;
    }

    public void setImageAds(List<ImageAds> imageAds) {
        this.imageAds = imageAds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
