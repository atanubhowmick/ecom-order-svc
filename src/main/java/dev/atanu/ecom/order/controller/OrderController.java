/**
 * 
 */
package dev.atanu.ecom.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.atanu.ecom.order.dto.GenericResponse;
import dev.atanu.ecom.order.dto.OrderDetails;
import dev.atanu.ecom.order.service.BaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Atanu Bhowmick
 *
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private BaseService<OrderDetails, Long> orderService;
	
	@ApiOperation(value = "Get Order Details by Id", response = GenericResponse.class)
	@GetMapping(value = "/get-by-id/{order-id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<OrderDetails>> getCartDetailsById(
			@ApiParam(value = "Order Id", required = true) @PathVariable("order-id") Long cartId) {
		GenericResponse<OrderDetails> response = new GenericResponse<>(orderService.get(cartId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get all Order Details by User Id", response = GenericResponse.class)
	@GetMapping(value = "/get-by-user-id/{user-id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<List<OrderDetails>>> getCartDetailsByUserId(
			@ApiParam(value = "User Id", required = true) @PathVariable("user-id") Long userId) {
		GenericResponse<List<OrderDetails>> response = new GenericResponse<>(orderService.getByUserId(userId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
