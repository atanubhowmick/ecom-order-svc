/**
 * 
 */
package dev.atanu.ecom.order.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

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
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd MMM, yyyy - hh:mm a")
	private Date orderDate;
	
	private List<ProductDetails> products;
	
	@NotEmpty(message = "Product Id Map should not be empty")
	private Map<Long, Long> productIdMap;
	
	private Double totalPrice;
}
