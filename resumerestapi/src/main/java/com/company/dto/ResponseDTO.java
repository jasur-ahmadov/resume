package com.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {

    private Integer errorCode;
    private String errorMessage;
    private String successMessage;
    private Object obj;

    private ResponseDTO() {
    }

    public static ResponseDTO of(Object obj) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setObj(obj);
        return responseDTO;
    }

    public static ResponseDTO of(Object obj, String errorMessage) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setObj(obj);
        responseDTO.setErrorMessage(errorMessage);
        return responseDTO;
    }

    public static ResponseDTO of(Object obj, String errorMessage, String successMessage) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setObj(obj);
        responseDTO.setErrorMessage(errorMessage);
        responseDTO.setSuccessMessage(successMessage);
        return responseDTO;
    }
}