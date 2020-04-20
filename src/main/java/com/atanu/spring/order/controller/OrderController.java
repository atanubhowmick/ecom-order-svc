/**
 * 
 */
package com.atanu.spring.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atanu.spring.order.dto.OrderDetails;
import com.atanu.spring.order.dto.GenericResponse;
import com.atanu.spring.order.service.BaseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Atanu Bhowmick
 *
 */
@RestController
@RequestMapping("/api/cart")
public class OrderController {
	
	@Autowired
	private BaseService<OrderDetails, Long> orderService;
	
	@ApiOperation(value = "Get Cart by Id", response = GenericResponse.class)
	@GetMapping(value = "/get-by-id/{order-id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<OrderDetails>> getCartDetailsById(
			@ApiParam(value = "Provide Cart Id to get Cart Details", required = true) @PathVariable("order-id") Long cartId) {
		GenericResponse<OrderDetails> response = new GenericResponse<>(orderService.get(cartId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get Cart by User Id", response = GenericResponse.class)
	@GetMapping(value = "/get-by-user-id/{user-id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<OrderDetails>> getCartDetailsByUserId(
			@ApiParam(value = "Provide User Id to get Cart Details", required = true) @PathVariable("user-id") Long userId) {
		GenericResponse<OrderDetails> response = new GenericResponse<>(orderService.getByUserId(userId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
