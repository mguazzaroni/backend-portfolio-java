package com.portfolio.backend.Security.Service;

import com.portfolio.backend.Security.Entity.Role;
import com.portfolio.backend.Security.Enums.RoleName;
import com.portfolio.backend.Security.Repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    @Autowired
    IRoleRepository roleRepository;
    
    public Optional<Role> getByRoleName(RoleName roleName){
        return roleRepository.findByRoleName(roleName);
    }
    
    public void save(Role role){
        roleRepository.save(role);
    }
}
