package com.tutorials.msuser.mapper;

import com.tutorials.msuser.entity.User;
import com.tutorials.msuser.model.SignupRequestModel;
import com.tutorials.msuser.model.SignupResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "email", source = "username")
    public abstract User mapRequestToEntity(SignupRequestModel request);

    @Mapping(target = "username", source = "email")
    public abstract SignupResponseModel mapEntityToResponse(User user);
}
