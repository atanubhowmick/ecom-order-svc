/**
 * 
 */
package com.atanu.spring.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atanu.spring.order.entity.OrderEntity;

/**
 * @author Atanu Bhowmick
 *
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

	/**
	 * Find Cart by Id
	 * 
	 * @param cartId
	 * @param activeStatus
	 * @return
	 */
	OrderEntity findByCartIdAndActiveStatus(Long cartId, Character activeStatus);
	
	/**
	 * Find by User Id
	 * 
	 * @param userId
	 * @param activeStatus
	 * @return CartEntity
	 */
	OrderEntity findByUserIdAndActiveStatus(Long userId, Character activeStatus);
}
