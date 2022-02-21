package com.example.carsharingservice.dtos.base;

import com.example.carsharingservice.enums.RestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    private String message;
    private Object data;
    private RestStatus status;

}
