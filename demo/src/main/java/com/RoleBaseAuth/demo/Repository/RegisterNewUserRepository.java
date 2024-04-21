package com.RoleBaseAuth.demo.Repository;

import com.RoleBaseAuth.demo.Entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterNewUserRepository extends JpaRepository<UserProfile,Long> {
    Optional<UserProfile>findByEmail(String email);

}
