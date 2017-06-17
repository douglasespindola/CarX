package entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
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
    @Column(name = "title_ads")
    private String titleAds;
    @Column
    private Double value;
    @Column(name = "branch_key")
    private String branchKey;
    @Column
    private Integer year;
    @Column(name = "key_words")
    private String keyWords;
    //TODO: criar campos de created_at e updated_at, lembrar de reproduzir os campos no DTO
    @Transient
    private Integer user_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_id")
    private User user;

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
}
