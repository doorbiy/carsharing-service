package com.example.carsharingservice.mappers;

import com.example.carsharingservice.dtos.base.BaseResponse;
import com.example.carsharingservice.enums.RestStatus;
import org.springframework.stereotype.Component;

@Component
public class BaseMapper {

    public BaseResponse mapToBaseResponse(String message, Object data, RestStatus status) {
        BaseResponse baseResponse = new BaseResponse();

        baseResponse.setMessage(message);
        baseResponse.setData(data);
        baseResponse.setStatus(status);

        return baseResponse;
    }
}
