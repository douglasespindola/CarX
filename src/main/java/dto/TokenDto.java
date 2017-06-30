package dto;

/**
 * Created by felipemoura on 07/06/2017.
 */
public class TokenDto {
    
    private String token;

    private Integer user_id;

    private String name;

    private String email;

    private Long cpf;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = Long.parseLong(cpf);
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
