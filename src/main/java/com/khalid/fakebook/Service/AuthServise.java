package com.khalid.fakebook.Service;

import com.khalid.fakebook.Encryption.EncryptPasswordGenerator;
import com.khalid.fakebook.Repo.UserRepo;
import com.khalid.fakebook.dto.RegisterReq;
import com.khalid.fakebook.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;


@Service
@AllArgsConstructor
public class AuthServise {

    private final UserRepo userRepo;

    @Transactional
    public void register(RegisterReq req) {
        User user = new User();

        String salt = EncryptPasswordGenerator.getSalt(30);
        String password = EncryptPasswordGenerator.generateSecurePassword(req.getPassword(), salt);

        user.setPassword(password);
        user.setSalt(salt);
        user.setFirstname(req.getFirstname());
        user.setLastname(req.getLastname());
        user.setEmail(req.getEmail());
        user.setCreatedDate(Instant.now());
        userRepo.save(user);
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