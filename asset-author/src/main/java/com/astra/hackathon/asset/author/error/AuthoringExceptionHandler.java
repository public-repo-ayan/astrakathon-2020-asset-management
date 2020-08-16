package com.astra.hackathon.asset.author.error;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.astra.hackathon.asset.exceptions.InvalidRequestException;
import com.astra.hackathon.asset.exceptions.StorageException;

@ControllerAdvice
public class AuthoringExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { InvalidRequestException.class })
	protected ResponseEntity<Object> handleMissingValues(RuntimeException ex, WebRequest req) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
	}

	@ExceptionHandler(value = { StorageException.class })
	protected ResponseEntity<Object> handleCassandraErrors(RuntimeException ex, WebRequest req) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, req);
	}
}
