package com.RoleBaseAuth.demo.Controller;

import com.RoleBaseAuth.demo.Entity.UserProfile;
import com.RoleBaseAuth.demo.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterUserController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/register")
    ResponseEntity<String>register(@RequestBody UserProfile user){
        if (registrationService.registerUser(user)){
            return  ResponseEntity.ok("User created sucess");
        }
    return ResponseEntity.badRequest().body("User already Exist");
    }



}
