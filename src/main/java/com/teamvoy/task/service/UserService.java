package com.teamvoy.task.service;

import com.teamvoy.task.entity.UserEntity;
import com.teamvoy.task.exception.UserAlreadyExistException;
import com.teamvoy.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        String  str = user.getUsername();
        UserEntity userEntity = userRepository.findByUsername(str);
        if (userEntity!=null){
            throw new UserAlreadyExistException("User already exist");
        }
        return userRepository.save(user);
    }
}
