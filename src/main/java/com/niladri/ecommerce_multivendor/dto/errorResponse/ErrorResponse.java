package com.niladri.ecommerce_multivendor.dto.errorResponse;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@Builder
public class ErrorResponse {
	private HttpStatus status;
	private String message;
	private Map<String,String> subErrors;
}
