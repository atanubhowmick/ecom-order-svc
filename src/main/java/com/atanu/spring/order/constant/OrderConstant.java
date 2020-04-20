/**
 * 
 */
package com.atanu.spring.order.constant;

/**
 * This class contains all the constant values for Product Service
 * 
 * @author Atanu Bhowmick
 *
 */
public class OrderConstant {

	/**
	 * Default Constructor
	 */
	private OrderConstant() {
		// No Action Required
	}

	public static final Long DEFAULT_USER_ID					= 100001L;
	public static final Long PRODUCT_CACHE_TTL					= 10L;
	
	public static final String EMPTY_STRING						= "";
	public static final String PRODUCT_SEARCH_CACHE_MAP_KEY		= "product_search_cache_map";
}
