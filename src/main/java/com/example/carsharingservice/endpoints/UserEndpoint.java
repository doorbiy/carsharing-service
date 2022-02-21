package com.example.carsharingservice.endpoints;

import com.example.carsharingservice.dtos.base.BaseResponse;
import com.example.carsharingservice.dtos.user.UserCreateDto;

public interface UserEndpoint {

    BaseResponse saveUser(UserCreateDto userCreateDto);
}
