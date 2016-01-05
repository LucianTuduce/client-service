package ro.fortech.faces;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 *
 */
@FacesValidator("notNullValidator")
public class NotNullValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,Object value) throws ValidatorException {

		String valueToBeChecked = String.valueOf(value);
			
		boolean valid = true;

		if (StringUtils.isBlank(valueToBeChecked)) {
			valid = false;
		}
				
	if (!valid) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The field must be filled", "The field must be filled");
			throw new ValidatorException(message);
		}
	}

}
