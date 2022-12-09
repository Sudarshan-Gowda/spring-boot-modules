package com.star.sud.mapping.util;

import java.util.ArrayList;
import java.util.List;

import com.star.sud.common.dto.Error;
import com.star.sud.common.dto.ErrorDetail;
import com.star.sud.common.dto.ResponseDTO;

public class GenerateResponse {

	public static <T> ResponseDTO<T> getSuccessResponse(T data) {
		ResponseDTO<T> responseDto = new ResponseDTO<>();
		responseDto.setData(data);
		return responseDto;
	}

	public static <T> ResponseDTO<T> getErrorResponse(Error error) {
		ResponseDTO<T> responseDto = new ResponseDTO<>();
		responseDto.setError(error);
		return responseDto;
	}

	public static <T> ResponseDTO<T> getErrorResponse(String errorCode, String errorDescription, String errorDetails) {
		ResponseDTO<T> responseDTO = new ResponseDTO<>();
		responseDTO.setError(getError(errorCode, errorDescription, errorDetails, "ERROR"));
		return responseDTO;
	}

	private static Error getError(String code, String message, String detail, String severity) {
		Error error = new Error();
		List<ErrorDetail> errors = new ArrayList<>();
		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setCode(code);
		errorDetail.setMessage(message);
		errorDetail.setDetails(detail);
		errorDetail.setSeverityLevel(severity);
		errors.add(errorDetail);
		error.setErrors(errors);
		return error;
	}

}
