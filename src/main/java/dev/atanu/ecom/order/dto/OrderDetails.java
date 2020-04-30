/**
 * 
 */
package dev.atanu.ecom.order.dto;

import java.util.Date;
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
	private Date orderDate;
	private List<ProductDetails> products;
	private Double totalPrice;
}
