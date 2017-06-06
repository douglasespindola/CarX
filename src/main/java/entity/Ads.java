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
    @Column
    private String titleAds;
    @Column
    private Double value;
    @Column
    private String marcaKey;
    @Column
    private Integer year;
    @Column
    private String brancWords;

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBrancWords() {
        return brancWords;
    }

    public void setBrancWords(String brancWords) {
        this.brancWords = brancWords;
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

    public String getMarcaKey() {
        return marcaKey;
    }

    public void setMarcaKey(String marcaKey) {
        this.marcaKey = marcaKey;
    }
}
