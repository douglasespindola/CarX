package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ads")
@NamedQueries({
        @NamedQuery(name = "Ads.getAllAds", query = "select a from ads a"),
        @NamedQuery(name = "Ads.getAds", query = "select a from ads a where a.id=:id")
})


public class Ads implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="title_ads")
    private String titleAds;
    @Column
    private Double value;
    @Column(name="branch_key")
    private String branchKey;
    @Column
    private Integer year;
    @Column(name="key_words")
    private String keyWords;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

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
