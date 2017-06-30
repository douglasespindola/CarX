package entity;
import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity(name="user")
@SuppressWarnings("serial")
@NamedQueries({
        @NamedQuery(name =
                "User.getLogin", query = "select u from user u where u.email=:email and u.password=:password"),
        @NamedQuery(name = "User.getAllUsers", query = "select u from user u"),
        @NamedQuery(name = "User.getUser", query = "select u from user u where u.id=:id"),
        @NamedQuery(name = "User.checkUserNamedAvailable", query = "select u from user u where u.email=:email or u.cpf=:cpf"),
        @NamedQuery(name = "User.getToken", query = "select u from user u where u.token=:token")

})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        return this.password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = this.convertPasswordToMD5(password);
    }

    public void setPasswordToString(String password) {
        this.password = password;
    }

    public String convertPasswordToMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest).toLowerCase();
    }
}
