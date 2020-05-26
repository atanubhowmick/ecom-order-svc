/**
 * 
 */
package dev.atanu.ecom.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Entity
@Table(name = "ORDER_PRODUCT_MAPPING")
public class OrderProductMappingEntity extends BaseEntity {

	private static final long serialVersionUID = 724082340071008053L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_PRODUCT_ID")
	private Long orderProductId;

	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "PRODUCT_COUNT", nullable = false)
	private Long productCount;

	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private OrderEntity orderEntity;

}
