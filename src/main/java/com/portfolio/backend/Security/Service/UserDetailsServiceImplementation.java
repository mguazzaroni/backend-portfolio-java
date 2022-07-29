package com.portfolio.backend.Security.Service;

import com.portfolio.backend.Security.Entity.UserPrincipal;
import com.portfolio.backend.Security.Entity.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService{
    @Autowired
    UserSecurityService userSecurityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurity user = userSecurityService.getByUsername(username).get();
        return UserPrincipal.build(user);
    }
    
    
}
