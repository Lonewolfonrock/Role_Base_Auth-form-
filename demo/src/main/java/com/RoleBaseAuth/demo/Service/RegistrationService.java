package com.RoleBaseAuth.demo.Service;

import com.RoleBaseAuth.demo.Entity.UserProfile;
import com.RoleBaseAuth.demo.Repository.RegisterNewUserRepository;
import com.RoleBaseAuth.demo.Role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    RegisterNewUserRepository registerNewUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean registerUser(UserProfile user){
        Optional<UserProfile>userContainer = registerNewUserRepository.findByEmail(user.getEmail());
        if (userContainer.isEmpty()){
            user.setRole(Role.ROLE_USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            registerNewUserRepository.save(user);
            return  true;
        }
        return  false;
    }
    // admin registration not done yet

    public boolean registerAdmin(UserProfile admin) {
        Optional<UserProfile> userContainer = registerNewUserRepository.findByEmail(admin.getEmail());
        if (userContainer.isEmpty()) {
            admin.setRole(Role.ROLE_ADMIN);
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            registerNewUserRepository.save(admin);
            return  true;
        }
        return  false;
    }




}
