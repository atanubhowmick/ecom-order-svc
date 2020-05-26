/**
 * 
 */
package dev.atanu.ecom.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@ApiModel(value = "WarningResponse", description = "Warning Details")
public class WarningResponse extends AbstractBaseDTO {
	
	private static final long serialVersionUID = 694389539806213873L;

	@ApiModelProperty(value = "Warn Code", example = "WE100")
	private String warnCode;

	@ApiModelProperty(value = "Warn Message", example = "Delevery might be delayed due to high traffic")
	private String warnMessage;
}
