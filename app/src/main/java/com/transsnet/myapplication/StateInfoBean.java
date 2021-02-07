package com.transsnet.myapplication;

import java.io.Serializable;

/**
 * Author:  zengfeng
 * Time  :  2021/2/7 13:54
 * Des   :
 */
public class StateInfoBean implements Serializable {
    private boolean isLogin;
    private String token;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
