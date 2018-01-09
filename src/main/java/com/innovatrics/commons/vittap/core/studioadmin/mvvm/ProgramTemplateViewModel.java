package com.innovatrics.commons.vittap.core.studioadmin.mvvm;

import com.innovatrics.commons.vittap.core.studioadmin.service.ProgramTemplateService;
import com.innovatrics.commons.vittap.model.dao.ProgramTemplate;
import com.innovatrics.commons.vittap.model.dao.Tarif;
import com.innovatrics.commons.vittap.model.repository.TarifRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class ProgramTemplateViewModel {

  private final Logger log = LoggerFactory.getLogger(ProgramTemplateViewModel.class);

  private String keyword;
  private List<ProgramTemplate> programTemplateList;
  private ProgramTemplate selectedProgramTemplate = new ProgramTemplate();

  private List<Tarif> listTarif;
  private Tarif selectedTarif;


  @WireVariable("programTemplateService")
  private ProgramTemplateService service;

  @WireVariable("tarifRepository")
  private TarifRepository tarifRepository;

  @Command
  @NotifyChange("programTemplateList")
  public void search(){
    programTemplateList = service.findByNameOrDescription(keyword);
  }

  @Command
  @NotifyChange({"programTemplateList", "selectedProgramTemplate"})
  public void submit(){
    service.save(selectedProgramTemplate);
    log.info("Save programTemplate {}", selectedProgramTemplate);
    //FIXME: component has to refresh by its self without redirect
    Executions.sendRedirect(null);
  }

  @Command
  @NotifyChange(".")
  public void delete(){
    service.delete(selectedProgramTemplate);
    log.info("Delete programTemplate {}", selectedProgramTemplate);
    //FIXME: component has to refresh by its self without redirect
    Executions.sendRedirect(null);
  }

  @Command
  @NotifyChange({"programTemplateList", "selectedProgramTemplate"})
  public void clear(){
    init();
  }

  @Init
  public void init(){
    keyword = "";
    programTemplateList = service.getAll();
    listTarif = tarifRepository.findAll();
    selectedProgramTemplate = new ProgramTemplate();
    selectedTarif = new Tarif();
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public List<ProgramTemplate> getProgramTemplateList() {
    return programTemplateList;
  }

  public void setProgramTemplateList(List<ProgramTemplate> programTemplateList) {
    this.programTemplateList = programTemplateList;
  }

  public ProgramTemplate getSelectedProgramTemplate() {
    return selectedProgramTemplate;
  }

  public void setSelectedProgramTemplate(ProgramTemplate selectedProgramTemplate) {
    this.selectedProgramTemplate = selectedProgramTemplate;
  }

  public List<Tarif> getListTarif() {
    return listTarif;
  }

  public void setListTarif(List<Tarif> listTarif) {
    this.listTarif = listTarif;
  }

  public Tarif getSelectedTarif() {
    return selectedTarif;
  }

  public void setSelectedTarif(Tarif selectedTarif) {
    this.selectedTarif = selectedTarif;
  }

}
