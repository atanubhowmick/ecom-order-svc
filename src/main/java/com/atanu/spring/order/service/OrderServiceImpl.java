/**
 * 
 */
package com.atanu.spring.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.atanu.spring.order.annotation.LogMethodCall;
import com.atanu.spring.order.client.ProductSvcClient;
import com.atanu.spring.order.constant.QueryFilterEnum;
import com.atanu.spring.order.constant.QueryOperatorEnum;
import com.atanu.spring.order.constant.StatusEnum;
import com.atanu.spring.order.dto.OrderDetails;
import com.atanu.spring.order.dto.ProductDetails;
import com.atanu.spring.order.dto.QueryFilter;
import com.atanu.spring.order.dto.QueryPageable;
import com.atanu.spring.order.entity.OrderEntity;
import com.atanu.spring.order.entity.OrderProductMappingEntity;
import com.atanu.spring.order.repository.OrderRepository;

/**
 * @author Atanu Bhowmick
 *
 */
@Service
@LogMethodCall(logLevel = Level.INFO, showParams = true, showResult = true)
public class OrderServiceImpl implements BaseService<OrderDetails, Long> {

	@Autowired
	private OrderRepository cartRepository;

	@Autowired
	private ProductSvcClient productSvcClient;

	@Override
	public OrderDetails get(Long id) {
		OrderDetails cartDetails = null;
		OrderEntity cartEntity = cartRepository.findByOrderIdAndActiveStatus(id, StatusEnum.ACTIVE.getValue());
		if (cartEntity != null) {
			cartDetails = new OrderDetails();
			cartDetails.setOrderId(cartEntity.getOrderId());
			if (!CollectionUtils.isEmpty(cartEntity.getOrderProductMappings())) {
				List<Long> productIds = cartEntity.getOrderProductMappings().stream()
						.map(OrderProductMappingEntity::getProductId).collect(Collectors.toList());
				QueryPageable queryPageable = new QueryPageable(0, Integer.MAX_VALUE);
				QueryFilter queryFilter = new QueryFilter(QueryFilterEnum.ID, productIds, QueryOperatorEnum.IN);
				queryPageable.getFilters().add(queryFilter);
				List<ProductDetails> products = productSvcClient.getProducts(queryPageable);
				Double totalPrice = products.stream()
						.collect(Collectors.summingDouble(ProductDetails::getProductPrice));
				cartDetails.setProducts(products);
				cartDetails.setTotalPrice(totalPrice);
			}

		}
		return cartDetails;
	}

	@Override
	public OrderDetails getByUserId(Long userId) {
		OrderDetails cartDetails = null;
		OrderEntity cartEntity = cartRepository.findByUserIdAndActiveStatus(userId, StatusEnum.ACTIVE.getValue());
		if (cartEntity != null) {
			cartDetails = new OrderDetails();
			cartDetails.setOrderId(cartEntity.getOrderId());
			if (!CollectionUtils.isEmpty(cartEntity.getOrderProductMappings())) {
				List<Long> productIds = cartEntity.getOrderProductMappings().stream()
						.map(OrderProductMappingEntity::getProductId).collect(Collectors.toList());
				QueryPageable queryPageable = new QueryPageable(0, Integer.MAX_VALUE);
				QueryFilter queryFilter = new QueryFilter(QueryFilterEnum.ID, productIds, QueryOperatorEnum.IN);
				queryPageable.getFilters().add(queryFilter);
				List<ProductDetails> products = productSvcClient.getProducts(queryPageable);
				Double totalPrice = products.stream()
						.collect(Collectors.summingDouble(ProductDetails::getProductPrice));
				cartDetails.setProducts(products);
				cartDetails.setTotalPrice(totalPrice);
			}
		}
		return cartDetails;
	}

}
