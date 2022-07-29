package com.portfolio.backend.Security.Repository;

import com.portfolio.backend.Security.Entity.Role;
import com.portfolio.backend.Security.Enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByRoleName(RoleName RoleName);
}
