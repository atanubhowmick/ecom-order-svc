/**
 * 
 */
package dev.atanu.ecom.order.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import dev.atanu.ecom.order.constant.ErrorCode;
import dev.atanu.ecom.order.dto.GenericResponse;
import dev.atanu.ecom.order.dto.ProductDetails;
import dev.atanu.ecom.order.dto.QueryPageable;
import dev.atanu.ecom.order.exception.OrderException;
import dev.atanu.ecom.order.feign.ProductSvcFeign;

/**
 * @author Atanu Bhowmick
 *
 */
@Component
public class ProductSvcClient implements BaseClient {

	private static final Logger logger = LoggerFactory.getLogger(ProductSvcClient.class);
	
	@Autowired
	private ProductSvcFeign productSvcFeign;

	@HystrixCommand(fallbackMethod = "getProducts_fallback")
	public List<ProductDetails> getProducts(QueryPageable queryPageable) {
		ResponseEntity<GenericResponse<List<ProductDetails>>> response = this.productSvcFeign
				.productsBySpecification(true, queryPageable);
		return this.validateResponse(response);
	}

	/**
	 * Fallback method for Hystrix
	 * 
	 */
	@SuppressWarnings("unused")
	private List<ProductDetails> getProducts_fallback(QueryPageable queryPageable) {
		logger.error("Product Service is down. FallBack route enabled.");
		throw new OrderException(ErrorCode.CART_E500.name(), "Product Service is down. Will be back shortly.");
	}
}
