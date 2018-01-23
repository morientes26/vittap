package com.vitta_pilates.core.shared.mvvm;

import com.vitta_pilates.core.shared.component.MessageBox;
import com.vitta_pilates.core.shared.service.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Executions;

import java.util.List;

public class BaseViewModel<T> {

  private final Logger log = LoggerFactory.getLogger(BaseViewModel.class);

  private EntityService service;
  private T entity;

  public BaseViewModel(EntityService service, T entity){
    this.service = service;
    this.entity = entity;
  }

  public void submit(){
    service.save(entity);
    log.info("Save tarif {}", entity);
    //FIXME: component has to refresh by its self without redirect
    Executions.sendRedirect(null);
  }

  public void delete(){
    new MessageBox.MessageBoxBuilder().setDefaultConfirmDialog(() -> {
      if (service.delete(entity)) {
        log.info("Delete item {}", entity);
        //FIXME: component has to refresh by its self without redirect
        Executions.sendRedirect(null);
      } else new MessageBox.MessageBoxBuilder().setDefaultWarningDialog().createMessageBox();
      return true;
    }).createMessageBox();
  }
}
