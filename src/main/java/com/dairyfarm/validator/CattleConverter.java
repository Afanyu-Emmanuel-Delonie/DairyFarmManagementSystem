package com.dairyfarm.validator;

import com.dairyfarm.dao.CattleDAO;
import com.dairyfarm.model.Cattle;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("cattleConverter")
public class CattleConverter implements Converter<Cattle> {
    private final CattleDAO dao = new CattleDAO();

    @Override
    public Cattle getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return dao.findById(Long.valueOf(value));
        } catch (NumberFormatException ex) {
            throw new ConverterException("Invalid cattle selection", ex);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Cattle value) {
        return value == null || value.getId() == null ? "" : value.getId().toString();
    }
}
