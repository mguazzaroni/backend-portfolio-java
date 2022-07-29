package com.portfolio.backend.Security.Service;

import com.portfolio.backend.Security.Entity.UserSecurity;
import com.portfolio.backend.Security.Repository.IUserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserSecurityService {
    @Autowired
    IUserSecurityRepository userSecurityRepository;
    
    public Optional<UserSecurity> getByUsername(String username){
        return userSecurityRepository.findByUsername(username);
    }
    
    public boolean existsByUsername(String username){
        return userSecurityRepository.existsByUsername(username);
    }
    
    public boolean existsByEmail(String email){
        return userSecurityRepository.existsByEmail(email);
    }
    
    public void save(UserSecurity userSecurity){
        userSecurityRepository.save(userSecurity);
    }
}
