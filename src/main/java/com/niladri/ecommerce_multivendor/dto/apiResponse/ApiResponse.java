package com.niladri.ecommerce_multivendor.dto.apiResponse;

import com.niladri.ecommerce_multivendor.dto.errorResponse.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
	private LocalDateTime timeStamp;
	private T data;
	private ErrorResponse error;

	public ApiResponse() {
		this.timeStamp = LocalDateTime.now();
	}

	public ApiResponse(T data){
		this();
		this.data = data;
	}

	public ApiResponse(ErrorResponse error){
		this();
		this.error = error;
	}



}
