package entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name="user")
@SuppressWarnings("serial")
@NamedQueries({
        @NamedQuery(name = "User.getLogin", query = "select u from user u where u.name=:nome and u.password=:senha"),
        @NamedQuery(name = "User.getAllUsers", query = "select u from user u"),
        @NamedQuery(name = "User.getUser", query = "select u from user u where u.id=:id")
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
}
