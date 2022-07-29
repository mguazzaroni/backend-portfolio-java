package com.portfolio.backend.Security.Controller;

import com.portfolio.backend.Security.Dto.JwtDTO;
import com.portfolio.backend.Security.Dto.LoginUserDTO;
import com.portfolio.backend.Security.Dto.NewUserDTO;
import com.portfolio.backend.Security.Entity.Role;
import com.portfolio.backend.Security.Entity.UserSecurity;
import com.portfolio.backend.Security.Enums.RoleName;
import com.portfolio.backend.Security.Jwt.JwtProvider;
import com.portfolio.backend.Security.Service.RoleService;
import com.portfolio.backend.Security.Service.UserSecurityService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserSecurityService userSecurityService;
    @Autowired
    RoleService roleService;
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/new")
    public ResponseEntity<?> create(@Valid @RequestBody NewUserDTO newUserDTO, @NotNull BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Responses("Invalid fields"),HttpStatus.BAD_REQUEST);
        
        if(userSecurityService.existsByUsername(newUserDTO.getUsername()))
            return new ResponseEntity(new Responses("Username already exists"), HttpStatus.BAD_REQUEST);

        UserSecurity user = new UserSecurity(newUserDTO.getName(), newUserDTO.getUsername(),
            newUserDTO.getEmail(), passwordEncoder.encode(newUserDTO.getPassword()));
        
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
        
        if(newUserDTO.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());

        user.setRoles(roles);
        userSecurityService.save(user);
        
        return new ResponseEntity(new Responses("User saved"),HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUserDTO loginUserDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return new ResponseEntity(new Responses("Invalid fields"), HttpStatus.BAD_REQUEST);
        }
        if(!userSecurityService.existsByUsername(loginUserDTO.getUsername())){
            return new ResponseEntity(new Responses("Username does not exist"), HttpStatus.UNAUTHORIZED);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginUserDTO.getUsername(), loginUserDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDTO jwtDto = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
