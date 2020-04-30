/**
 * 
 */
package dev.atanu.ecom.order.client;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dev.atanu.ecom.order.constant.ErrorCode;
import dev.atanu.ecom.order.dto.ErrorResponse;
import dev.atanu.ecom.order.dto.GenericResponse;
import dev.atanu.ecom.order.exception.OrderException;

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
