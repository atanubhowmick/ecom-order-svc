/**
 * 
 */
package dev.atanu.ecom.order.service;

import java.util.List;

/**
 * Interface to provide search related operations
 * 
 * @author Atanu Bhowmick
 *
 */
public interface BaseService<T, K> {

	/**
	 * Find by Id
	 * 
	 * @param id
	 * @return T
	 */
	T get(K id);

	/**
	 * Find by User Id
	 * 
	 * @param userId
	 * @return T
	 */
	List<T> getByUserId(Long userId);

	/**
	 * Create
	 * 
	 * @param userId
	 * @param t
	 * @return T
	 */
	T create(Long userId, T t);

}
