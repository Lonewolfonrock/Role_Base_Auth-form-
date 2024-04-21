package com.RoleBaseAuth.demo.Controller;

import com.RoleBaseAuth.demo.Entity.UserProfile;
import com.RoleBaseAuth.demo.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterAdminController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/register-admin")
    ResponseEntity<String> register(@RequestBody UserProfile admin){
        if (registrationService.registerAdmin(admin)){
            return  ResponseEntity.ok("Admin created sucessfully");
        }
            return  ResponseEntity.badRequest().body("Admin already exist");
    }

}
