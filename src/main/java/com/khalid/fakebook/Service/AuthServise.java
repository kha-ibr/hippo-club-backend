package com.khalid.fakebook.Service;

import antlr.BaseAST;
import com.khalid.fakebook.Encryption.EncryptPasswordGenerator;
import com.khalid.fakebook.Repo.UserRepo;
import com.khalid.fakebook.Repo.VerificationTokenRepo;
import com.khalid.fakebook.dto.LoginReq;
import com.khalid.fakebook.dto.RegisterReq;
import com.khalid.fakebook.model.User;
import com.khalid.fakebook.model.VerificationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
@AllArgsConstructor
public class AuthServise {

    private final UserRepo userRepo;
    private final VerificationTokenRepo verificationTokenRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegisterReq req) {
        User user = new User();


        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setFirstname(req.getFirstname());
        user.setLastname(req.getLastname());
        user.setEmail(req.getEmail());
        user.setAvatar(req.getAvatar());
        user.setCreatedDate(Instant.now());
        user.setEnabled(false);
        userRepo.save(user);
    }

    @Transactional
    public void login(LoginReq req) {

    }

//    public Optional<Object> findUserByName(String firstName, String lastName) {
//        return userRepo.findUserByName(firstName, lastName);
//    }

//    public User findUserById(Long id) {
//        return userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("User by id " + id + " was not found"));
//    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

}