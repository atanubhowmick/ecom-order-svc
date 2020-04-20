/**
 * 
 */
package com.atanu.spring.order.client;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.atanu.spring.order.constant.ErrorCode;
import com.atanu.spring.order.dto.ErrorResponse;
import com.atanu.spring.order.dto.GenericResponse;
import com.atanu.spring.order.exception.OrderException;

/**
 * @author Atanu Bhowmick
 *
 */
public interface BaseClient {

	/**
	 * Default method to validate response
	 * 
	 * @param <T>
	 * @param response
	 * @return T
	 */
	public default <T> T validateResponse(ResponseEntity<GenericResponse<T>> response) {
		if (!HttpStatus.OK.equals(response.getStatusCode()) || !response.getBody().isSuccess()) {
			ErrorResponse error = response.getBody().getError();
			if (Objects.nonNull(error)) {
				throw new OrderException(error.getErrorCode(), error.getErrorMessage());
			} else {
				throw new OrderException(ErrorCode.CART_E500.name(), ErrorCode.CART_E500.getErrorMsg());
			}
		}
		return response.getBody().getPayload();
	}
}
