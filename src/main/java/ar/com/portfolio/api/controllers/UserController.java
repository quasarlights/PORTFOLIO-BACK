package ar.com.portfolio.api.controllers;

import ar.com.portfolio.api.models.User;
import ar.com.portfolio.api.repositories.UserRepository;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.portfolio.api.services.PersonService;
import ar.com.portfolio.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
@RestController

public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonService personService;

    @PostMapping("login")
    public boolean login(@RequestBody User user) {
        List<User> usuarios = userRepository.findByUserAndPass(user.getUser(), user.getPass());
        if (!usuarios.isEmpty()) {
            return true;
        }
        return false;
    }
    
 }
