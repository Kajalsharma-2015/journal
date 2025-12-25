package net.Karo.journalApp.controller;


import net.Karo.journalApp.Etity.User;
import net.Karo.journalApp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        System.out.println(">>> Inside Controller <<<");
        userServices.saveEntry(user);
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }
}



