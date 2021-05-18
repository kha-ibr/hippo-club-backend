package com.khalid.fakebook.Service;

import com.khalid.fakebook.Exception.UserNotFoundException;
import com.khalid.fakebook.PasswordEncryption.EncryptPasswordGenerator;
import com.khalid.fakebook.Repo.UserRepo;
import com.khalid.fakebook.dto.RegisterReq;
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
    public void register(RegisterReq req) {
        User user = new User();

        String salt = EncryptPasswordGenerator.getSalt(30);
        String password = EncryptPasswordGenerator.generateSecurePassword(req.getPassword(), salt);

        user.setPassword(password);
        user.setSalt(salt);
        user.setFirstname(req.getFirstname());
        user.setLastname(req.getLastname());
        user.setEmail(req.getEmail());
        user.setCreatedAt(Instant.now());
        userRepo.save(user);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void updateUser(User users, Long userId) {
        System.out.println(userRepo.findUserByUserId(userId));
        userRepo.findUserByUserId(userId).map(user -> {
            user.setAvatar(users.getAvatar());
            return userRepo.save(user);
        })
        .orElseThrow(() -> new UserNotFoundException("post id not found"));
    }

    public void deleteUser(Long id) {
        userRepo.deleteUserByUserId(id);
    }

    public Optional<User> findById(Long id){
        return userRepo.findUserByUserId(id);
    }
}