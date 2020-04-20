/**
 * 
 */
package com.atanu.spring.order.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderDetails extends AbstractBaseDTO {

	private static final long serialVersionUID = -8115173794209557247L;

	private Long orderId;
	private List<ProductDetails> products;
	private Double totalPrice;
}