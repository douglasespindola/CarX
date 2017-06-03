package entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name="usuario")
@SuppressWarnings("serial")
@NamedQueries({
        @NamedQuery(name = "Usuario.findByNomeSenha", query = "select u from usuario u where u.nome=:nome and u.senha=:senha"),
        @NamedQuery(name = "Usuario.getAllUsuarios", query = "select u from usuario u"),
        @NamedQuery(name = "Usuario.getUsuario", query = "select u from usuario u where u.id=:id")
})
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String nome;
    @Column
    private Long cpf;
    @Column
    private String senha;
    @Column
    private String email;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
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
}
