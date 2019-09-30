package com.louis.mango.admin.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * 权限封装
 * Created by liyue
 * Time 2019-09-20 17:40
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
