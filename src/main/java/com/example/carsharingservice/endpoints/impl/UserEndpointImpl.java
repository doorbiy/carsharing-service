package com.example.carsharingservice.endpoints.impl;

import com.example.carsharingservice.dtos.base.BaseResponse;
import com.example.carsharingservice.dtos.user.UserCreateDto;
import com.example.carsharingservice.dtos.user.UserResponse;
import com.example.carsharingservice.endpoints.UserEndpoint;
import com.example.carsharingservice.enums.RestStatus;
import com.example.carsharingservice.mappers.BaseMapper;
import com.example.carsharingservice.mappers.UserMapper;
import com.example.carsharingservice.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class UserEndpointImpl implements UserEndpoint {

    private final UserService userService;

    private final BaseMapper baseMapper;

    private final UserMapper userMapper;

    public UserEndpointImpl(UserService userService, BaseMapper baseMapper, UserMapper userMapper) {
        this.userService = userService;
        this.baseMapper = baseMapper;
        this.userMapper = userMapper;
    }

    @Override
    public BaseResponse saveUser(UserCreateDto userCreateDto) {
        try {

        String phoneNumberValidateRegEx = "^(996)?\\s\\d{3}-\\d{3}-\\d{3}";
        // if (!Pattern.matches("\\d{3}\\s\\d{3}-\\d{3}-\\d{3}", userCreateDto.getPhoneNumber()))

        if (!Pattern.matches(phoneNumberValidateRegEx, userCreateDto.getPhoneNumber())) {

            return baseMapper.mapToBaseResponse(
                    "Вы ввели некорректный номер телефона!",
                    null,
                    RestStatus.FAILED
            );
        }

        if (userService.existsByPhoneNumber(userCreateDto.getPhoneNumber())) {

            return baseMapper.mapToBaseResponse(
                    "Клиент с таким номером телефона уже существует",
                    null,
                    RestStatus.FAILED
            );
        }

        if (!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}", userCreateDto.getDob())) {

            return baseMapper.mapToBaseResponse(
                    "Введите корректную дату",
                    null,
                    RestStatus.FAILED
            );
        }

        if (userCreateDto.getName().isBlank() || Objects.isNull(userCreateDto.getName())
                || userCreateDto.getPhoneNumber().isBlank() || Objects.isNull(userCreateDto.getPhoneNumber())
                || userCreateDto.getAddress().isBlank() || Objects.isNull(userCreateDto.getAddress())
                || userCreateDto.getCity().isBlank() || Objects.isNull(userCreateDto.getCity())
                || userCreateDto.getDob().isBlank() || Objects.isNull(userCreateDto.getDob())) {

            return baseMapper.mapToBaseResponse(
                    "Заполните все данные!",
                    null,
                    RestStatus.FAILED
            );
        }

        UserResponse userResponse = userMapper.mapToUserResponse(userService.save(userCreateDto));

        return baseMapper.mapToBaseResponse(
                "Клиент успешно создан!",
                userResponse,
                RestStatus.SUCCESS
        );

        } catch (Exception e) {

            return baseMapper.mapToBaseResponse(
                    "Сохранить не удалось произошла ошибка системы!",
                    null,
                    RestStatus.FAILED
            );
        }
    }
}
