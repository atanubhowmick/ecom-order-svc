/**
 * 
 */
package dev.atanu.ecom.order.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import dev.atanu.ecom.order.constant.OrderConstant;

/**
 * Custom AuditAware for Order Service
 * 
 * @author Atanu Bhowmick
 *
 */
public class EcomAuditorAware implements AuditorAware<Long>{

	@Override
	public Optional<Long> getCurrentAuditor() {
		Long userId = OrderConstant.DEFAULT_USER_ID;
		
		/*
		 * Here we can read the user information from Spring Security Context
		 * for logged-in scenario and set the userId in AuditorAware. 
		 * 
		 * This will help to monitor all insert/update query in Database tables.
		 * 
		 * CREATED_BY and LAST_MODIFIED_BY columns will be automatically updated 
		 */
		
		return Optional.of(userId);
	}

}
