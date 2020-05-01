/**
 * 
 */
package dev.atanu.ecom.order.constant;

/**
 * This Enum contains all the error code and error message
 * 
 * @author Atanu Bhowmick
 *
 */
public enum ErrorCode {

	CART_E001("No Product found"), 
	CART_E002("No Search result found"),
	CART_E006("Invalid Json"),
	
	CART_E500("Internal Server Error. Please try again later!");
	
	private String errorMsg;

	private ErrorCode(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
