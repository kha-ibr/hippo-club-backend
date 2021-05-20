package com.khalid.fakebook.Controller;

import com.khalid.fakebook.Exception.ResponseException;
import com.khalid.fakebook.PasswordEncryption.EncryptPasswordGenerator;
import com.khalid.fakebook.Service.AuthServise;
import com.khalid.fakebook.Service.SessionService;
import com.khalid.fakebook.dto.LoginReq;
import com.khalid.fakebook.dto.RegisterReq;
import com.khalid.fakebook.dto.UpdateUserReq;
import com.khalid.fakebook.model.Session;
import com.khalid.fakebook.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final AuthServise authServise;
    private final SessionService sessionService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReq req) {
        if(req.getFirstname() == null && req.getLastname() == null)
            return new ResponseEntity<>(ResponseException.jsonResponse("error", "one or more fields appears to be empty."), HttpStatus.BAD_REQUEST);

        //Checking for email patterns
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!req.getEmail().matches(regex))
            return new ResponseEntity<>(ResponseException.jsonResponse("error", "Email not valid. Make sure you are using correct email."), HttpStatus.BAD_REQUEST);

        // Check if email already exists.
        User emailExists = authServise.findByEmail(req.getEmail());

        if (emailExists != null)
            return new ResponseEntity<>(ResponseException.jsonResponse("error", "Oops! There is already a req registered with the email provided."), HttpStatus.BAD_REQUEST);

        //Password length
        if (req.getPassword().length() < 8)
            return new ResponseEntity<>(ResponseException.jsonResponse("error", "Password too short. Should be at least 8 character."), HttpStatus.BAD_REQUEST);

        authServise.register(req);

        return new ResponseEntity<>(ResponseException.jsonResponse("success", "User created successfully."), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq req) {
        User user = authServise.findByEmail(req.getEmail());

        if (user != null) {
            // Decrypt password
            boolean passwordVerified = EncryptPasswordGenerator.verifyUserPassword(req.getPassword(), user.getPassword(), user.getSalt());

            //TODO: Check if the user provide something or not
            //TODO: Create error handler response class

            // Check if if email and password is not null
            if (user.getEmail() == null && !passwordVerified)
                return new ResponseEntity<>(ResponseException.jsonResponse("error", "Incorrect email or password"), HttpStatus.BAD_REQUEST);

            // Generate random session id to verify the user. after login.
            String generateSession = UUID.randomUUID().toString();
            Session session = sessionService.saveSession(generateSession, user);

            return new ResponseEntity<>(ResponseException.jsonResponse("success", session.getSession()), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(ResponseException.jsonResponse("error", "There is no user with that " + req.getEmail()), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserReq req, @PathVariable("userId") Long userId, @RequestHeader(value = "Authentication") String validateSession) {

        if (sessionService.findBySession(validateSession) == null)
            return new ResponseEntity<>(ResponseException.jsonResponse("error", "Access denied. You need to login first"), HttpStatus.FORBIDDEN);

        authServise.updateUser(req, userId);
        return new ResponseEntity<>(ResponseException.jsonResponse("success", "Post updated successfully"), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long id,  @RequestHeader(value = "Authentication") String validateSession) {
        // checking if the user is logged in by validating the session id.
        if (sessionService.findBySession(validateSession) == null)
            return new ResponseEntity<>(ResponseException.jsonResponse("error", "Access denied. You need to login first"), HttpStatus.FORBIDDEN);

        //checking if the user id exists in the database.
        if (authServise.findById(id).isEmpty())
            return new ResponseEntity<>(ResponseException.jsonResponse("error", "There is no user with that (" + id + ") id"), HttpStatus.BAD_REQUEST);

        authServise.deleteUser(id);
        return new ResponseEntity<>(ResponseException.jsonResponse("success", "User with the id of (" + id + ") is deleted successfully"), HttpStatus.OK);
    }
}