package com.portfolio.backend.Security.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
@Getter @Setter
public class JwtDTO {
    private String token;
    private String bearer = "Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    //Constructor

    public JwtDTO(String token, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.username = username;
        this.authorities = authorities;
    }
}
