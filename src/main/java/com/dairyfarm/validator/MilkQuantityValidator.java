package com.dairyfarm.validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("milkQuantityValidator")
public class MilkQuantityValidator implements Validator<Number> {
    @Override
    public void validate(FacesContext context, UIComponent component, Number value) throws ValidatorException {
        // f:convertNumber yields a Long for whole numbers and a Double otherwise, so accept any Number.
        double litres = value == null ? -1 : value.doubleValue();
        if (litres < 0 || litres > 100) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Quantity must be between 0 and 100 litres", null));
        }
    }
}
