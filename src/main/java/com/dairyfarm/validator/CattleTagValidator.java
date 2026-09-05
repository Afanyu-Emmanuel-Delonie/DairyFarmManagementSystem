package com.dairyfarm.validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("cattleTagValidator")
public class CattleTagValidator implements Validator<String> {
    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (value == null || !value.trim().matches("[A-Za-z0-9-]{3,20}")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Use 3-20 letters, numbers, or hyphens", null));
        }
    }
}
