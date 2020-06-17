package io.instagram.instagram.jwt;

import io.instagram.instagram.user.AppBaseUser;

public class Token {
    private String access;
    private String refresh;


    public Token(String access,
                 String refresh) {
        this.access = access;
        this.refresh = refresh;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }
}
