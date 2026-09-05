package com.dairyfarm.bean;

import com.dairyfarm.dao.CattleDAO;
import com.dairyfarm.dao.MilkProductionDAO;
import com.dairyfarm.model.Cattle;
import com.dairyfarm.model.MilkProduction;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("milkProductionBean")
@ViewScoped
public class MilkProductionBean implements Serializable {
    private final MilkProductionDAO dao = new MilkProductionDAO();
    private final CattleDAO cattleDAO = new CattleDAO();
    private List<MilkProduction> productionList;
    private List<Cattle> cattleList;
    private MilkProduction production;

    @PostConstruct
    public void init() {
        production = new MilkProduction();
        load();
        cattleList = new ArrayList<>(cattleDAO.findAll());
    }

    public void load() { productionList = new ArrayList<>(dao.findAll()); }

    public void edit(MilkProduction selected) { production = selected; }

    public void save() {
        try {
            if (production.getId() == null) dao.save(production); else dao.update(production);
            addMessage(FacesMessage.SEVERITY_INFO, "Milk production saved successfully");
            production = new MilkProduction();
            load();
        } catch (RuntimeException ex) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Milk production could not be saved");
        }
    }

    public void delete(MilkProduction selected) {
        try {
            dao.delete(selected.getId());
            addMessage(FacesMessage.SEVERITY_INFO, "Milk production deleted successfully");
            load();
        } catch (RuntimeException ex) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Milk production could not be deleted");
        }
    }

    public void cancel() { production = new MilkProduction(); }

    private void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, null));
    }

    public List<MilkProduction> getProductionList() { return productionList; }
    public List<Cattle> getCattleList() { return cattleList; }
    public MilkProduction getProduction() { return production; }
    public void setProduction(MilkProduction production) { this.production = production; }
}
