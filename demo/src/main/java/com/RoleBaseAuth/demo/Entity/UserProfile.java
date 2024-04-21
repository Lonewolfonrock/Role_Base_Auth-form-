package com.RoleBaseAuth.demo.Entity;

import com.RoleBaseAuth.demo.Role.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  String name;
    private  String email;
    private  String password;

    @Enumerated(EnumType.STRING)
    private Role role;






}
