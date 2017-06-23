package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity(name="user")
@SuppressWarnings("serial")
@NamedQueries({
        @NamedQuery(name =
                "User.getLogin", query = "select u from user u where u.email=:email and u.password=:password"),
        @NamedQuery(name = "User.getAllUsers", query = "select u from user u"),
        @NamedQuery(name = "User.getUser", query = "select u from user u where u.id=:id"),
        @NamedQuery(name = "User.checkUserNamedAvailable", query = "select u from user u where u.email=:email"),
        @NamedQuery(name = "User.getToken", query = "select u from user u where u.token=:token")
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private Long cpf;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String token;
    //@XmlTransient
//    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, mappedBy = "user")
//    private List<Ads> ads = null;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = Long.parseLong(cpf);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //@XmlTransient
//    public List<Ads> getAds() {
//        return ads;
//    }
//
//    public void setAds(List<Ads> ads) {
//        this.ads = ads;
//    }
}
