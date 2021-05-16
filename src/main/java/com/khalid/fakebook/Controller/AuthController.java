package com.khalid.fakebook.Controller;

import com.khalid.fakebook.Encryption.EncryptPasswordGenerator;
import com.khalid.fakebook.Service.AuthServise;
import com.khalid.fakebook.Service.SessionService;
import com.khalid.fakebook.dto.LoginReq;
import com.khalid.fakebook.dto.RegisterReq;
import com.khalid.fakebook.model.Session;
import com.khalid.fakebook.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final AuthServise authServise;
    private final SessionService sessionService;


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
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginReq req) {
        Map<String, String> errorResponse = new HashMap<String, String>();
        Map<String, String> successResponse = new HashMap<String, String>();

        User user = authServise.findByEmail(req.getEmail());
        boolean passwordVerified = EncryptPasswordGenerator.verifyUserPassword(req.getPassword(), user.getPassword(), user.getSalt());

        errorResponse.put("error", "Incorrect email or password");
        if (user.getEmail() == null && !passwordVerified)
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

        String generateSession = UUID.randomUUID().toString();

        Session session = sessionService.saveSession(generateSession, user);
        successResponse.put("Status", "Success");
        successResponse.put("session", session.getSession());
        return new ResponseEntity<>(successResponse, HttpStatus.ACCEPTED);
    }
}