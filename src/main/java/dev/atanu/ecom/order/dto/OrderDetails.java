/**
 * 
 */
package dev.atanu.ecom.order.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

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
	
	@NotEmpty(message = "Product Id Map should not be empty")
	private Map<Long, Long> productIdMap;
	private Double totalPrice;
}
