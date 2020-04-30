/**
 * 
 */
package dev.atanu.ecom.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import dev.atanu.ecom.order.annotation.LogMethodCall;
import dev.atanu.ecom.order.client.ProductSvcClient;
import dev.atanu.ecom.order.constant.OrderConstant;
import dev.atanu.ecom.order.constant.QueryFilterEnum;
import dev.atanu.ecom.order.constant.QueryOperatorEnum;
import dev.atanu.ecom.order.constant.StatusEnum;
import dev.atanu.ecom.order.dto.OrderDetails;
import dev.atanu.ecom.order.dto.ProductDetails;
import dev.atanu.ecom.order.dto.QueryFilter;
import dev.atanu.ecom.order.dto.QueryPageable;
import dev.atanu.ecom.order.entity.OrderEntity;
import dev.atanu.ecom.order.entity.OrderProductMappingEntity;
import dev.atanu.ecom.order.repository.OrderRepository;
import dev.atanu.ecom.order.util.OrderUtil;

/**
 * @author Atanu Bhowmick
 *
 */
@Service
@LogMethodCall(showParams = true, showResult = true)
public class OrderServiceImpl implements BaseService<OrderDetails, Long> {

	@Autowired
	private OrderRepository cartRepository;

	@Autowired
	private ProductSvcClient productSvcClient;

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Override
	public OrderDetails get(Long id) {
		OrderEntity orderEntity = cartRepository.findByOrderIdAndActiveStatus(id, StatusEnum.ACTIVE.getValue());
		return this.getOrderDetails(orderEntity);
	}

	@Override
	public List<OrderDetails> getByUserId(Long userId) {
		List<OrderDetails> orderDetails = new ArrayList<>();
		List<OrderEntity> orderEntities = cartRepository.findByUserIdAndActiveStatus(userId,
				StatusEnum.ACTIVE.getValue());
		if (!CollectionUtils.isEmpty(orderEntities)) {
			orderDetails.addAll(
					orderEntities.stream().map(entity -> this.getOrderDetails(entity)).collect(Collectors.toList()));
		}
		return orderDetails;
	}

	/**
	 * 
	 * @param orderEntity
	 * @return {@link OrderDetails}
	 */
	private OrderDetails getOrderDetails(OrderEntity orderEntity) {
		OrderDetails orderDetails = null;
		if (orderEntity != null) {
			orderDetails = new OrderDetails();
			orderDetails.setOrderId(orderEntity.getOrderId());
			orderDetails.setOrderDate(orderEntity.getOrderDate());
			if (!CollectionUtils.isEmpty(orderEntity.getOrderProductMappings())) {
				List<Long> productIds = orderEntity.getOrderProductMappings().stream()
						.map(OrderProductMappingEntity::getProductId).collect(Collectors.toList());
				QueryPageable queryPageable = new QueryPageable(0, Integer.MAX_VALUE);
				QueryFilter queryFilter = new QueryFilter(QueryFilterEnum.ID, productIds, QueryOperatorEnum.IN);
				queryPageable.getFilters().add(queryFilter);
				List<ProductDetails> products = productSvcClient.getProducts(queryPageable);
				logger.debug("Products for order id {} are: {}", orderEntity.getOrderId(), products);
				Map<Long, List<OrderProductMappingEntity>> map = orderEntity.getOrderProductMappings().stream()
						.collect(Collectors.groupingBy(OrderProductMappingEntity::getProductId));
				products.stream().forEach(product -> {
					if (map.containsKey(product.getProductId())) {
						product.setProductCount(map.get(product.getProductId()).get(0).getProductCount());
					}
				});
				Double totalPrice = products.stream()
						.collect(Collectors.summingDouble(pdt -> pdt.getProductPrice() * pdt.getProductCount()));
				orderDetails.setProducts(products);
				orderDetails.setTotalPrice(
						Double.valueOf(OrderUtil.formatDecimal(OrderConstant.TWO_DECIMAL_PLACE, totalPrice)));
			}
		}
		return orderDetails;
	}
}
