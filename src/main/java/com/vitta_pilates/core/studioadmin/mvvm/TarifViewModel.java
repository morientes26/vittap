package com.vitta_pilates.core.studioadmin.mvvm;

import com.vitta_pilates.core.shared.mvvm.BaseViewModel;
import com.vitta_pilates.core.studioadmin.service.TarifService;
import com.vitta_pilates.model.dao.Room;
import com.vitta_pilates.model.dao.Tarif;
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
public class TarifViewModel {

  private final Logger log = LoggerFactory.getLogger(TarifViewModel.class);

  private String keyword;
  private List<Tarif> tarifList;
  private Tarif selected = new Tarif();

  @WireVariable("tarifService")
  private TarifService service;


  @Command
  @NotifyChange("tarifList")
  public void search() {
    tarifList = service.findByKeywords(keyword);
  }

  @Command
  @NotifyChange({"tarifList", "selected"})
  public void submit(){
    new BaseViewModel<Tarif>(service, selected).submit();
  }

  @Command
  @NotifyChange(".")
  public void delete(){
    new BaseViewModel<Tarif>(service, selected).delete();
  }

  @Command
  @NotifyChange({"programTemplateList", "selectedProgramTemplate"})
  public void clear(){
    init();
  }

  @Init
  public void init(){
    keyword = "";
    tarifList = service.getAll();
    selected = new Tarif();
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }


  public List<Tarif> getTarifList() {
    return tarifList;
  }

  public void setTarifList(List<Tarif> tarifList) {
    this.tarifList = tarifList;
  }

  public Tarif getSelected() {
    return selected;
  }

  public void setSelected(Tarif selected) {
    this.selected = selected;
  }
}
