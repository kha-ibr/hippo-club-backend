package com.khalid.fakebook.Controller;

import com.khalid.fakebook.Repo.UserRepo;
import com.khalid.fakebook.Service.AuthServise;
import com.khalid.fakebook.dto.RegisterReq;
import com.khalid.fakebook.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {

    public final AuthServise authServise;
    public final UserRepo userRepo;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterReq req) {

        if(req.getFirstname().isEmpty() && req.getLastname().isEmpty())
            return new ResponseEntity<>("First and last name should not be empty.", HttpStatus.BAD_REQUEST);

        //Checking for email patterns
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!req.getEmail().matches(regex))
            return new ResponseEntity<>("Email not valid. Make sure you are using correct email.", HttpStatus.BAD_REQUEST);

        // Check if email already exists.
        User emailExists = authServise.findByEmail(req.getEmail());

        if (emailExists != null)
            return new ResponseEntity<>("Oops!  There is already a req registered with the email provided.", HttpStatus.BAD_REQUEST);

        //Password length
        if (req.getPassword().length() < 8)
            return new ResponseEntity<>("Password too short. Should be at least 8 character.", HttpStatus.BAD_REQUEST);

        authServise.register(req);

        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginReq req) {
        User newUser = new User();
        User user = authServise.findByEmail(req.getEmail());

        boolean passwordVerified = EncryptPasswordGenerator.verifyUserPassword(req.getPassword(), user.getPassword(), user.getSalt());

        if (user.getEmail() == null && !passwordVerified)
            return new ResponseEntity<>("Incorrect email or password", HttpStatus.BAD_REQUEST);

//        if ()
//            return new ResponseEntity<>("Incorrect email or password", HttpStatus.BAD_REQUEST);

        String session = UUID.randomUUID().toString();
        newUser.setSession_id(session);
        //TODO: set a session id for authentication
        userRepo.save(newUser);
        System.out.println(newUser.getSession_id());



        return new ResponseEntity<>("saveSession.toString()", HttpStatus.ACCEPTED);
    }
}