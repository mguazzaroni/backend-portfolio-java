package com.portfolio.backend.Security.Repository;

import com.portfolio.backend.Security.Entity.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IUserSecurityRepository extends JpaRepository<UserSecurity, Integer> {

    Optional<UserSecurity> findByUsername(String username);
    
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
