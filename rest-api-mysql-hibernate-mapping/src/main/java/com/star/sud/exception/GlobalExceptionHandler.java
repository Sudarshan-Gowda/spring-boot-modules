package com.star.sud.exception;

import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.star.sud.mapping.common.ErrorCode;
import com.star.sud.mapping.util.GenerateResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException ex, WebRequest request) {

		logger.error(String.format("resourceNotFoundExceptionHandler :: %s", ex.getMessage()));

		return new ResponseEntity<>(GenerateResponse.getErrorResponse(ErrorCode.EC003.getCode(),
				ErrorCode.EC003.getDescription(), ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
		logger.error("globalExceptionHandler: ", ex);
		return new ResponseEntity<>(
				GenerateResponse.getErrorResponse(ErrorCode.EC002.getCode(), ErrorCode.EC002.getDescription(), null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		logger.error("handleMethodArgumentNotValid", ex);

		String collect = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage())
				.collect(Collectors.joining(","));

		return new ResponseEntity<>(
				GenerateResponse.getErrorResponse(ErrorCode.EC002.getCode(), ErrorCode.EC002.getDescription(), collect),
				HttpStatus.BAD_REQUEST);
	}

}
