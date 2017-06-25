package dto;

/**
 * Created by felipemoura on 07/06/2017.
 */
public class TokenDto {
    
    private String token;

    private Integer user_id;

    private String name;

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
