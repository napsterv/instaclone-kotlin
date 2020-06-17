package io.instagram.instagram.jwt;

import io.instagram.instagram.user.AppBaseUser;

public class TokenResponse {
    private Token token;
    private AppBaseUser user;

    public TokenResponse(Token token,
                         AppBaseUser user) {
        this.token = token;
        this.user = user;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public AppBaseUser getUser() {
        return user;
    }

    public void setUser(AppBaseUser user) {
        this.user = user;
    }
}
