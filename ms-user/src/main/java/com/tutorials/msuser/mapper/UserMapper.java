package com.tutorials.msuser.mapper;

import com.tutorials.msuser.entity.User;
import com.tutorials.msuser.exception.AppException;
import com.tutorials.msuser.model.SignupRequestModel;
import com.tutorials.msuser.model.SignupResponseModel;
import com.tutorials.msuser.repository.RoleRepository;
import java.util.Collections;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    protected BCryptPasswordEncoder encoder;
    protected RoleRepository roleRepository;

    @Mapping(target = "email", source = "username")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", ignore = true)
    public abstract User mapRequestToEntity(SignupRequestModel request);

    @AfterMapping
    void mapAdditionalValues(@MappingTarget User.UserBuilder user, SignupRequestModel request) {
        user.password(encoder.encode(request.getPassword()));

        user.roles(Collections.singletonList(roleRepository.findByNameIgnoreCase("ROLE_USER")
                .orElseThrow(() -> new AppException("role not found"))));
    }

    @Mapping(target = "username", source = "email")
    public abstract SignupResponseModel mapEntityToResponse(User user);

    @Autowired
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
