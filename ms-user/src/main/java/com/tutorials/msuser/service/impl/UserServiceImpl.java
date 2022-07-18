package com.tutorials.msuser.service.impl;

import com.tutorials.msuser.exception.AppException;
import com.tutorials.msuser.mapper.UserMapper;
import com.tutorials.msuser.model.SignupRequestModel;
import com.tutorials.msuser.model.SignupResponseModel;
import com.tutorials.msuser.repository.UserRepository;
import com.tutorials.msuser.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public SignupResponseModel signup(SignupRequestModel request) {
        checkUserUniqueness(request.getUsername());
        validateUsername(request.getUsername());
        validatePassword(request.getPassword());
        validateFullname(request.getFullName());

        var user = userMapper.mapRequestToEntity(request);

        userRepository.save(user);

        return userMapper.mapEntityToResponse(user);
    }

    private void checkUserUniqueness(String email) {
        if (userRepository.findByEmail(email).isPresent())
            throw new AppException("User is duplicate by this username");
    }

    private void validateUsername(String email) {
        if (!email.contains("@")
                && !email.matches("" +
                "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}"))
            throw new AppException("Email format is not correct");
    }

    private void validatePassword(String password) {
        if (password.length() < 5)
            throw new AppException("Password is short");
    }

    private void validateFullname(String fullname) {
        if (fullname.split(" ").length < 2)
            throw new AppException("Full name must contain at least two words");
    }
}
