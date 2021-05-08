package com.khalid.fakebook.Controller;

import com.khalid.fakebook.Encryption.EncryptPasswordGenerator;
import com.khalid.fakebook.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.khalid.fakebook.model.User;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class AuthController {
    @Autowired
    public UserService userService;


    @PostMapping("/api/register")
    public Object register(@RequestBody User user) {

        if(user.getFirstname().isEmpty() && user.getLastname().isEmpty())
            return new ResponseEntity<>("First and last name should not be empty.", HttpStatus.BAD_REQUEST);

        //Checking for email patterns
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!user.getEmail().matches(regex))
            return new ResponseEntity<>("Email not valid. Make sure you are using correct email.", HttpStatus.BAD_REQUEST);

        // Check if email already exists.
        User emailExists = userService.findUserByEmail(user);
        System.out.println("email " + emailExists);

        if (user.getEmail().equals(emailExists.getEmail()))
            return new ResponseEntity<>("Email already in use", HttpStatus.BAD_REQUEST);

        //Password length
        if (user.getPassword().length() < 8)
            return new ResponseEntity<>("Password too short. Should be at least 8 character.", HttpStatus.BAD_REQUEST);

        // Hashing the password
        String salt = EncryptPasswordGenerator.getSalt(30);
        String password = EncryptPasswordGenerator.generateSecurePassword(user.getPassword(), salt);

        user.setPassword(password);
        user.setFirstname(user.getFirstname());
        user.setLastname(user.getLastname());
        user.setEmail(user.getEmail());
        user.setAvatar(user.getAvatar());
        //Save user
//        userService.addUser(user);

        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        var newUser = new User();
//        if (!user.getEmail().equals(newUser.getEmail())) {
//
//        }
        System.out.println(newUser.getEmail());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

//class Massage {
//    public static String massage (String s) {
//        return s;
//    }
//}