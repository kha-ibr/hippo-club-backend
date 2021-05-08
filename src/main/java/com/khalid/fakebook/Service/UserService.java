package com.khalid.fakebook.Service;

import com.khalid.fakebook.Exception.UserNotFoundException;
import com.khalid.fakebook.Repo.UserRepo;
import com.khalid.fakebook.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User addUser(User user) {
        return userRepo.save(user);
    }

//    public Optional<Object> findUserByName(String firstName, String lastName) {
//        return userRepo.findUserByName(firstName, lastName);
//    }

    public User findUserById(Long id) {
        return (User) userRepo.findUserById(id).orElseThrow(()-> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public User findUserByEmail(User user) {
        return userRepo.findUserByEmail(user.getEmail());
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteUserById(id);
    }
}
