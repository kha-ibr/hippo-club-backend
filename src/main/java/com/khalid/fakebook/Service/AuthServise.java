package com.khalid.fakebook.Service;

import com.khalid.fakebook.Exception.UserNotFoundException;
import com.khalid.fakebook.PasswordEncryption.EncryptPasswordGenerator;
import com.khalid.fakebook.Repo.UserRepo;
import com.khalid.fakebook.dto.RegisterReq;
import com.khalid.fakebook.dto.UpdateUserReq;
import com.khalid.fakebook.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AuthServise {

    private final UserRepo userRepo;

    @Transactional
    public User register(RegisterReq req) {
        User user = new User();

        String salt = EncryptPasswordGenerator.getSalt(30);
        String password = EncryptPasswordGenerator.generateSecurePassword(req.getPassword(), salt);

        user.setPassword(password);
        user.setSalt(salt);
        user.setFirstname(req.getFirstname());
        user.setLastname(req.getLastname());
        user.setEmail(req.getEmail());
        user.setCreatedAt(Instant.now());
        return userRepo.save(user);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void updateUser(UpdateUserReq req, Long userId) {

        userRepo.findById(userId).map(user -> {
            user.setAvatar(req.getAvatar());
            return userRepo.save(user);
        })
        .orElseThrow(() -> new UserNotFoundException("user id not found"));
    }

    public void deleteUser(Long id) {
        userRepo.findByUserId(id).map(user -> {
            userRepo.delete(user);
            return user.getUserId();
        });
    }

    public Optional<User> findById(Long id){
        return userRepo.findByUserId(id);
    }
}