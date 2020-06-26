package cl.dyi.feriados.Response;

import cl.dyi.feriados.Model.User;

public class LoginResponse {

    private Boolean success;
    private User user;
    private String jwt;

    public Boolean getSuccess() {
        return success;
    }

    public User getUser() {
        return user;
    }

    public String getJwt() {
        return jwt;
    }

}
