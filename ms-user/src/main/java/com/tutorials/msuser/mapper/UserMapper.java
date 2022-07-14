package com.tutorials.msuser.mapper;

import com.tutorials.msuser.entity.User;
import com.tutorials.msuser.model.SignupRequestModel;
import com.tutorials.msuser.model.SignupResponseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapRequestToEntity(SignupRequestModel request);

    SignupResponseModel mapEntityToResponse(User user);
}
