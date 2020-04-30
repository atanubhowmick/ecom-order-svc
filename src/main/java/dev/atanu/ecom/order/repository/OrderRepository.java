/**
 * 
 */
package dev.atanu.ecom.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import dev.atanu.ecom.order.entity.OrderEntity;

/**
 * @author Atanu Bhowmick
 *
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

	/**
	 * Find Order Details by Id
	 * 
	 * @param orderId
	 * @param activeStatus
	 * @return
	 */
	OrderEntity findByOrderIdAndActiveStatus(Long orderId, Character activeStatus);

	/**
	 * Find by User Id
	 * 
	 * @param userId
	 * @param activeStatus
	 * @return OrderEntity
	 */
	List<OrderEntity> findByUserIdAndActiveStatus(Long userId, Character activeStatus);
}
