package com.dairyfarm.bean;

import com.dairyfarm.dao.CattleDAO;
import com.dairyfarm.model.Cattle;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("cattleBean")
@ViewScoped
public class CattleBean implements Serializable {
    private final CattleDAO dao = new CattleDAO();
    private List<Cattle> cattleList;
    private Cattle cattle;

    @PostConstruct
    public void init() {
        cattle = new Cattle();
        load();
    }

    public void load() {
        cattleList = new ArrayList<>(dao.findAll());
    }

    public void edit(Cattle selected) {
        cattle = selected;
    }

    public void save() {
        try {
            if (cattle.getId() == null) dao.save(cattle); else dao.update(cattle);
            addMessage(FacesMessage.SEVERITY_INFO, "Cattle saved successfully");
            cattle = new Cattle();
            load();
        } catch (RuntimeException ex) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Cattle could not be saved");
        }
    }

    public void delete(Cattle selected) {
        try {
            dao.delete(selected.getId());
            addMessage(FacesMessage.SEVERITY_INFO, "Cattle deleted successfully");
            load();
        } catch (RuntimeException ex) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Cattle could not be deleted");
        }
    }

    public void cancel() { cattle = new Cattle(); }

    private void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, null));
    }

    public List<Cattle> getCattleList() { return cattleList; }
    public Cattle getCattle() { return cattle; }
    public void setCattle(Cattle cattle) { this.cattle = cattle; }
}
