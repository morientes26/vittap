package com.vitta_pilates.core.shared.component;


import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

import java.util.concurrent.Callable;

public class MessageBox {

  private String title;
  private final String label;
  private final Callable action;

  private MessageBox(final String title, final String label, final Callable action) {
    this.title = title;
    this.label = label;
    this.action = action;

    if (this.action==null)
      initDialog();
    else
      initConfirmDialog();
  }


  private void initDialog() {
    Messagebox.show(label, title, Messagebox.OK, Messagebox.EXCLAMATION);
  }

  private void initConfirmDialog() {

    EventListener<Messagebox.ClickEvent> clickListener = event -> {
      if (event.getData().toString().equals("YES"))
        action.call();
    };

    Messagebox.show(
            label,
            title,
            new Messagebox.Button[]{Messagebox.Button.YES, Messagebox.Button.NO},
            Messagebox.QUESTION,
            clickListener);

  }

  public static class MessageBoxBuilder {

    private String title;
    private String label;
    private Callable action;

    public MessageBoxBuilder() {
    }

    public MessageBoxBuilder(final String title, final String label) {
      this.title = title;
      this.label = label;
    }

    public MessageBoxBuilder setDefaultWarningDialog(){
      this.title = Labels.getLabel("dialog.warning.title");
      this.label = Labels.getLabel("dialog.warning.label");
      return this;
    }

    public MessageBoxBuilder setDefaultConfirmDialog(Callable action){
      this.title = Labels.getLabel("dialog.confirmation.title");
      this.label = Labels.getLabel("dialog.confirmation.label");
      this.action = action;
      return this;
    }

    public MessageBoxBuilder setTitle(String title) {
      this.title = title;
      return this;
    }

    public MessageBoxBuilder setLabel(String label) {
      this.label = label;
      return this;
    }

    public MessageBoxBuilder setAction(Callable action) {
      this.action = action;
      return this;
    }

    public MessageBox createMessageBox(){
      return new MessageBox(this.title, this.label, this.action);
    }
  }
}
