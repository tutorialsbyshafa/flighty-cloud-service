package com.tutorials.msuser.mapper;

import com.tutorials.msuser.entity.User;
import com.tutorials.msuser.model.SignupRequestModel;
import com.tutorials.msuser.model.SignupResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "email", source = "username")
    User mapRequestToEntity(SignupRequestModel request);

    @Mapping(target = "username", source = "email")
    SignupResponseModel mapEntityToResponse(User user);
}
