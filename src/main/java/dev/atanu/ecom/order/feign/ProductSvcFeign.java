/**
 * 
 */
package dev.atanu.ecom.order.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import dev.atanu.ecom.order.dto.GenericResponse;
import dev.atanu.ecom.order.dto.ProductDetails;
import dev.atanu.ecom.order.dto.QueryPageable;

/**
 * @author Atanu Bhowmick
 *
 */
@FeignClient("product-svc")
public interface ProductSvcFeign {

	@PostMapping(value = "/api/product/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<List<ProductDetails>>> productsBySpecification(
			@RequestParam(value = "isListRequired", required = false) boolean isListRequired,
			@RequestBody QueryPageable queryPageable);
}
